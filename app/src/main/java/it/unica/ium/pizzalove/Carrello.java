package it.unica.ium.pizzalove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perlo on 14/02/16.
 */
public class Carrello extends AppCompatActivity{
    int deletePosition = -1;


    List<String> listpizze;
    List<Ingredienti> listingredienti;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<Pizza> elenco = new ArrayList<>();

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrello);

        bundle = getIntent().getExtras();

        if (bundle.getStringArrayList("classica")!= null) {
            listpizze = bundle.getStringArrayList("classica");

            for (String pizza : listpizze) {
                if (Pizza.containPizza(elenco, pizza) == -1)
                    elenco.add(new Pizza(pizza));
                elenco.get(Pizza.containPizza(elenco,pizza)).addCount();
        }
        if (bundle.getInt("creata") > 0) {
            for(int i=0; i < bundle.getInt("creata"); i++) {
                for(String nome : bundle.getStringArrayList(String.valueOf(i+1))){
                    listingredienti.get((Pizza.trovaIngrediente(listingredienti,nome))).addIngrediente();

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

        // expListView.setOnCreateContextMenuListener(optionPizzeCarrello);
        expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deletePosition=position;
                doPopup(view);

//                Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();

                return true;
            }
        });


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



    // flag true -> elimina pizza
    // flag false ->modifica pizza
    private void removePizzaCarrello(boolean flag){

        String nomePizza = elenco.get(deletePosition).getStringNome();

        elenco.remove(deletePosition);

        int creata =0;
        if (elenco.size() == 0) {

            bundle.putStringArrayList("classica", null);
            bundle.putInt("creata", 0);
            Intent intent;
            if (flag==true) {
                intent= new Intent(Carrello.this, Scelta.class);
                Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();
            }else{//torna a scelta perche' il carrello e' vuoto
                if (ListaPizza.getClassicaS(nomePizza)== ListaPizza.Classica.Creata){

                    ArrayList<String> ingredienti = new ArrayList<String>();
                    for (ListaIngrediente ingrediente : listingredienti){
                        if (ingrediente.getCount()>0)
                            ingredienti.add(ingrediente.getStringNome());
                    }
                    Log.d("ingredienti","inseriti");
                    bundle.putStringArrayList("aggiunteCreata",ingredienti);

                }

                intent = new Intent(Carrello.this, CreaPizza.class);
                bundle.putString("aggiunte", nomePizza);
            }


            intent.putExtras(bundle);
            onResume();
            startActivityForResult(intent, 0);

        } else {// le pizze non sono finite

            //aggiorna elenco pizze
            ArrayList<String> nomiPizze = new ArrayList<>();
            ArrayList<ListaPizza> pizzeCreate = new ArrayList<>();
            for (ListaPizza pizza : elenco) {
                if (!pizza.getStringNome().equals("not valid")) {
                    nomiPizze.add(pizza.getStringNome());
                } else {// aggiorna le pizze create dall utente
                    ArrayList<String> ingredienti = new ArrayList<>();
                    for (ListaIngrediente ingrediente : pizza.getIngredienti()){
                        ingredienti.add(ingrediente.getStringNome());
                    }

                    bundle.putStringArrayList(String.valueOf(creata), ingredienti);
                    creata++;
                }

            }



            bundle.putStringArrayList("classica", null);

            bundle.putInt("creata", creata);
            if (nomiPizze.size()>0)
                bundle.putStringArrayList("classica", nomiPizze);


            if (flag==false){
                Intent intent = new Intent(Carrello.this, CreaPizza.class);

                bundle.putString("aggiunte", nomePizza);
                if (ListaPizza.getClassicaS(nomePizza)== ListaPizza.Classica.Creata){

                    ArrayList<String> ingredienti = new ArrayList<String>();
                    for (ListaIngrediente ingrediente : listingredienti){
                        if (ingrediente.getCount()>0)
                            ingredienti.add(ingrediente.getStringNome());
                    }
                    bundle.putStringArrayList("aggiunteCreata",ingredienti);

                }
                intent.putExtras(bundle);
                onResume();
                startActivityForResult(intent, 0);
            }
            else{
                ((TextView) findViewById(R.id.txtTotale)).setText(Pizza.totalePrezzo(elenco));
                Toast.makeText(Carrello.this, "Hai rimosso una pizza", Toast.LENGTH_SHORT).show();
                listAdapter = new ExpandableList(Carrello.this, elenco);
                expListView.setAdapter(listAdapter);
            }

        }


    }

    private void doPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.popup_one:
                                removePizzaCarrello(true);
                                return true;
                            case R.id.popup_two:
                                removePizzaCarrello(false);
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
