/********************************************************************************************************
 * Realitzar la gestió de la informació de jugadors d'un joc. La informació que es té d'un determinat   *
 * jugador és: el nom del jugador (cadena de caracters), l'equip al qual pertany (enumerat) i el nombre *
 * de trofeus que ha obtingut (enter). L'equip pot ser un valor de la següent llista: ESPASES, OROS,    *
 * COPES i BASTOS. El programa que gestioni aquesta informació ha de ser capaç d'inserir un nombre      *
 * determinat d'objectes de la classe Jugador a un fitxer seqüencial d'objectes, mostrar per pantalla   *
 * el contingut d'aquest fitxer de jugadors i calcular dades com el nombre total de jugadors, el nombre *
 * total de trofeus guanyats, la mitjana aritmètica de trofeus per jugador i la desviació estàndar. A   *
 * més el programa ha de ser capaç de generar nous fitxers agrupant els jugadors per equips, aquests    *
 * fitxers s'anomenaran ESPASES.dat, OROS.dat, ... i contindran les dades del jugadors del mateix equip *
 * que el seu nom indica. El programa podrà mostrar el contingut d'aquests fitxers i també treure les   *
 * dades estadístiques d'ells.                                                                          *
 ********************************************************************************************************/

package programacion2_2013_2014_taller1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Lluis
 */
public class Programacion2_2013_2014_taller1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean sortir = false;
        int opcio;
        while (!sortir) {
            menu();
            opcio = llegirNum("\n\n\tInserir opció: ");
            switch (opcio) {
                case 1:
                    FitxerJugadorsOut jugador = new FitxerJugadorsOut("fitxers/jugadors.dat");
                    int num = llegirNum("\nQuants jugadors vols introduir? ");
                    for (int i = 1; i <= num; i++) {
                        String nom = llegirCadena("Nom jugador: ");
                        TipusEquip equip = llegirEquip("Equip (1)ESPASES, (2)OROS, (3)COPES, (4) BASTOS: ");
                        int trofeus = llegirNum("Nombre de trofeus: ");
                        Jugador j = new Jugador(nom, equip, trofeus);
                        jugador.escriu(j);
                    }
                    jugador.tancaJugadors();
                    break;
                case 2:
                    FitxerJugadorsIn fji = new FitxerJugadorsIn("fitxers/jugadors.dat");
                    System.out.print("Contingut del fitxer de jugadors\n" + fji.mostrarJugardors());
                    fji.tancarJugadors();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    sortir = true;
                    break;
            }

        }
    }
    
    private static void menu() {
        System.out.println("\n\nINFORMACIÓ DEL JOC");
        System.out.println("\n\t1. Inserir jugador");
        System.out.println("\t2. Veure jugadors");
        System.out.println("\t3. Estadistiques jugadors");
        System.out.println("\t4. Crear grups");
        System.out.println("\t5. Veure grups");
        System.out.println("\t6. Estadistiques grups");
        System.out.println("\t0. Sortir");
    }
    
    private static String llegirCadena(String msg) {
        String s = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg);
            s = in.readLine();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return s;

    }

    private static int llegirNum(String msg) {
        int x = 0;
        try {
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg);
            s = in.readLine();
            x = Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return x;
    }
    
    private static TipusEquip llegirEquip(String msg) {
        TipusEquip equip = null;
        try {
            boolean ok = false;
            String s;
            int x = 0;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(!ok) {
                System.out.print(msg);
                s = in.readLine();
                x = Integer.parseInt(s);
                ok = (x == 1 || x == 2 || x == 3 || x == 4);
            }
            switch(x) {
                case 1:
                    equip = TipusEquip.ESPASES;
                    break;
                case 2:
                    equip = TipusEquip.OROS;
                    break;
                case 3:
                    equip = TipusEquip.COPES;
                    break;
                case 4:
                    equip = TipusEquip.BASTOS;
                    break;
            }
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return equip;
    }
}
