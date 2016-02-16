package it.unica.ium.pizzalove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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


        /*
*/
        List<ListaPizza> elenco = new ArrayList<>();

        if (bundle.getStringArrayList("classica")!= null) {
            listpizze = bundle.getStringArrayList("classica");

            for (String pizza : listpizze) {
                if (Pizza.containPizza(elenco,pizza)==-1){
                   elenco.add(new ListaPizza(ListaPizza.getClassicaS(pizza),1));
                }
                else
                    elenco.get(Pizza.containPizza(elenco,pizza)).addCount();
            }





        }
        if (bundle.getInt("creata")>0) {
           // listingredienti = bundle.getInt("creata");
            for(int i=0; i<bundle.getInt("creata");i++) {

                listingredienti = Pizza.resetIngredienti();
                for (ListaIngrediente ingrediente : listingredienti) {
                    listingredienti.get(Pizza.trovaIngrediente(listingredienti,
                            ingrediente.getStringNome())).setIngrediente
                            (bundle.getIntegerArrayList(ingrediente.getStringNome()).get(i));
                    System.out.println(ingrediente.getStringNome() + "count ->" + ingrediente.getCount()
                            + "pos -> " + Pizza.trovaIngrediente(listingredienti, ingrediente.getStringNome()));
                }

                elenco.add(new ListaPizza(listingredienti));



            }

            //System.out.println("sssss "+ elenco.get(1).getIngredienti().get(0).getCount()+ " ...");
          //  System.out.println(elenco.get(0).getIngredienti().get(0).getCount());


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


        TextView totale = (TextView) findViewById(R.id.txtTotale);
        totale.setText(Pizza.totalePrezzo(elenco));
        Pizza.printAll(elenco);



        // expListView.setOnGroupExpandListener((OnGroupExpandListener) clickGroup);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                System.out.println("selected child");
                doPopup(v);
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


    private void doPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.popup_one:
                                Toast.makeText(Carrello.this, "Popup item" +
                                        "one selected",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;

                        }


                    }
                }

        );

        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_popup,popupMenu.getMenu());
        popupMenu.show();

        }




}
