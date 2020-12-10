/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;
import java.io.*;
import javax.persistence.*;
/**
 *
 * @author Samuel
 */
@Entity
@Table(name="TipoSensor")
public class TablaTS implements Serializable{
    @Id
    public String tipo;
    public String nombre;
    public double minimo;
    public double maximo;
    public String promedio;
    public double numeroHoras;

    public TablaTS(String tipo, String nombre, double minimo, double maximo, String promedio, double numeroHoras) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.minimo = minimo;
        this.maximo = maximo;
        this.promedio = promedio;
        this.numeroHoras = numeroHoras;
    }
    
    

    public TablaTS() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
        
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public double getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(double numeroHoras) {
        this.numeroHoras = numeroHoras;
    }
    
    
}
