package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.models.Depense;

public class DepenseDAO {

    Context context;
    SQLiteDatabase db;

    public DepenseDAO(Context context) {
        this.context = context;
        MonDBHelper helper = new MonDBHelper(context);
        this.db = helper.getWritableDatabase();
    }

    public ArrayList<Depense> getAllDepenses() {
        ArrayList<Depense> depenses = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT d.*, c.nom FROM Depense d INNER JOIN Categorie c ON d.idcat=c.idcat", null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                Depense dep = new Depense(c.getInt(0), c.getString(1), c.getString(2), Integer.parseInt(c.getString(3)), c.getFloat(4));
                dep.setCategorie(c.getString(5));
                depenses.add(dep);
            } while (c.moveToNext());
            c.close();
        }
        return depenses;
    }

    public long addDepense(Depense d) {
        ContentValues cv = new ContentValues();
        cv.put("libelle", d.getDepense());
        cv.put("date", d.getDateDep());
        cv.put("montant", d.getMontant());
        cv.put("idcat", d.getIdCat());

        Log.e("Add", "Add depense");
        return db.insert("Depense", null, cv);
    }

    public void updateDepense(Depense d) {
        ContentValues cv = new ContentValues();
        cv.put("libelle", d.getDepense());
        cv.put("date", d.getDateDep());
        cv.put("montant", d.getMontant());
        cv.put("idcat", d.getIdCat());

        Log.e("Update", "Update depense, id: " + d.getDepense());
        db.update("Depense", cv, " iddep=? ", new String[]{ String.valueOf(d.getIdDep())});
    }

    public void deleteDepense(int id) {
        db.execSQL("DELETE FROM Depense WHERE iddep=" + id + ";");
        Log.i("Delete", "Delete depense");
    }
}