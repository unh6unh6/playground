package networkSecurity1.encryption;

import javax.crypto.SecretKey;

public interface Crypto {
    String encrypt(String plainText) throws Exception;
    String decrypt(String cipherText) throws Exception;

    SecretKey getSecretKey();
}
