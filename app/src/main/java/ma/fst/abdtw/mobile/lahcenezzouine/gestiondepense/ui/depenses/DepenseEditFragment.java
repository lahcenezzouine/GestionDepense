package ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.ui.depenses;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.fragment.app.Fragment;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.R;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.dao.CategorieDAO;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.dao.DepenseDAO;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.models.Categorie;
import ma.fst.abdtw.mobile.lahcenezzouine.gestiondepense.models.Depense;

public class DepenseEditFragment extends Fragment {

    int selectedCategoryId;
    int selectedDeoenseId;

    EditText txt_descriptionDepense;
    EditText txt_montant;
    EditText txt_dateDepense;
    Button btn_add, btn_cancel;
    DatePickerDialog datePickerDialog;
    Spinner spinner_categories;

    DepenseDAO depenseDAO;
    CategorieDAO categorieDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);

        // Setup DAOs
        depenseDAO = new DepenseDAO(getContext());
        categorieDAO = new CategorieDAO(getContext());

        // Set ui elements
        View view = inflater.inflate(R.layout.depense_ajout, container, false);
        txt_descriptionDepense = view.findViewById(R.id.txt_descriptionDepense);
        txt_montant = view.findViewById((R.id.txt_montant));
        btn_add = view.findViewById((R.id.btn_add));
        btn_cancel = view.findViewById((R.id.btn_cancel));
        spinner_categories = view.findViewById((R.id.spinner_categories));
        setCategoriesSpinner();

        // perform click event on datePicker edit text
        txt_dateDepense = (EditText) view.findViewById((R.id.txt_dateDepense));
        txt_dateDepense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date, month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                txt_dateDepense.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        // Get data and fill the fields
        Bundle args = getArguments();
        Depense d = (Depense) args.getSerializable(Depense.serialVersionUID);
        if (d != null) {
            selectedCategoryId = d.getIdDep();
            txt_descriptionDepense.setText(d.getDepense());
            txt_montant.setText(String.valueOf(d.getMontant()));
            txt_dateDepense.setText(String.valueOf(d.getDateDep()));
//            spinner_categories.setSelection(d.getIdCat());
        }

        // perform click event on add button
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depenseDAO.updateDepense(
                        new Depense(
                                selectedCategoryId,
                                txt_descriptionDepense.getText().toString(),
                                txt_dateDepense.getText().toString(),
                                selectedCategoryId,
                                Float.parseFloat(txt_montant.getText().toString())));

                Toast.makeText(getActivity(), "Mis á jour avec succes", Toast.LENGTH_SHORT).show();
                backToList();
            }
        });

        // perform click event on cancel button
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToList();
            }
        });

        // perfrom select event on category spinner
        spinner_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Categorie cat = (Categorie) parent.getSelectedItem();
                selectedCategoryId = cat.getIdcat();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }

    private void setCategoriesSpinner() {
        ArrayList<Categorie> categories = categorieDAO.getAllCategories();

        // Fill data in spinner
        ArrayAdapter<Categorie> adapter = new ArrayAdapter<Categorie>(getContext(), R.layout.support_simple_spinner_dropdown_item, categories);
        spinner_categories.setAdapter(adapter);
    }

    private void backToList() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, new DepenseFragment())
                .addToBackStack(null)
                .commit();
    }
}