package cohorte16.homeservice.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class EncryptData {

        public static String encryptPassword(String password) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] hashedBytes = md.digest(password.getBytes());
                StringBuilder hexString = new StringBuilder();

                for (byte b : hashedBytes) {
                    String hex = Integer.toHexString(0xFF & b);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }

                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }

    public static boolean verifyPassword(String originalPassword, String storedHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = md.digest(originalPassword.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            String computedHash = hexString.toString();
            return storedHash.equals(computedHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }



}
