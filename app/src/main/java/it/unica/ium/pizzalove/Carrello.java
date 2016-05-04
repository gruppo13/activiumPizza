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

    private int deletePosition = -1;

    private List<String> listaPizze;
    private List<Ingredienti> listIngredienti;

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<Pizza> elenco = new ArrayList<>();
    public static final String ELENCO_PIZZE = "it.unica.ium.pizzalove.ELENCO_PIZZE";
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrello);
        Float tot = 0.0f;
        bundle = getIntent().getExtras();

        for(String key : bundle.keySet()){
            if(bundle.get(key) instanceof ArrayList) {
                ArrayList temp = (ArrayList) bundle.get(key);
                for(Object value : temp)
                    Log.e(key, value.toString());
            }
        }

        if(bundle.getStringArrayList(ElencoPizze.CLASSICA)!= null) {
            listaPizze = bundle.getStringArrayList(ElencoPizze.CLASSICA);
            for (String pizza : listaPizze) {
                if (Pizza.containPizza(elenco, pizza) == -1)
                    elenco.add(new Pizza(pizza));
                elenco.get(Pizza.containPizza(elenco, pizza)).addCount();
            }
        }
            if (bundle.getInt(CreaPizza.NUOVA_PIZZA) > 0) {
                for (int i = 0; i < bundle.getInt(CreaPizza.NUOVA_PIZZA); i++) {
                    listIngredienti = new ArrayList<>();
                    for (String nome : bundle.getStringArrayList(String.valueOf(i + 1))) {
                        listIngredienti.add(Ingredienti.valueOf(nome));
                    }
                    elenco.add(new Pizza(listIngredienti));
                    elenco.get(elenco.size()-1).addCount();
                }

            }
            listAdapter = new ExpandableList(this, elenco);
            expListView = (ExpandableListView) findViewById(R.id.carrello);
        FloatingActionButton btnNuovaPizza = (FloatingActionButton) findViewById(R.id.btnNewPizza);

        btnNuovaPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrello.this, CreaPizza.class);
                bundle.putString(CreaPizza.MODIFICA_PIZZA, null);
                intent.putExtras(bundle);
                onResume();
                startActivityForResult(intent, 0);
            }
        });

            expListView.setAdapter(listAdapter);

            //TextView totale = (TextView) findViewById(R.id.txtTotale);
            tot = contaTotale();

            //totale.setText(Pizza.formatoPrezzo(tot));

            // expListView.setOnCreateContextMenuListener(optionPizzeCarrello);
            expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    deletePosition = position;
                    doPopup(view);

//                Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();

                    return true;
                }
            });


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

            /**Button btn = (Button) findViewById(R.id.btnCreaPizza);
            Button btn2 = (Button) findViewById(R.id.btnCreaElenco);
            //aggiungi una pizza da creare
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Carrello.this, CreaPizza.class);
                    // b.putStringArrayList("lista", new ArrayList<String>(listIngredienti.keySet()));
                    bundle.putString(CreaPizza.MODIFICA_PIZZA, null);
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
                    // b.putStringArrayList("lista", new ArrayList<String>(listIngredienti.keySet()));
                    bundle.putString(CreaPizza.MODIFICA_PIZZA, null);
                    intent.putExtras(bundle);
                    onResume();
                    startActivityForResult(intent, 0);
                }
            });*/

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
        int creata = 0;
        Intent intent;
        if(elenco.size() == 0){
            bundle.putStringArrayList(ElencoPizze.CLASSICA, null);
            bundle.putInt(CreaPizza.NUOVA_PIZZA, 0);
            bundle.putSerializable(CreaPizza.MODIFICA_PIZZA, (Serializable) pizzaModifica.getIngredienti());
            intent = new Intent(Carrello.this, CreaPizza.class);
        }
        else{
            ArrayList<String> nomiPizze = new ArrayList<>();
            for (Pizza pizza : elenco) {
                if (!pizza.getNomePizza().equals("creata")) {//aggiorna le pizze classiche presenti nel carrello da inserire nel bundle
                    for(int i = 0; i < pizza.getCount(); i++)
                        nomiPizze.add(pizza.getNomePizza());
                } else {// aggiorna le pizze create dall utente da inserire nel bundle
                    ArrayList<String> ingredienti = new ArrayList<>();
                    for (Ingredienti ingrediente : pizza.getIngredienti()){
                        ingredienti.add(ingrediente.toString());
                    }
                    creata++;
                    bundle.putStringArrayList(String.valueOf(creata), ingredienti);
                }
            }
            //bundle.putSerializable(CreaPizza.MODIFICA_PIZZA, );

            bundle.putInt(CreaPizza.NUOVA_PIZZA, creata);
            if (nomiPizze.size() > 0)
                bundle.putStringArrayList(ElencoPizze.CLASSICA, nomiPizze);
            else
                bundle.putStringArrayList(ElencoPizze.CLASSICA, null);
            intent = new Intent(Carrello.this, CreaPizza.class);

            ArrayList<String> ingredienti = new ArrayList<>();
            for (Ingredienti ingrediente : pizzaModifica.getIngredienti()){
                ingredienti.add(ingrediente.toString());
            }
            bundle.putStringArrayList(CreaPizza.MODIFICA_PIZZA, ingredienti);
        }
        intent.putExtras(bundle);
        onResume();
        startActivityForResult(intent, 0);
    }
    /*removePizzaCarrello
    rimuove o modifica una pizza dal carrello

    // flag true -> elimina pizza
    // flag false ->modifica pizza
    */
    public void removePizzaCarrello(int grpPos){

       // String nomePizza = elenco.get(deletePosition).getNomePizza();

        elenco.remove(grpPos);
        if (elenco.size() == 0) {
            bundle.putStringArrayList(ElencoPizze.CLASSICA, null);
            bundle.putInt(CreaPizza.NUOVA_PIZZA, 0);
            Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();
            onResume();
            startActivityForResult(new Intent(Carrello.this, Scelta.class).putExtras(bundle), 0);

        } else {// le pizze non sono finite
            //((TextView) findViewById(R.id.txtTotale)).setText(Pizza.formatoPrezzo(contaTotale()));
            Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();
                listAdapter = new ExpandableList(Carrello.this, elenco);
                expListView.setAdapter(listAdapter);
        }
    }

    private void doPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.popDelete:
                                //removePizzaCarrello(true);
                                return true;
                            case R.id.popModifica:
                                //removePizzaCarrello(false);
                                return true;
                            default:
                                return false;

                        }


                    }
                }

        );

        MenuInflater inflater = popupMenu.getMenuInflater();

        inflater.inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.show();

    }
}
