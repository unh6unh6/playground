package networkSecurity1.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class TripleDES implements Crypto{

    private static Cipher cipher;
    private static SecretKey secretKey;

    public TripleDES() throws Exception {
        cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        secretKey = KeyGenerator.getInstance("DESede").generateKey();
    }

    public TripleDES(SecretKey secretKey) throws Exception{
        cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        this.secretKey = secretKey;
    }

    @Override
    public String encrypt(String plainText) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    @Override
    public String decrypt(String cipherText) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }

    @Override
    public SecretKey getSecretKey() {
        return secretKey;
    }
}
