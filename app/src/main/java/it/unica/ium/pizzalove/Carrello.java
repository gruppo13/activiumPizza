package it.unica.ium.pizzalove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by perlo on 14/02/16.
 */
public class Carrello extends AppCompatActivity{
    HashMap<String, Integer> listingredienti;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrello);

        /*aggiornamento Pizza Creata */
        Bundle b = getIntent().getExtras();
        listingredienti = new HashMap<String,Integer>();
        listingredienti = Pizza.resetIngredienti();
        for (String ingrediente : listingredienti.keySet())
            listingredienti.put(ingrediente,b.getInt(ingrediente));



        expListView = (ExpandableListView) findViewById(R.id.carrello);

        // preparing list data

        prepareListData();

        listAdapter = new ExpandableList(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        // expListView.setOnGroupExpandListener((OnGroupExpandListener) clickGroup);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                System.out.println("selected child");

                return true;
            }
        });




    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("La Tua Prima Creazione");

        // Adding child data
        List<String>  creazione= new ArrayList<String>();
        for (String ingrediente : listingredienti.keySet())
            if (listingredienti.get(ingrediente)>0){
                creazione.add(ingrediente);
            }
        listDataChild.put(listDataHeader.get(0), creazione); // Header, Child data

    }
}
