package it.unica.ium.pizzalove;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;


public class ElencoPizze extends AppCompatActivity{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    private GoogleApiClient client;


    List<ListaPizza> listePizzeClassiche;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elenco_pizze);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableList);

        listePizzeClassiche = new ArrayList<>();
        listePizzeClassiche = Pizza.getPizzeClassiche();
        listAdapter = new ExpandableList(this,listePizzeClassiche);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        // expListView.setOnGroupExpandListener((OnGroupExpandListener) clickGroup);

        final int[] lastExpandedPosition = {-1, -1};

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
               /**/







                    if(expListView.isGroupExpanded(groupPosition)){
                        //vai al carrello doppio click
                        if (lastExpandedPosition[1] == groupPosition) {
                            // Log.i("group position", groupPosition);
                            //Log.i("group position", ((Pizza.PizzaClassica)listAdapter.getGroup(groupPosition));
/*
                            Intent intent = new Intent(ElencoPizze.this, Carrello.class);
                            Bundle b = getIntent().getExtras();

                            ArrayList<String> pizzeClassiche;

                            if (b.getStringArrayList("classica") != null)
                                pizzeClassiche = b.getStringArrayList("classica");
                            else
                                pizzeClassiche = new ArrayList<String>();

                            pizzeClassiche.add(Pizza.getPizzeClassiche().get(groupPosition).getStringNome());
                            b.putStringArrayList("classica", pizzeClassiche);
                            // b.putStringArrayList("lista", new ArrayList<String>(listingredienti.keySet()));
                            intent.putExtras(b);
                            startActivityForResult(intent, 0);
*/                          View view = listAdapter.getGroupView(groupPosition, false, v, null);
                            View viewButton = listAdapter.getChildView(groupPosition, listePizzeClassiche.get(groupPosition).getCount() - 1, true, v, null);


                           Button btn1 = (Button) viewButton.findViewById(R.id.btnModificaClassica);
                           Button btn2 = (Button) viewButton.findViewById(R.id.btnAgggiungiClassica);

                              btn1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      System.out.println("sei dentron on long");
                                      TextView quantita = (TextView) v.findViewById(R.id.lblListHeaderN);
                                      quantita.setText("1");


                                  }
                              });
                            btn2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(ElencoPizze.this, CreaPizza.class);
                                    Bundle b = getIntent().getExtras();

                                    ArrayList<String> pizzeClassiche;

                                    if (b.getStringArrayList("classica") != null)
                                        pizzeClassiche = b.getStringArrayList("classica");
                                    else
                                        pizzeClassiche = new ArrayList<String>();

                                    pizzeClassiche.add(Pizza.getPizzeClassiche().get(groupPosition).getStringNome());
                                    b.putStringArrayList("classica", pizzeClassiche);
                                    // b.putStringArrayList("lista", new ArrayList<String>(listingredienti.keySet()));
                                    intent.putExtras(b);
                                    startActivityForResult(intent, 0);



                                }
                            });

                        } else {
                            //  System.out.println("group clicked 2");
                            expListView.collapseGroup(groupPosition);
                        }


                    }

                    else

                    {//espandi lista
                      /*  */




                        lastExpandedPosition[1] = groupPosition;
                        if (lastExpandedPosition[0] == groupPosition) {
                            System.out.println("same expanded group");
                        } else {
                            if (lastExpandedPosition[0] != -1
                                    && groupPosition != lastExpandedPosition[0]) {
                                expListView.collapseGroup(lastExpandedPosition[0]);
                            }
                            lastExpandedPosition[0] = groupPosition;




                        }
                        expListView.expandGroup(groupPosition);

                    }


                   // Toast.makeText(ElencoPizze.this,"You selected"+String.valueOf(listAdapter.getGroupId(groupPosition)),Toast.LENGTH_SHORT).

                   // show();

                    return true;
                }


            }

            );

/*
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(final int groupPosition) {
                expListView.collapseGroup(groupPosition);

                //listePizzeClassiche.get(groupPosition).addCount();
                TextView quantita = (TextView) findViewById(R.id.lblListHeaderN);

                quantita.setText("prova");

                expListView.getHeaderViewsCount();

                //expListView.setAdapter(listAdapter2);
                // expListView.setItemChecked(0,true);
                expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        expListView.expandGroup(position);
                        System.out.println("itemm long" + position
                                + "liste " + expListView.getHeaderViewsCount());
                        return true;
                    }
                });


            }
        });*/

            client=new GoogleApiClient.Builder(this).

            addApi(AppIndex.API)

            .

            build();
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