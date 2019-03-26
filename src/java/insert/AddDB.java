/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insert;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import perdodb.SQLiteJDBCDriverConnection;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import perdodb.Reservation;

/**
 *
 * @author Apergot
 */
@WebService(serviceName = "AddDB")
public class AddDB {

    @WebMethod(operationName = "insertDB")
    public String insertDB(@WebParam(name = "name") String name, 
            @WebParam(name = "phone") String phone, @WebParam(name = "email") String email, 
            @WebParam(name = "date") String date, @WebParam(name = "time") String time, 
            @WebParam(name = "movie_id") String movie_id) {
        try {
            SQLiteJDBCDriverConnection.insertDB(name, email, phone, date, time, movie_id);
        } catch (SQLException ex) {
            Logger.getLogger(AddDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Your reservation has been created successfully!";
    }
    
    @WebMethod(operationName = "getReservationId")
    public int getReservationId(@WebParam(name = "email") String email){
        try {
            return SQLiteJDBCDriverConnection.getReservationId(email);
        } catch (SQLException ex) {
            Logger.getLogger(AddDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    @WebMethod(operationName = "getReservation")
    public Reservation getReservation(@WebParam(name = "id") int id){
        try {
            return SQLiteJDBCDriverConnection.getReservation(id);
        } catch (SQLException ex) {
            Logger.getLogger(AddDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
