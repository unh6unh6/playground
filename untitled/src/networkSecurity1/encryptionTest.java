package networkSecurity1;

import networkSecurity1.encryption.AES;
import networkSecurity1.encryption.Crypto;
import networkSecurity1.encryption.DES;
import networkSecurity1.encryption.TripleDES;

public class encryptionTest {
    public static void main(String[] args) throws Exception {
        String text10Byte = Util.readFile("./src/networkSecurity1/text1.txt");
        String text100Byte = Util.readFile("./src/networkSecurity1/text2.txt");
        String text1kByte = Util.readFile("./src/networkSecurity1/text3.txt");

        /** AES + CBC **/
        System.out.println("AES + CBC");
        encryptAndDecrypt(text10Byte, new AES());
        encryptAndDecrypt(text100Byte, new AES());
        encryptAndDecrypt(text1kByte, new AES());


        /** DES + ECB **/
        System.out.println("\n\nDES + ECB");
        encryptAndDecrypt(text10Byte, new DES());
        encryptAndDecrypt(text100Byte, new DES());
        encryptAndDecrypt(text1kByte, new DES());

        /** 3DES + ECB **/
        System.out.println("\n\n3DES + ECB");
        encryptAndDecrypt(text10Byte, new TripleDES());
        encryptAndDecrypt(text100Byte, new TripleDES());
        encryptAndDecrypt(text1kByte, new TripleDES());
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
