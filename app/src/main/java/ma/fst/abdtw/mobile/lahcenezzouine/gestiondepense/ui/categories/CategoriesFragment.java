package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.ui.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.R;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.dao.CategorieDAO;

public class CategoriesFragment extends Fragment implements View.OnClickListener {

    CategorieAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ListView list = view.findViewById(R.id.lv_categories);
        CategorieDAO categorieDAO = new CategorieDAO(getContext());

        adapter = new CategorieAdapter(getContext(), categorieDAO.getAllCategories());
        list.setAdapter(adapter);

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