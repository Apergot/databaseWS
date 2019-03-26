/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perdodb;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Apergot
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservation")
public class Reservation implements Serializable{
    @XmlElement(name = "name")
    private final String name;
    @XmlElement(name = "email")
    private final String email;
    @XmlElement(name = "phone")
    private final String phone;
    @XmlElement(name = "date")
    private final String date;
    @XmlElement(name = "time")
    private final String time;
    @XmlElement(name = "movie_id")
    private final String movie_id;

    public Reservation(String name, String phone, String email, String date, String time, String movie_id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.movie_id = movie_id;
    }
    public Reservation(){
        name = "";
        email = "";
        phone = "";
        date = "";
        time = "";
        movie_id = "";
    }

    public String getMovie_id() {
        return movie_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
