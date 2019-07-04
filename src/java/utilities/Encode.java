package utilities;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * Classe d'encodage
 */
public class Encode {

    /**
     * Encode un String en sha1
     *
     * @param s String a encodÃ©
     * @return Le String encodÃ© en sha1
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sha1(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Objects.requireNonNull(s);
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(s.getBytes("utf8"));
        return String.format("%040x", new BigInteger(1, digest.digest()));
    }

}
