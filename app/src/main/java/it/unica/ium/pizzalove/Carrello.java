package it.unica.ium.pizzalove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrello);

        bundle = getIntent().getExtras();
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
            for(int i=0; i<bundle.getInt("creata");i++) {

                listingredienti = Pizza.resetIngredienti();
                for (ListaIngrediente ingrediente : listingredienti) {
                    listingredienti.get(Pizza.trovaIngrediente(listingredienti,
                            ingrediente.getStringNome())).setIngrediente
                            (bundle.getIntegerArrayList(ingrediente.getStringNome()).get(i));

                }

                elenco.add(new ListaPizza(listingredienti));



            }

        }
        listAdapter = new ExpandableList(this,elenco);
         expListView = (ExpandableListView) findViewById(R.id.carrello);

        expListView.setAdapter(listAdapter);

        TextView totale = (TextView) findViewById(R.id.txtTotale);
        totale.setText(Pizza.totalePrezzo(elenco));
        Pizza.printAll(elenco);

        Button btn = (Button) findViewById(R.id.btnCreaPizza);
        Button btn2 = (Button) findViewById(R.id.btnCreaElenco);
        //aggiungi una pizza da creare
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrello.this, CreaPizza.class);
                // b.putStringArrayList("lista", new ArrayList<String>(listingredienti.keySet()));
                bundle.putString("aggiunte",null);
                intent.putExtras(bundle);
                onResume();
                startActivityForResult(intent, 0);
            }
        });
//aggiungi una pizza dal menu
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrello.this, ElencoPizze.class);
                // b.putStringArrayList("lista", new ArrayList<String>(listingredienti.keySet()));
                bundle.putString("aggiunte",null);
                intent.putExtras(bundle);
                onResume();
                startActivityForResult(intent, 0);
            }
        });






    }




}
