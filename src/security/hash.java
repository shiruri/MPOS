package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class hash {

    static Connection con = null;
    static Statement stmt = null;
    private static byte[] salt;
    private String password;

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getCurrentSalt() {
        return this.salt;
    }

    public String getPassword(String password,byte[] salt) {
          String passwordHash = getSecurePassword(password,salt);
        return passwordHash;
    }

    /*
	 * stmt = con.createStatement(); String sqlDelete = "DELETE FROM admin "
	 * +"Where adminId = ?";
	 * 
	 * PreparedStatement prep = con.prepareStatement(sqlDelete); prep.setString(1,
	 * id); prep.executeUpdate();
     */
    // tutorial from  https://www.javaguides.net/2020/02/java-sha-256-hash-with-salt-example.html too lazy to learn actual cryptograpy
    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    // salt

    public static String getSecurePassword(String password, byte[] salt) {
        hash c = new hash();
        c.setPassword(password);
        String generatedPassword = null;
        try {

            c.setSalt(salt);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error Logging in");
        }

        return generatedPassword;
    }
}
