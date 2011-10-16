package labrisca;

import java.awt.Image;

/**
 * LaBrisca 2011
 * @author Hector COsta Guzman
 */
public class Carta {
    
    private Image imagen;
    
    private int puntos;

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
    private String palo;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }
    private int num;
    
    public Carta(){}
    
    public Carta(String palo, int num, int puntos, Image imagen){
        this.palo = palo;
        this.num = num;
        this.puntos = puntos;
        this.imagen = imagen;
    }
    
}