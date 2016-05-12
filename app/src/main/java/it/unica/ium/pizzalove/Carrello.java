package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<Pizza> elenco;
    private Pizza pizzaModifica = null;


    @Override
    protected void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        if(elenco.size() == 0)
            state.putSerializable(Bundles.ELENCO_PIZZE.getBundle(), null);
        else
            state.putSerializable(Bundles.ELENCO_PIZZE.getBundle(), (Serializable) elenco);
        if(pizzaModifica != null)
            state.putSerializable(Bundles.MODIFICA_PIZZA.getBundle(), (Serializable) pizzaModifica.getIngredienti());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        elenco = (List<Pizza>)savedInstanceState.getSerializable(Bundles.ELENCO_PIZZE.getBundle());
        Log.e("elenco", "caricato");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(savedInstanceState.getSerializable(Bundles.ELENCO_PIZZE.getBundle()) != null)
            elenco = (List<Pizza>)savedInstanceState.getSerializable(Bundles.ELENCO_PIZZE.getBundle());
        setContentView(R.layout.activity_carrello);

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
                onSaveInstanceState(new Bundle());
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
        pizzaModifica = elenco.get(grpPos);
        elenco.remove(grpPos);
        onSaveInstanceState(new Bundle());
        onResume();
        startActivityForResult(new Intent(Carrello.this, CreaPizza.class), 0);
    }


    public void removePizzaCarrello(int grpPos){

        elenco.remove(grpPos);
        if (elenco.size() == 0) {
            onSaveInstanceState(new Bundle());
            Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();
            onResume();
            startActivityForResult(new Intent(Carrello.this, Scelta.class), 0);
        }
        Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();
        listAdapter = new ExpandableList(Carrello.this, elenco);
        expListView.setAdapter(listAdapter);
    }
}
