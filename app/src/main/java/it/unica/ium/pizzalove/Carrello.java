package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;

public class Carrello extends Activity {

    public static final String PIZZA_MODIFICA = "it.unica.ium.pizzalove.PIZZA_MODIFICA";
    public static final String ELENCO_PIZZE = "it.unica.ium.pizzalove.ELENCO_PIZZE";
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private ArrayList<Pizza> elenco  = new ArrayList<>();
    Bundle bundle;

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putParcelableArrayList(ELENCO_PIZZE, elenco);
        saveInstanceState.putAll(bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrello);
        bundle = getIntent().getExtras();

        if(savedInstanceState != null)
            bundle = savedInstanceState;

        if(bundle.keySet().contains(Carrello.ELENCO_PIZZE)){
            elenco = bundle.getParcelableArrayList(ELENCO_PIZZE);
        }

        listAdapter = new ExpandableList(this, elenco);
        expListView = (ExpandableListView) findViewById(R.id.carrello);
        FloatingActionButton btnNuovaPizza = (FloatingActionButton) findViewById(R.id.btnNewPizza);


        /**
         * bottone nuova pizza
         */
        btnNuovaPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putParcelableArrayList(ELENCO_PIZZE, elenco);
                startActivityForResult(new Intent(Carrello.this, Scelta.class).putExtras(bundle), 0);
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

    public void modificaPizzaCarrello(int grpPos){
        bundle.putParcelable(PIZZA_MODIFICA, elenco.get(grpPos));
        if(elenco.get(grpPos).getCount() == 1)
            elenco.remove(grpPos);
        else
            elenco.get(grpPos).lessCount();
        bundle.putParcelableArrayList(ELENCO_PIZZE, elenco);
        startActivityForResult(new Intent(Carrello.this, CreaPizza.class).putExtras(bundle), 0);
    }


    public void removePizzaCarrello(int grpPos){
        if(elenco.get(grpPos).getCount() == 1)
            elenco.remove(grpPos);
        else
            elenco.get(grpPos).lessCount();
        if (elenco.size() == 0) {
            Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();
            startActivityForResult(new Intent(Carrello.this, Scelta.class).putExtras(bundle), 0);
        }
        Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();
        listAdapter = new ExpandableList(Carrello.this, elenco);
        expListView.setAdapter(listAdapter);
    }
}
