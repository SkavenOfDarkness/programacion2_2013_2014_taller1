/************************************
 * Clase lectura de fitxer jugadors *
 ************************************/

package programacion2_2013_2014_taller1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Lluis
 */
public class FitxerJugadorsIn {
    private FileInputStream fis;
    private ObjectInputStream ois;
    
    //El constructor obri un fitxer d'objectes per a lectura a partir del nom del fitxer físic que passa per paràmetre
    public FitxerJugadorsIn(String nom) {
        try {
            fis = new FileInputStream(nom);
            ois = new ObjectInputStream(fis);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }    
    }
    
    //Un recorregut del fitxer fins al centinel·la que va enregistrant les dades a una cadena de caracters.
    public String mostrarJugardors() {
        String jugador = null;
        try {   
            Jugador j = (Jugador)ois.readObject();
            while (!j.esCentinela()) {
                jugador += j;
                jugador += "\n";
                j = (Jugador)ois.readObject();
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error:  " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return jugador;
    }

    //Llegeix un jugador del fitxer
    public Jugador llegir() {
        Jugador j = null;
        try {   
            j = (Jugador)ois.readObject();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error:  " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return j;
    }
    
    //Mostre les estadistiques del fitxer pasat
    
    //Tanca el fitxer
    public void tancarJugadors() {
        try {
            ois.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
