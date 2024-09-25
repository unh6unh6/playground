package networkSecurity1;

import networkSecurity1.encryption.Crypto;
import networkSecurity1.encryption.DES;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        PrintWriter out = null;

        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {

            socket = new Socket("localhost", 7777);

            Crypto crypto = new DES();
            String strKey = Util.secretKeyToString(crypto.getSecretKey());
            System.out.println("key : " + strKey);

            out = new PrintWriter(socket.getOutputStream());
            out.println(strKey);
            out.flush();

            System.out.println("success to send DES key");

            while (true) {
                System.out.print("plain text : ");
                String plainText = scanner.nextLine();
                String encryptMessage = crypto.encrypt(plainText); // 암호화
                System.out.println("encrypt message : " + encryptMessage);
                out.println(encryptMessage);
                out.flush();
                System.out.println("Send Success");
                if ("quit".equalsIgnoreCase(plainText))
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                scanner.close();
                if (socket != null) socket.close();
                System.out.println("Close");
            } catch (IOException e) {
                System.out.println("Socket Connection Error");
            }
        }
    }
}
