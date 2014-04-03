/*******************************************
 * Classe escritura de fitxer de jugadors. *
 *******************************************/

package programacion2_2013_2014_taller1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Lluis
 */
public class FitxerJugadorsOut {
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    
    //El constructor obri un fitxer d'objectes per a escritura a partir del nom del fitxer físic que passa per paràmetre
    public FitxerJugadorsOut(String nom) {
        try {
            fos = new FileOutputStream(nom);
            oos = new ObjectOutputStream(fos);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }  
    }
    
    //Escriu un objecte jugador.
    public void escriu(Jugador j) {
        try {
            oos.writeObject(j);
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //Genera els fitxers de grups
    
    //Tanca el fitxer i escriu el centinel·la.
    public void tancaJugadors() {
        try {
            oos.writeObject(Jugador.CENTINELA);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
