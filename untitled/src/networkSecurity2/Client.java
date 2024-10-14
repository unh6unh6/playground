package networkSecurity2;

import networkSecurity1.encryption.Crypto;
import networkSecurity1.encryption.DES;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        PrintWriter out = null;
        BufferedReader in = null;

        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {

            socket = new Socket("localhost", 7777);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            String publicKeyStr = in.readLine();
            System.out.println("public key : " + publicKeyStr);
            PublicKey publicKey = Util.stringToPublicKey(publicKeyStr);

            while (true) {
                System.out.print("plain text : ");
                String message = scanner.nextLine();
                String encryptedMessage = RSA.encrypt(message, publicKey);
                System.out.println("encrypted message : " + encryptedMessage);
                out.println(encryptedMessage);
                out.flush();
                System.out.println("Send Success");

                if ("quit".equalsIgnoreCase(message))
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
