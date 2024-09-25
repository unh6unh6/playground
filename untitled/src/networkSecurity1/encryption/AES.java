package networkSecurity1.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class AES implements Crypto{

    private static Cipher cipher;
    private static SecretKey secretKey;
    private static IvParameterSpec ivSpec;

    public AES() throws Exception {
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        secretKey = KeyGenerator.getInstance("AES").generateKey();
        ivSpec = new IvParameterSpec(new byte[16]);
    }

    public AES(SecretKey secretKey) throws Exception{
        cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        this.secretKey = secretKey;
        ivSpec = new IvParameterSpec(new byte[16]);
    }

    @Override
    public String encrypt(String plainText) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    @Override
    public String decrypt(String cipherText) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }

    @Override
    public SecretKey getSecretKey() {
        return secretKey;
    }
}
