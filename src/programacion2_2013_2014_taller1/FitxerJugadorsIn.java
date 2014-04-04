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
        String jugador = "";
        try {   
            Jugador j = (Jugador)ois.readObject();
            while (!j.esCentinela()) {
                jugador += j;
                jugador += "\n";
                j = (Jugador)ois.readObject();
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
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
            System.out.println("Error: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return j;
    }
    
    //Mostre les estadistiques del fitxer pasat
    public String mostrarEstadistiques() {
        String estadistiques = "";
        int totalTrofeus = 0;
        int totalJugadors = 0;
        double mitjanaAritmetica;
        try {
            Jugador j = (Jugador)ois.readObject();
            if(!j.esCentinela()) {
                while(!j.esCentinela()) {
                    totalTrofeus = totalTrofeus + j.getTrofeus();
                    totalJugadors++;
                    j = (Jugador)ois.readObject();
                }
                mitjanaAritmetica = totalTrofeus / totalJugadors;
                tancarJugadors();
                FitxerJugadorsIn desviacio = new FitxerJugadorsIn("fitxers/jugadors.dat");
                estadistiques = "\tTotal jugadors: " + totalJugadors + "\n\tTotal Trofeus: " + totalTrofeus
                        + "\n\tMitjana Aritmètica: " + mitjanaAritmetica + desviacio.desviacionEstandar(mitjanaAritmetica, totalJugadors);
            }
            tancarJugadors();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error:  " + e.getMessage());
        }
        return estadistiques;
    }
    
    //Calcula la desviació estàndar
    private String desviacionEstandar(double mitjanaAritmetica, int totalJugadors) {
        String desviacio = "";
        double calcul = 0.0;
        try { 
            Jugador j = (Jugador)ois.readObject();
            while(!j.esCentinela()) {
                calcul = calcul + ((j.getTrofeus() - mitjanaAritmetica) * (j.getTrofeus() - mitjanaAritmetica));
                j = (Jugador)ois.readObject();
            }
            if(totalJugadors <= 1) {
                totalJugadors = 2;
            }
            //Math.sqrt() permet fer l'arrel cuadrada d'un nombre introduit
            desviacio = "\n\tDesviació Estàndar: " + Math.sqrt(((1 / ((double)totalJugadors - 1)) * calcul));
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error:  " + e.getMessage());
        }
        return desviacio;
    }
    
    //Crear els diferents grups
    public void crearGrups(FitxerJugadorsOut espases, FitxerJugadorsOut oros, FitxerJugadorsOut copes, FitxerJugadorsOut bastos) {
        try {
            Jugador j = (Jugador)ois.readObject();
            while(!j.esCentinela()) {
                switch(j.getEquip()) {
                    case ESPASES:
                        espases.escriu(j);
                        break;
                    case OROS:
                        oros.escriu(j);
                        break;
                    case COPES:
                        copes.escriu(j);
                        break;
                    case BASTOS:
                        bastos.escriu(j);
                        break;
                }
                j = (Jugador)ois.readObject();
            }
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Error:  " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error:  " + e.getMessage());
        }
    }

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
