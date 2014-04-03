/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package programacion2_2013_2014_taller1;

import java.io.Serializable;

/**
 *
 * @author Lluis
 */
public class Jugador implements Serializable {
    private String nom;
    private TipusEquip equip;
    private int trofeus;
    
    public static final Jugador CENTINELA = new Jugador("zzz", TipusEquip.SENSE, 0);

    public Jugador() {}

    public Jugador(String n, TipusEquip e, int t) {
        nom = n;
        equip = e;
        trofeus = t;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nom=" + nom + ", equip=" + equip + ", trofeus=" + trofeus + '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TipusEquip getEquip() {
        return equip;
    }

    public void setEquip(TipusEquip equip) {
        this.equip = equip;
    }

    public int getTrofeus() {
        return trofeus;
    }

    public void setTrofeus(int trofeus) {
        this.trofeus = trofeus;
    }
    
    public boolean esCentinela() {
        return "zzz".equals(nom);
    }
}
