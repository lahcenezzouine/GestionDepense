package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.models;

import java.io.Serializable;

public class Depense implements Serializable {
    public static String serialVersionUID = "00_163051469151804394L";

    private int idDep;
    private String depense;
    private String dateDep;
    private int idCat;
    private String categorie;
    public float montant;

    public Depense(int idDep, String depense, String dateDep, int idCat, float montant) {
        this.idDep = idDep;
        this.depense = depense;
        this.dateDep = dateDep;
        this.idCat = idCat;
        this.montant = montant;
    }

    public Depense(String depense, String dateDep, int idCat, float montant) {
        this.depense = depense;
        this.dateDep = dateDep;
        this.idCat = idCat;
        this.montant = montant;
    }

    public int getIdDep() {
        return idDep;
    }

    public void setIdDep(int idDep) {
        this.idDep = idDep;
    }

    public String getDepense() {
        return depense;
    }

    public void setDepense(String depense) {
        this.depense = depense;
    }

    public String getDateDep() {
        return dateDep;
    }

    public void setDateDep(String dateDep) {
        this.dateDep = dateDep;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}