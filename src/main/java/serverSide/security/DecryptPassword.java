package serverSide.security;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * decrypt an encrypted text.
 * encrypt strategy is not provided for security reasons.
 * note: developer can get original password value from memory after executing decrypt method.
 */
public final class DecryptPassword {
    private static final Logger LOGGER = Logger.getLogger(DecryptPassword.class.getSimpleName());

    public static String decrypt(String saltEncryptedPassword) throws BadPaddingException, IOException {
        if (StringUtils.isBlank(saltEncryptedPassword)) {
            LOGGER.warning("password should not be empty: "+saltEncryptedPassword);
            throw new IllegalArgumentException("check your mail server password: "+saltEncryptedPassword);
        }
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = createSecretKey("secret".toCharArray(), salt, iterationCount, keyLength);
        return decrypt(saltEncryptedPassword, key);
    }

    public static String encrypt(String originalPassword) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = createSecretKey("secret".toCharArray(), salt, iterationCount, keyLength);

        return encrypt(originalPassword, key);
    }

    private static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) {
        SecretKey keyTmp = null;
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            keyTmp = keyFactory.generateSecret(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }


    private static String decrypt(String string, SecretKeySpec key)
            throws BadPaddingException, IOException {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        try {
            Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
            return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
        } catch (NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }

    private static String encrypt(String property, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters parameters = pbeCipher.getParameters();
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    }

    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

}
