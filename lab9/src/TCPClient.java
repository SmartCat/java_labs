import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 26.01.13
 * Time: 3:18
 */

public class TCPClient {

    public static void main(String[] args) {
        String hostname = "127.0.0.1";
        if (args.length > 0) {
            hostname = args[0];
        }
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            Socket socket = new Socket(hostname, 3000);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream( )));
            Scanner sc = new Scanner(System.in);
            out = new PrintWriter(socket.getOutputStream( ));
            System.out.println("Connected to echo server");
            while (true) {
                String line = sc.nextLine();
                if (line.equals(".")) {
                    break;
                } else if (line.equals("get list")) {
                    out.println("get count");
                    out.flush();
                    String strCount = in.readLine();
                    System.out.println("Goods: " + strCount);
                    int count = Integer.parseInt(strCount);
                    for (int i = 0; i < count; i++) {
                        out.println("get item = " + i);
                        out.flush();
                        System.out.println("#" + i + " " + in.readLine( ));
                    }
                } else {
                    out.println(line);
                    out.flush( );
                    System.out.println(in.readLine( ));
                }
            }
        }
        catch (IOException e) {
            System.err.println(e);
        }
        finally {
            try {
                if (in != null)
                    in.close( );
                if (out != null)
                    out.close();
            }
            catch (IOException e) {}
        }
    } // end main
}
