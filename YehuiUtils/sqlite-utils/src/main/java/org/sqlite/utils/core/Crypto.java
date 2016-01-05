package org.sqlite.utils.core;

import android.content.Context;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {

    private Context mContext;

    public static Crypto newInstance(Context context) {
        return new Crypto(context);
    }

    private Crypto(Context context) {
        this.mContext = context;
    }

    private final byte[] iv = new byte[]{
            126, 12, -53, 86, 28, 58, -126, 125, -76, 102, 118, -51, -24, -116, 11, 57};

    private byte[] getKey(String udidStr) {
        byte[] udid = BinAscii.unhexlify(udidStr);
        if (udid == null) {
            throw new IllegalStateException();
        }
        byte lastByte = 0x7f;
        for (int i = 0; i < udid.length; i++) {
            udid[i] = (byte) ((udid[i] * 60863847) ^ lastByte);
            lastByte = udid[i];
        }
        return udid;
    }

    public byte[] encrypt(byte[] message) {
        return encrypt(message, DeviceId.newInstance(mContext).getDeviceId());
    }

    private byte[] encrypt(byte[] message, String udid) {
        return aesEnc(getKey(udid), message, iv);
    }

    public byte[] aesEnc(byte[] key, byte[] content, byte[] iv) {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec;
        if (iv == null) {
            ivSpec = new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        } else {
            ivSpec = new IvParameterSpec(iv);
        }
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                IllegalBlockSizeException |
                BadPaddingException |
                InvalidAlgorithmParameterException ignored) {
        }
        return null;
    }

    public byte[] decrypt(byte[] message) {
        return decrypt(message, DeviceId.newInstance(mContext).getDeviceId());
    }

    private byte[] decrypt(byte[] message, String udid) {
        return aesDec(getKey(udid), message, iv);
    }

    public byte[] aesDec(byte[] key, byte[] content, byte[] iv) {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec;
        if (iv == null) {
            ivSpec = new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        } else {
            ivSpec = new IvParameterSpec(iv);
        }
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                InvalidKeyException |
                BadPaddingException ignored) {
            Logging.logStackTrace(ignored);
        }
        return null;
    }

    public byte[] aesDecNoPadding(byte[] key, byte[] content, byte[] iv) {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec;
        if (iv == null) {
            ivSpec = new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        } else {
            ivSpec = new IvParameterSpec(iv);
        }
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                InvalidKeyException |
                BadPaddingException ignored) {
            Logging.logStackTrace(ignored);
        }
        return null;
    }
}