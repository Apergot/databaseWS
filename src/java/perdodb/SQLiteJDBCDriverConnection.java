package perdodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Apergot
 */
public class SQLiteJDBCDriverConnection {

    //this works
    private static Connection connect() {
        Connection conn = null;
        try {
            //db path
            String url = "jdbc:sqlite:C:/sqlite/gui/Reservations";
            //connect to the db
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been stablished");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void disconnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void insertDB( String name, String email, String phone, String date, String time, String movie_id) throws SQLException{
        String sqlQuery = "INSERT INTO reservation(name, email, phone, date, time, movie_id) VALUES(?,?,?,?,?,?)";
        try(Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sqlQuery)){
            pstmt.setString(1,name);
            pstmt.setString(2,phone);
            pstmt.setString(3,email);
            pstmt.setString(4, date);
            pstmt.setString(5,time);
            pstmt.setString(6,movie_id);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static int getReservationId(String email) throws SQLException{
        String sqlQuery = "SELECT reservation_id from reservation where email = '"+email+"';";
        String id = "";
        try(Connection conn = connect();
                Statement pstmt = conn.createStatement()){
            ResultSet rs = pstmt.executeQuery(sqlQuery);
            while(rs.next()){
                id = rs.getString("reservation_id");
                System.out.println(id + "\n");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(id);
    }
    
    public static Reservation getReservation(int n) throws SQLException{
        String sqlQuery = "SELECT * from reservation where reservation_id = '"+n+"';";
        String[] reservationColumns;
        Reservation reservation = new Reservation();
        try(Connection conn = connect();Statement pstmt = conn.createStatement()){
            ResultSet rs = pstmt.executeQuery(sqlQuery);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            reservationColumns = new String[columns+1];
            for(int i = 0; i< columns; i++){
                reservationColumns[i] = md.getColumnName(i+1);
            }
            for (int i = 1; i < reservationColumns.length; i++) {
                String columnName = reservationColumns[i];
                String sqlQuery2 = "SELECT " +columnName + " from reservation where reservation_id = '"+n+"';";
                Statement pstmt2 = conn.createStatement();
                ResultSet rs2 = pstmt2.executeQuery(sqlQuery2);
                while(rs2.next() && reservationColumns[i] != null){
                    reservationColumns[i] = rs2.getString(columnName);
                }
            }
            reservation = new Reservation(reservationColumns[1],
                    reservationColumns[2],reservationColumns[3], reservationColumns[4]
                    ,reservationColumns[5],reservationColumns[6]);
            
            return reservation;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return reservation;
    }
    
    
}