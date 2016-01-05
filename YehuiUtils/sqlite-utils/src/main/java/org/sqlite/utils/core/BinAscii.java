package org.sqlite.utils.core;


import android.support.annotation.Nullable;
import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * Wrapper of common binary-string operations
 */
public class BinAscii {
    /**
     * a hmac-sha1 auth digest
     * <p/>
     * Corresponding server side python impl:
     * >>> import hmac, sha
     * >>> key, msg = ... , ...
     * >>> hmac.new(key, msg, sha).digest()
     *
     * @param key  secret key
     * @param msgs message body
     * @return the digested binary
     */
    public static byte[] hmacSha1(byte[] key, byte[][] msgs) {
        Mac mac;
        try {
            mac = Mac.getInstance("HmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            Logging.logStackTrace(e);
            return null;
        }
        SecretKeySpec keySpec = new SecretKeySpec(key, "Hmac");
        try {
            mac.init(keySpec);
        } catch (InvalidKeyException e) {
            Logging.logStackTrace(e);
            return null;
        }
        for (byte[] msg : msgs) {
            mac.update(msg);
        }
        return mac.doFinal();
    }

    public static byte[] hmacSha1(byte[] key, byte[] msg) {
        return hmacSha1(key, new byte[][]{msg});
    }

    public static byte[] base64decode(String s) {
        return Base64.decode(s, Base64.DEFAULT);
    }

    public static String base64encode(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static String hexlify(byte[] src) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < src.length; i++) {
            String hex = Integer.toHexString(0xFF & src[i]);
            if (hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(0xFF & src[i]));
        }
        return hexString.toString();
    }

    public static
    @Nullable
    byte[] unhexlify(String s) {
        if ((s.length() & 1) != 0) {
            return null;
        }
        byte[] data = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) +
                    Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static byte[] md5(byte[] src) {
        return md5(new byte[][]{src});
    }

    public static byte[] md5(byte[][] src) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            Logging.logStackTrace(e);
            return null; // This should never happens
        }
        for (byte[] s : src) {
            md5.update(s);
        }
        return md5.digest();
    }
}
