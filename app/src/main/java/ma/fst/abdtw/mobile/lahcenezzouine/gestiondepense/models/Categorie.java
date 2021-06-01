package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.models;

import java.io.Serializable;

import kotlin.text.UStringsKt;

public class Categorie implements Serializable {
    public static String serialVersionUID = "01_2163051469151804394L";
    private int idcat;
    private String nom;

    public Categorie(int idcat, String nom) {
        this.idcat = idcat;
        this.nom = nom;
    }

    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // To display object as a string in spinner
    @Override
    public String toString() {
        return nom;
    }
}