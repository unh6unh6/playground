package networkSecurity1;

import networkSecurity1.encryption.AES;
import networkSecurity1.encryption.Crypto;
import networkSecurity1.encryption.DES;
import networkSecurity1.encryption.TripleDES;

public class encryptionTest {
    static String text10Byte = Util.readFile("./src/networkSecurity1/text1.txt");
    static String text100Byte = Util.readFile("./src/networkSecurity1/text2.txt");
    static String text1kByte = Util.readFile("./src/networkSecurity1/text3.txt");

    public static void main(String[] args) throws Exception {
        /** AES + CBC **/
        System.out.println("AES + CBC");
        displayResult(new AES());

        /** DES + ECB **/
        System.out.println("DES + ECB");
        displayResult(new DES());

        /** 3DES + ECB **/
        System.out.println("3AES + ECB");
        displayResult(new TripleDES());
    }
    static void displayResult(Crypto algorithm) {
        System.out.println("10Byte");
        encryptAndDecrypt(text10Byte, algorithm);
        System.out.println("100Byte");
        encryptAndDecrypt(text100Byte, algorithm);
        System.out.println("1kByte");
        encryptAndDecrypt(text1kByte, algorithm);
        System.out.println();
    }

    static void encryptAndDecrypt(String plainText, Crypto crypto) {
        try {
            System.out.println("Plain Text : " + plainText);
            String encryptedText = crypto.encrypt(plainText);
            System.out.println("Encrypted Text : " + encryptedText);
            String decryptedText = crypto.decrypt(encryptedText);
            System.out.println("Decrypted Text : " + decryptedText);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
