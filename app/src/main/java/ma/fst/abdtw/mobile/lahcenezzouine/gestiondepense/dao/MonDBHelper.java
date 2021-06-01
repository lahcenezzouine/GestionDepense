package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MonDBHelper extends SQLiteOpenHelper {
    public final static int DB_VERSION = 1;
    public final static String DB_NAME = "DEPENSE_DB_LEZ";
    public final static String sql1 = "CREATE TABLE Categorie(idcat INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT NOT NULL);";
    public final static String sql2 = "CREATE TABLE Depense (iddep INTEGER PRIMARY KEY AUTOINCREMENT, libelle TEXT NOT NULL, date DATE, idcat INTEGER, montant FLOAT);";
    public final static String sql3 = "INSERT INTO Categorie VALUES(1, 'Santé'), (2, 'Loisir'), (3, 'Factures'), (4, 'Habillement');";

    public MonDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DB", "creation de la BD.....");
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);// Insert default categories
        insertDefaultDepenses(db);
        Log.i("DB", "la BD a ete cree avec succes.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        Log.i("DB", "upgrading BD.....");
        db.execSQL("DROP TABLE Depense;");
        db.execSQL("DROP TABLE Categorie;");
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);// Insert default categories
        insertDefaultDepenses(db);
        Log.i("DB", "upgrading BD a ete fait avec succes.");
    }

    private void insertDefaultDepenses(SQLiteDatabase db) {
        String sql4 = "INSERT INTO Depense VALUES(1, 'Ophtalmologue', '2021-03-15', 1, 350)";
        sql4 += ",(2, 'Eau', '2021-03-15', 3, 250)";
        sql4 += ",(3, 'Internet', '2021-05-11', 3, 300)";
        sql4 += ",(4, 'Voyage', '2021-03-03', 2, 1400)";
        sql4 += ",(5, 'Pantalon', '2019-11-21', 4, 200)";

        db.execSQL(sql4);// Insert default categories
    }
}
