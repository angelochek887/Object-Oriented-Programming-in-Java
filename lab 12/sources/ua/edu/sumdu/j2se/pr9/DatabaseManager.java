package ua.edu.sumdu.j2se.pr9;

import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;

public class DatabaseManager {
    private String url;
    private String user;
    private String password;

    public DatabaseManager(String configPath) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(configPath)) {
            props.load(in);
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
        } catch (Exception e) {
            System.err.println("Error loading DB config: " + e.getMessage());
        }
    }

    public void savePhone(Phone ph, int quantity) {
        String sql = "INSERT INTO phones (type, brand, model, storage, price, os, quantity, " +
                     "camera_mp, has_flashlight, cooling, provider) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ph.getClass().getSimpleName().toUpperCase());
            pstmt.setString(2, ph.getBrand());
            pstmt.setString(3, ph.getModel());
            pstmt.setInt(4, ph.getStorage());
            pstmt.setDouble(5, ph.getPrice());
            pstmt.setString(6, ph.getOs().toString());
            pstmt.setInt(7, quantity);
            pstmt.setNull(8, Types.INTEGER); 
            pstmt.setNull(9, Types.BOOLEAN); 
            pstmt.setNull(10, Types.VARCHAR);
            pstmt.setNull(11, Types.VARCHAR); 

            if (ph instanceof SmartPhone) {
                pstmt.setInt(8, ((SmartPhone) ph).getCameraMp());
            }
            if (ph instanceof KeypadPhone) {
                pstmt.setBoolean(9, ((KeypadPhone) ph).isHasFlashlight());
            }
            if (ph instanceof GamingPhone) {
                pstmt.setInt(8, ((GamingPhone) ph).getCameraMp());
                pstmt.setString(10, ((GamingPhone) ph).getCooling());
            }
            if (ph instanceof SatellitePhone) {
                pstmt.setString(11, ((SatellitePhone) ph).getProvider());
            }

            pstmt.executeUpdate();
            System.out.println("-> Mirroring to DB: Success.");

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }
    }
}