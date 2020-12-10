/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 *
 * @author Samuel
 */
import java.util.*;
import java.io.*;
import javax.persistence.*;
import static javax.persistence.TemporalType.DATE;

@Entity
@Table(name="Historico")
public class TablaHD implements Serializable {
    public int idsensor;
    public double value;
    public String date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public TablaHD(int idsensor, double value, String date, int id) {
        this.idsensor = idsensor;
        this.value = value;
        this.date = date;
        this.id = id;
    }

    public TablaHD() {
    }

    public int getIdsensor() {
        return idsensor;
    }

    public void setIdsensor(int idsensor) {
        this.idsensor = idsensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TablaHD{" + "value=" + value + '}';
    }
    
    
}
