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



    List<String> listpizze;
    List<ListaIngrediente> listingredienti;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    Bundle bundle;


   // HashMap<PizzaClassica,List<Pizza.Ingrediente>>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrello);

        /*aggiornamento Pizza Creata */
        bundle = getIntent().getExtras();
       // listingredienti = new HashMap<String,Integer>();
        listingredienti = Pizza.resetIngredienti();

        /*
*/
        List<ListaPizza> elenco = new ArrayList<>();

        if (bundle.getStringArrayList("classica")!= null) {
            listpizze = bundle.getStringArrayList("classica");

            for (String pizza : listpizze) {
                   elenco.add(new ListaPizza(ListaPizza.getClassicaS(pizza)));
            }





        }
        if (bundle.getInt("creata")>0) {
           // listingredienti = bundle.getInt("creata");
            for(int i=0; i<bundle.getInt("creata");i++) {
                for (ListaIngrediente ingrediente : listingredienti) {
                    listingredienti.get(Pizza.trovaIngrediente(listingredienti,
                            ingrediente.getStringNome())).setIngrediente
                            (bundle.getIntegerArrayList(ingrediente.getStringNome()).get(i));
                    System.out.println(ingrediente.getStringNome() + "count ->" + ingrediente.getCount()
                            + "pos -> " + Pizza.trovaIngrediente(listingredienti, ingrediente.getStringNome()));
                }
                elenco.add(new ListaPizza(listingredienti));


            }

        }
        listAdapter = new ExpandableList(this,elenco);
       /* else {
            for (String ingrediente : listingredienti.keySet())
                listingredienti.put(ingrediente,bundle.getInt(ingrediente));

            //listAdapter = new ExpandableList(this, Pizza.getPizzaCreata(listingredienti,Pizza.Classica.Creata));




        }*/
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
