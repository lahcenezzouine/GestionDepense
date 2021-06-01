package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.ui.depenses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.R;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.dao.DepenseDAO;

public class DepenseFragment extends Fragment {

    DepenseAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_depenses, container, false);
        ListView list = view.findViewById(R.id.lv_depenses);
        DepenseDAO depenseDAO = new DepenseDAO(getContext());

        adapter = new DepenseAdapter(getContext(), depenseDAO.getAllDepenses());
        list.setAdapter(adapter);

        return view;
    }
}