package Network;

import Products.IProduct;
import Products.Product;
import Products.Stack;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class TCPServer {
    public TCPServer(Stack<IProduct> productContainer) {
        try {
            int i = 1;
            ServerSocket s = new ServerSocket(3000);

            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(incoming, productContainer);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadedEchoHandler implements Runnable {
    public ThreadedEchoHandler(Socket i, Stack<IProduct> productContainer) {
        incoming = i;
        m_productContainer = productContainer;
    }

    public void run() {
        try {
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);

                out.println( "Hello! Enter BYE to exit." );

                // echo client input
                boolean done = false;
                while (!done && in.hasNextLine()) {
                    String line = in.nextLine();
                    //out.println("Echo: " + line);
                    if (line.trim().equals("BYE")) {
                        done = true;
                    } else if (line.trim().equals("get count")) {
                        out.println(m_productContainer.count());
                    } else if (line.trim().startsWith("get item")) {
                        String str = line.trim();
                        String num = str.substring(str.indexOf("=") + 1).trim();
                        int index = Integer.parseInt(num);
                        IProduct product = m_productContainer.getProduct(index);
                        if (product == null) {
                            out.println("Wrong index");
                        } else {
                            out.println(product.toString());
                        }
                    }
                }
            } finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Socket incoming;
    Stack<IProduct> m_productContainer;
}

