package com.vertback.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Created by HW on 10/4/16.
 */
public class SecurityUtils {

    private static Logger logger = Logger.getLogger(SecurityUtils.class.getName());

    public static  String get_SHA_512_SecurePassword(String passwordToHash){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            //md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            logger.log(Level.SEVERE,"",e);
        }
        catch (Exception e){
            logger.log(Level.SEVERE, "", e);
        }
        return generatedPassword;
    }

}
