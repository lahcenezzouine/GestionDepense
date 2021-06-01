package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.ui.categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.R;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.dao.CategorieDAO;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.models.Categorie;

public class CategorieDetailsFragment extends Fragment implements View.OnClickListener {

    CategorieAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.categorie_details, container, false);
        EditText txt_nomCategorie = view.findViewById(R.id.txt_nomCategorie);

        Bundle args = getArguments();
        Categorie c = (Categorie) args.getSerializable(Categorie.serialVersionUID);
        if (c != null) {
            txt_nomCategorie.setText(c.getNom());
        }

        return view;
    }

    public void refreshData() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
    }
}