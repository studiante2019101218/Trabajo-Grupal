/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public class Pasajero {
    private String bolnro;
    private String VIANRO;
    private String Nom_pas;
    private int Nro_asi;
    private String tipo;
    private double pago;

    public String getBolnro() {
        return bolnro;
    }

    public void setBolnro(String bolnro) {
        this.bolnro = bolnro;
    }

    public String getVIANRO() {
        return VIANRO;
    }

    public void setVIANRO(String VIANRO) {
        this.VIANRO = VIANRO;
    }

    public String getNom_pas() {
        return Nom_pas;
    }

    public void setNom_pas(String Nom_pas) {
        this.Nom_pas = Nom_pas;
    }

    public int getNro_asi() {
        return Nro_asi;
    }

    public void setNro_asi(int Nro_asi) {
        this.Nro_asi = Nro_asi;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }
    
    
}
