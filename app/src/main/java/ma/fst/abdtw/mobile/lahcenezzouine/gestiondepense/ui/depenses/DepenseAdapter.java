package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.ui.depenses;

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
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.R;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.dao.DepenseDAO;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.models.Categorie;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.models.Depense;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.ui.categories.CategorieDetailsFragment;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.ui.categories.CategoriesFragment;

public class DepenseAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<Depense> depensesList;

    public DepenseAdapter(Context ctx, ArrayList<Depense> depenses) {
        this.ctx = ctx;
        this.depensesList = depenses;
    }

    @Override
    public int getCount() {
        return depensesList.size();
    }

    @Override
    public Depense getItem(int position) {
        return depensesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(ctx);

        DepenseDAO depenseDAO = new DepenseDAO(ctx);
        Depense d = getItem(position);

        convertView = inf.inflate(R.layout.depense_list_item, parent, false);

        TextView txt_descriptionDepense = convertView.findViewById(R.id.txt_descriptionDepense);
        TextView txt_montant = convertView.findViewById(R.id.txt_montant);
        TextView txt_dateDepense = convertView.findViewById(R.id.txt_dateDepense);
        TextView txt_categorie = convertView.findViewById(R.id.txt_categorie);

        txt_descriptionDepense.setText(d.getDepense());
        txt_montant.setText(String.valueOf(d.getMontant()) + " Dhs");
        txt_dateDepense.setText(String.valueOf(d.getDateDep()));
        txt_categorie.setText(d.getCategorie());

        // perform delete dispense event
        ImageButton btn_delete = convertView.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDesponseWithConfirmDialog(depenseDAO, d);
            }
        });

        // perform delete categorie event
        ImageButton btn_edit = convertView.findViewById(R.id.btn_view);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDepense(d);
            }
        });

        return convertView;
    }

    private void viewDepense(Depense depense) {
        Bundle args = new Bundle();
        args.putSerializable(Depense.serialVersionUID, depense);
        Fragment fragment = new DepenseEditFragment();
        fragment.setArguments(args);

        FragmentManager fragmentManager = ((AppCompatActivity) ctx).getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void deleteDesponseWithConfirmDialog(DepenseDAO depenseDAO, Depense d) {
        AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        alert.setTitle("Supprimer dépense");
        alert.setMessage("Etes-vous sûr que vous voulez supprimer?");
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                depenseDAO.deleteDepense(d.getIdDep());
                Toast.makeText(ctx.getApplicationContext(), "Supprimer avec succes", Toast.LENGTH_SHORT).show();

                // reload categoriesListFragment
                Fragment fragment = new DepenseFragment();
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
