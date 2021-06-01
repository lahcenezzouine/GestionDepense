package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.ui.categories;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.MainActivity;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.R;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.dao.CategorieDAO;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.models.Categorie;

public class CategorieAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<Categorie> categoriesList;

    public CategorieAdapter(Context ctx, ArrayList<Categorie> categories) {
        this.ctx = ctx;
        this.categoriesList = categories;
    }

    @Override
    public int getCount() {
        return categoriesList.size();
    }

    @Override
    public Categorie getItem(int position) {
        return categoriesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(ctx);
        convertView = inf.inflate(R.layout.categorie_list_item, parent, false);

        Categorie c = getItem(position);
        TextView txt_descriptionDepense = convertView.findViewById(R.id.txt_nomCategorie);
        txt_descriptionDepense.setText(c.getNom());

        CategorieDAO categorieDAO = new CategorieDAO(ctx);
        // perform view event
        ImageButton btn_view = convertView.findViewById(R.id.btn_view);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewCategorie(c);
            }
        });

        // perform delete categorie event
        ImageButton btn_delete = convertView.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCategorieWithConfirmDialog(categorieDAO, c);
            }
        });

        return convertView;
    }

    private void viewCategorie(Categorie c) {
        Bundle args = new Bundle();
        args.putSerializable(Categorie.serialVersionUID, c);
        Fragment fragment = new CategorieDetailsFragment();
        fragment.setArguments(args);

        FragmentManager fragmentManager = ((AppCompatActivity) ctx).getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void deleteCategorieWithConfirmDialog(CategorieDAO categorieDAO, Categorie c) {
        AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        alert.setTitle("Supprimer catégorie");
        alert.setMessage("Etes-vous sûr que vous voulez supprimer?");
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                categorieDAO.deleteCategorie(c.getIdcat());
                Toast.makeText(ctx, "Supprimer avec succés", Toast.LENGTH_SHORT).show();

                // reload categoriesListFragment
                Fragment fragment = new CategoriesFragment();
                FragmentManager fragmentManager = ((AppCompatActivity) ctx).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // close dialog
                dialog.cancel();
            }
        });
        alert.show();
    }
}