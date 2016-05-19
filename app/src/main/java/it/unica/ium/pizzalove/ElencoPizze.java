package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ElencoPizze extends Activity {
    /*list view */
    public static final String NUOVA_PIZZA = "it.unica.ium.pizzalove.NUOVA_PIZZA";
    List<Pizza> elenco = new ArrayList<>();
    private ExpandableListView expListView;
    Bundle bundle;
    private GoogleApiClient client;

    /* menu pizze */
    List<Pizza> listaPizzeClassiche = Arrays.asList(new Pizza("Margherita"),
            new Pizza("Napoli"),new Pizza("Wurstel e Cipolle"),
            new Pizza("Funghi"),new Pizza("Cotto"),
            new Pizza("Prosciutto e Funghi"),new Pizza("Vegetariana"),
            new Pizza("Parmigiana"),new Pizza("Capricciosa"),
            new Pizza("Carbonara"),new Pizza("All American"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elenco_pizze);
        ExpandableListAdapter listAdapter;
        bundle = getIntent().getExtras();

        if(bundle.keySet().contains(Carrello.ELENCO_PIZZE))
            elenco = (List<Pizza>)bundle.getSerializable(Carrello.ELENCO_PIZZE);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableList);

        Button btn2 =  (Button)findViewById(R.id.btnModificaClassica);
        Button btn1 = (Button)findViewById(R.id.btnAgggiungiClassica);
        final int[] lastExpandedPosition = {-1, -1}; // ultima posizione aperta, ultima posizione chiusa


        listAdapter = new ExpandableList(this, listaPizzeClassiche);
        expListView.setAdapter(listAdapter);




        expListView.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return false;
                    }
                }
        );



        expListView.setOnGroupClickListener(new OnGroupClickListener() {
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
                                    lastExpandedPosition[0]=-1;
                                    lastExpandedPosition[1]=-1;
                                    expListView.collapseGroup(groupPosition);
                                } else {
                                    if (lastExpandedPosition[0] != -1){
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
        /**
         * Evento sul pulsante per la modifica di una pizza classica selezionata dall'elenco delle pizze
         */
            btn2.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastExpandedPosition[0] >= 0) {
                        //salva la pizza nel bundle
                        bundle.putSerializable(Carrello.PIZZA_MODIFICA, listaPizzeClassiche.get(lastExpandedPosition[1]));
                        startActivityForResult(new Intent(ElencoPizze.this, CreaPizza.class).putExtras(bundle), 0);
                    }
                    else {
                        Toast.makeText(ElencoPizze.this,"Non hai selezionato nessuna pizza",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        /**
         * Evento sul pulsante che aggiunge al carrello
         */
            btn1.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastExpandedPosition[0] >= 0) {
                        if(elenco.contains(listaPizzeClassiche.get(lastExpandedPosition[1])))
                            elenco.get(elenco.indexOf(listaPizzeClassiche.get(lastExpandedPosition[1]))).addCount();
                        else
                            elenco.add(listaPizzeClassiche.get(lastExpandedPosition[1]));
                        bundle.putSerializable(Carrello.ELENCO_PIZZE, (Serializable)elenco);
                        startActivityForResult(new Intent(ElencoPizze.this, Carrello.class).putExtras(bundle), 0);
                    }
                    else
                        Toast.makeText(ElencoPizze.this,"Non hai selezionato nessuna pizza",Toast.LENGTH_SHORT).show();
                }
            });
        client=new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ElencoPizze Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://it.unica.ium.pizzalove/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ElencoPizze Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://it.unica.ium.pizzalove/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}