package networkSecurity2;

import java.security.PrivateKey;
import java.security.PublicKey;

public class encryptionTest {
    public static void main(String[] args) {
        try {
            RSA rsa = new RSA();
            PublicKey publicKey = rsa.getPublicKey();
            PrivateKey privateKey = rsa.getPrivateKey();

            String plainText = "네트워크 보안 민경윤 RSA 테스트!!";
            System.out.println("평문 : " + plainText);
            String encryptedText = rsa.encrypt(plainText, publicKey);
            System.out.println("암호문 : " + encryptedText);
            String decryptedText = rsa.decrypt(encryptedText, privateKey);
            System.out.println("복호문 : " + decryptedText);
            String sign = rsa.sign(plainText, privateKey);
            System.out.println("서명 : " + sign);
            if (rsa.verify(plainText, sign, publicKey))
                System.out.println("유효한 서명입니다.");
            else
                System.out.println("유효하지않은 서명입니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
