package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Carrello extends Activity {

    public static final String TOTALE = "it.unica.ium.pizzalove.TOTALE";
    public static final String PIZZA_MODIFICA = "it.unica.ium.pizzalove.PIZZA_MODIFICA";
    public static final String ELENCO_PIZZE = "it.unica.ium.pizzalove.ELENCO_PIZZE";
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private ArrayList<Pizza> elenco  = new ArrayList<>();
    private Bundle bundle;
    private float totale;

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putParcelableArrayList(ELENCO_PIZZE, elenco);
        saveInstanceState.putFloat(TOTALE, totale);
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

        if(!elenco.isEmpty()) {
            aggiornaTotale();
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
    }

    private void aggiornaTotale() {
        totale = 0.f;
        for (Pizza p : elenco){
            float sub = 3.f;
            for (Ingredienti i : p.getIngredienti())
                sub += i.getPrice();
            sub *= p.getCount();
            totale += sub;
        }
        ((TextView)findViewById(R.id.totaleCarrello)).setText(Pizza.formatoPrezzo(totale));
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
        aggiornaTotale();
    }

    public void addPizzaCarrello(int grpPos){
        elenco.get(grpPos).addCount();
        listAdapter = new ExpandableList(Carrello.this, elenco);
        expListView.setAdapter(listAdapter);
        aggiornaTotale();
    }
}
