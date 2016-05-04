package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by perlo on 14/02/16.
 */
public class Carrello extends Activity {

    private List<String> listaPizze;
    private List<Ingredienti> listIngredienti;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<Pizza> elenco = new ArrayList<>();
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrello);
        bundle = getIntent().getExtras();

        for(String key : bundle.keySet()){
            if(bundle.get(key) instanceof ArrayList) {
                ArrayList temp = (ArrayList) bundle.get(key);
                assert temp != null;
                for(Object value : temp)
                    Log.e(key, value.toString());
            }
        }

        elenco = (List<Pizza>)bundle.getSerializable(Bundles.ELENCO_PIZZE.getBundle());
        listAdapter = new ExpandableList(this, elenco);
        expListView = (ExpandableListView) findViewById(R.id.carrello);
        FloatingActionButton btnNuovaPizza = (FloatingActionButton) findViewById(R.id.btnNewPizza);


        /**
         * bottone nuova pizza
         */
        btnNuovaPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrello.this, CreaPizza.class);
                bundle.putSerializable(Bundles.ELENCO_PIZZE.getBundle(), (Serializable)elenco);
                intent.putExtras(bundle);
                onResume();
                startActivityForResult(intent, 0);
            }
        });

        expListView.setAdapter(listAdapter);


        /* solo una pizza si puo espandere*/
        final int[] lastExpandedPosition = {-1, -1}; // ultima posizione aperta, ultima posizione chiusa

        /// fa espandere solo un gruppo per volta
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                                                @Override
                                                public boolean onGroupClick(ExpandableListView parent, View v, final int groupPosition, long id) {
                                                    // cambia attivita
                                                    //setContentView(R.layout.activity_creapizza);
                                                    expListView.collapseGroup(groupPosition);
                                                    //cambia la quantita delle pizze selezionate
                                                    if (expListView.isGroupExpanded(groupPosition)) {
                                                        //vai al carrello doppio click
                                                        if (lastExpandedPosition[1] == groupPosition) {
                                                            System.out.println("group clicked 1");

                                                        } else {
                                                            System.out.println("group clicked 2");
                                                            expListView.collapseGroup(groupPosition);
                                                        }


                                                    } else {//espandi lista
/*  */

                                                        System.out.println("group clicked 3");


                                                        lastExpandedPosition[1] = groupPosition;
                                                        if (lastExpandedPosition[0] == groupPosition) {
                                                            System.out.println("same expanded group");
                                                            lastExpandedPosition[0] = -1;
                                                            lastExpandedPosition[1] = -1;
                                                            expListView.collapseGroup(groupPosition);
                                                        } else {
                                                            if (lastExpandedPosition[0] != -1) {
                                                                expListView.collapseGroup(lastExpandedPosition[0]);
                                                            }
                                                            lastExpandedPosition[0] = groupPosition;

                                                            expListView.expandGroup(groupPosition);
                                                        }
                                                    }
                                                    return true;
                                                }
                                            }
            );

        }


    private Float contaTotale() {
        Float tot = 0.0f;
        for(Pizza p : elenco){
            tot += p.getPrezzo()*p.getCount();
        }
        return tot;
    }

    public void modificaPizzaCarrello(int grpPos){
        Pizza pizzaModifica = elenco.get(grpPos);
        elenco.remove(grpPos);
        if(elenco.size() == 0)
            bundle.putSerializable(Bundles.ELENCO_PIZZE.getBundle(), null);
        bundle.putSerializable(Bundles.MODIFICA_PIZZA.getBundle(), (Serializable) pizzaModifica.getIngredienti());
        Intent intent = new Intent(Carrello.this, CreaPizza.class);
        intent.putExtras(bundle);
        onResume();
        startActivityForResult(intent, 0);
    }


    public void removePizzaCarrello(int grpPos){

        elenco.remove(grpPos);
        if (elenco.size() == 0) {
            bundle.putSerializable(Bundles.ELENCO_PIZZE.getBundle(), null);
            Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();
            onResume();
            startActivityForResult(new Intent(Carrello.this, Scelta.class).putExtras(bundle), 0);
        }
        Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();
        listAdapter = new ExpandableList(Carrello.this, elenco);
        expListView.setAdapter(listAdapter);
    }
}
