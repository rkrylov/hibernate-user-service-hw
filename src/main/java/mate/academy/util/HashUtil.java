package mate.academy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String CRYPTO_ALGORITHM = "SHA-512";

    public static String hashPass(String password, byte[] salt) {
        StringBuilder hashedPass = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CRYPTO_ALGORITHM);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b : digest) {
                hashedPass.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "Could not create hash using" + CRYPTO_ALGORITHM + " algorithm");
        }
        return hashedPass.toString();
    }

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
