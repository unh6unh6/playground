package networkSecurity2;

import networkSecurity1.encryption.Crypto;
import networkSecurity1.encryption.DES;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {

        BufferedReader in = null;
        PrintWriter out = null;

        ServerSocket serverSocket = null;
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);


        try {
            RSA rsa = new RSA();
            PublicKey publicKey = rsa.getPublicKey();
            PrivateKey privateKey = rsa.getPrivateKey();

            serverSocket = new ServerSocket(7777);

            System.out.println("Waiting for client connect...");
            socket = serverSocket.accept();

            System.out.println("Client Connected !");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            out.println(Util.publicKeyToString(publicKey));
            out.flush();
            System.out.println("public key 전송 완료");

            while(true) {
                String message = in.readLine();
                System.out.println("received message : " + message);
                String decryptedMessage = RSA.decrypt(message, privateKey);
                System.out.println("decrypted message : " + decryptedMessage);
                if ("quit".equalsIgnoreCase(message))
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                scanner.close();
                socket.close();
                serverSocket.close();
                System.out.println("Close");
            } catch (IOException e) {
                System.out.println("Socket Connection Error");
            }
        }
    }
}
