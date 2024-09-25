package networkSecurity1.encryption;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class DES implements Crypto{
    private static Cipher cipher;
    private static SecretKey secretKey;

    public DES() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        secretKey = KeyGenerator.getInstance("DES").generateKey();
    }

    public DES(SecretKey secretKey) throws Exception{
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
