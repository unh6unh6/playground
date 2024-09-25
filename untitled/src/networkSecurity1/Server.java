package networkSecurity1;

import networkSecurity1.encryption.Crypto;
import networkSecurity1.encryption.DES;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        BufferedReader in = null;

        ServerSocket serverSocket = null;
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(7777);

            System.out.println("Waiting for client connect...");
            socket = serverSocket.accept();			// 연결대기

            System.out.println("Client Connected !");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String strKey = in.readLine();
            System.out.println("key : " + strKey);
            SecretKey secretKey = Util.stringToSecretKey(strKey);
            Crypto crypto = new DES(secretKey);
            System.out.println("success to receive DES key");


            while(true) {
                String receivedMessage = in.readLine();
                System.out.println("received message : " + receivedMessage);
                String decryptMessage = crypto.decrypt(receivedMessage);
                System.out.println("decrypt message : " + decryptMessage);
                if ("quit".equalsIgnoreCase(decryptMessage))
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
