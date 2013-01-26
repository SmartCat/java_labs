package Network;

import Main.MainClass;
import Products.IProduct;
import Products.Product;
import Products.Stack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 26.01.13
 * Time: 1:26
 */
public class UDPServer {
    final String ASK_STRING = "CONTAINQ";
    final String RESPONSE_STRING = "CONTAINR";

    Stack<IProduct> m_productContainer;

    byte  buf_in[], buf_out[];
    DatagramPacket in_packet, out_packet;

    DatagramSocket in_sock = null;
    DatagramSocket out_sock = null;

    public UDPServer(Stack<IProduct> productContainer) {
        m_productContainer = productContainer;

        buf_in = new byte[250];
        buf_out = new byte[0];
        in_packet = new DatagramPacket(buf_in, 250);
        out_packet = new DatagramPacket(buf_out, 0);

        try{
            in_sock = new DatagramSocket(1000);
            out_sock = new DatagramSocket(1001);
        }catch(Exception e){
            System.out.println("Socket creation error !!!");
            e.printStackTrace();
        }

        startListenInOtherThread();
    }

    public void startListenInOtherThread() {
        class newThread extends Thread {
            public void run() {
                startListen();
            }
        }

        Thread thread = new newThread();
        thread.start();
    }

    private void debugPrint(String msg) {
        System.out.println("UDP " + msg);
    }

    private void startListen() {
        while (true) {
            try{
                in_sock.receive(in_packet);
                debugPrint("Received a packet");

                String askString = new String(buf_in.clone());

                if (askString.startsWith(ASK_STRING)) {
                    DataInputStream dis = new DataInputStream( new ByteArrayInputStream(buf_in, ASK_STRING.length(), buf_in.length - ASK_STRING.length()));
                    int type  = dis.readInt();

                    int index = -1;
                    if (type == 2) {
                        index = dis.readInt();
                    }

                    boolean OkResult;
                    int responseInt = -1;
                    double responseDouble = 0;

                    try {
                        if (type == 1) {
                            responseInt = m_productContainer.count();
                            responseDouble = MainClass.avgPrice(m_productContainer);
                        } else if (type == 2) {
                            Product product = (Product) m_productContainer.getProduct(index);
                            if (product == null) {
                                responseInt = -1;
                                responseDouble = 0;
                            } else {
                                responseInt = index;
                                responseDouble = product.getPrice();
                            }
                        } else {
                            throw new RuntimeException();
                        }

                        OkResult = true;
                    } catch(Exception e) {
                        OkResult = false;
                    }

                    if (OkResult) {
                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        DataOutputStream dos = new DataOutputStream(bs);
                        dos.writeBytes(RESPONSE_STRING);
                        dos.writeInt(type);
                        dos.writeInt(responseInt);
                        dos.writeDouble(responseDouble);
                        buf_out = bs.toByteArray();
                        out_packet.setData(buf_out);
                        out_packet.setLength(buf_out.length);
                        out_packet.setPort(2001);
                        out_packet.setAddress(in_packet.getAddress());
                        out_sock.send(out_packet);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
