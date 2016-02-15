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


    List<String> listpizze;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrello);

        /*aggiornamento Pizza Creata */
        bundle = getIntent().getExtras();
        listingredienti = new HashMap<String,Integer>();
        listingredienti = Pizza.resetIngredienti();

        /*
*/
        HashMap<Pizza.Classica, List<Pizza.Ingrediente>> elenco = new HashMap<>();

        if (bundle.getStringArrayList("classica")!= null) {
            listpizze = bundle.getStringArrayList("classica");

            for (String pizza : listpizze) {
                //HashMap<String, Integer> ingredienti = new HashMap<>();
                /*for (Pizza.Ingrediente ingrediente : Pizza.getIngredientiClassica(Pizza.getClassicaS(pizza))) {
                    listingredienti.put(Pizza.getIngrediente(ingrediente), 1);
                }*/

                elenco.put(Pizza.getClassicaS(pizza),Pizza.getIngredientiClassica(Pizza.getClassicaS(pizza)));

            }

            listAdapter = new ExpandableList(this,elenco);



        }else {
            for (String ingrediente : listingredienti.keySet())
                listingredienti.put(ingrediente,bundle.getInt(ingrediente));

            listAdapter = new ExpandableList(this, Pizza.getPizzaCreata(listingredienti,Pizza.Classica.Creata));




        }
        expListView = (ExpandableListView) findViewById(R.id.carrello);

        // preparing list data


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

        Button btn = (Button) findViewById(R.id.btnAggiungi);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrello.this, Scelta.class);

                // b.putStringArrayList("lista", new ArrayList<String>(listingredienti.keySet()));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });


    }


}
