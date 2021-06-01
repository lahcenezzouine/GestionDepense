package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.models.Categorie;

public class CategorieDAO {
    Context context;
    SQLiteDatabase db;

    public CategorieDAO(Context context) {
        this.context = context;
        MonDBHelper helper = new MonDBHelper(context);
        this.db = helper.getWritableDatabase();
    }

    public ArrayList<Categorie> getAllCategories() {
        ArrayList<Categorie> categories = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Categorie", null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                Categorie cat = new Categorie(c.getInt(0), c.getString(1));
                categories.add(cat);
            } while (c.moveToNext());
            c.close();
        }

        return categories;
    }

    public long addCategorie(Categorie d) {
        ContentValues cv = new ContentValues();
        cv.put("nom", d.getNom());

        Log.e("Add", "Add Categorie");
        return db.insert("Depense", null, cv);
    }

    public void deleteCategorie(int id) {
        db.execSQL("DELETE FROM Categorie WHERE idcat=" + id + ";");
        Log.i("Delete", "Delete Categorie");
    }
}