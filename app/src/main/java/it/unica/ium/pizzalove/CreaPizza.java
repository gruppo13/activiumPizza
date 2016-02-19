package it.unica.ium.pizzalove;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perlo on 13/02/16.
 */
public class CreaPizza extends AppCompatActivity {
    int countIngredienti;

    List<ListaIngrediente> listingredienti;

    LayerDrawable layerDrawable;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<ListaPizza> elenco = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();

        List<String> listpizze;
        if (bundle.getStringArrayList("classica")!= null) {
            listpizze = bundle.getStringArrayList("classica");
/*
            for (String pizza : listpizze) {
                if (Pizza.containPizza(elenco,pizza)==-1){
                    elenco.add(new ListaPizza(ListaPizza.getClassicaS(pizza),1));
                }
                else
                    elenco.get(Pizza.containPizza(elenco,pizza)).addCount();
            }*/
        }


        setContentView(R.layout.activity_creapizza);

        this.countIngredienti = 0;
        listingredienti = new ArrayList<>();
        listingredienti = Pizza.resetIngredienti();
        updatePizza(null);

        Button btnAddPizzaCreate = (Button) findViewById(R.id.btnAddPizzaCreate);

        btnAddPizzaCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreaPizza.this, Carrello.class);



                //ArrayList<ArrayList<Integer>>
                for (ListaIngrediente ingrediente : listingredienti) {

                    ArrayList<Integer> ingredientiCreata;
                    if (b.getStringArrayList(ingrediente.getStringNome()) != null)
                        ingredientiCreata= b.getIntegerArrayList(ingrediente.getStringNome());
                    else
                        ingredientiCreata = new ArrayList<Integer>();
                    ingredientiCreata.add(ingrediente.getCount());
                    b.putIntegerArrayList(ingrediente.getStringNome(), ingredientiCreata);

                }

                if (b.getInt("creata")>0)
                         b.putInt("creata", b.getInt("creata")+1);
                else
                  b.putInt("creata",1);
               // b.putStringArrayList("lista", new ArrayList<String>(listingredienti.keySet()));
                intent.putExtras(b);


                startActivityForResult(intent, 0);

            }
        });

        // immagini da trascinare
        findViewById(R.id.image).setOnLongClickListener(longListener);
        findViewById(R.id.image1).setOnLongClickListener(longListener);
        findViewById(R.id.image2).setOnLongClickListener(longListener);
        findViewById(R.id.image3).setOnLongClickListener(longListener);
        findViewById(R.id.image4).setOnLongClickListener(longListener);

        //immagini da click
        findViewById(R.id.image).setOnClickListener(clickListener);
        findViewById(R.id.image1).setOnClickListener(clickListener);
        findViewById(R.id.image2).setOnClickListener(clickListener);
        findViewById(R.id.image3).setOnClickListener(clickListener);
        findViewById(R.id.image4).setOnClickListener(clickListener);


        //immagini da modificare
        findViewById(R.id.imageMain).setOnDragListener(dropListener);

      /*  findViewById(R.id.imageMain).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                doPopup(v);
                return true;

            }
        });*/

        ImageView imageViewcontextMenu = (ImageView) findViewById(R.id.imageMain);
        imageViewcontextMenu.setImageResource(R.drawable.pastapizza);
        imageViewcontextMenu.setOnCreateContextMenuListener(menuPizza);



        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    View.OnCreateContextMenuListener menuPizza = new View.OnCreateContextMenuListener() {
        //registerForContextMenu(v);
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuInflater inflater = getMenuInflater();
            menu.setHeaderTitle("rimuovi ingrediente");
            for (ListaIngrediente ingrediente : listingredienti) {
                if (ingrediente.getCount() > 0)
                    menu.add(ingrediente.getStringNome());
            }
            inflater.inflate(R.menu.menu_context_pizza, menu);
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if ((Pizza.trovaIngredientiInseriti(listingredienti, item.toString()))) {
                            listingredienti.get(Pizza.trovaIngrediente(listingredienti, item.toString())).setIngrediente(0);
                            countIngredienti--;
                            updatePizza(null);
                            Toast.makeText(CreaPizza.this,"Hai rimosso "+ item.toString(),Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
            }
        }


    };


    View.OnLongClickListener longListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            View.DragShadowBuilder dragShadow = new View.DragShadowBuilder(v);
            ClipData data = ClipData.newPlainText("", "");
            v.startDrag(data, dragShadow, v, 0);
            return false;
        }
    };




    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String imageClick = (String) (v.getContentDescription());

            if (!(Pizza.trovaIngredientiInseriti(listingredienti,imageClick))) {
                listingredienti.get(Pizza.trovaIngrediente(listingredienti,imageClick)).addIngrediente();
                countIngredienti++;
                updatePizza(imageClick);
                Toast.makeText(CreaPizza.this,"Hai aggiunto "+ imageClick,Toast.LENGTH_SHORT).show();
            }else{//ingrediente gia inserito nell immagine allora toglielo
                if ((Pizza.trovaIngredientiInseriti(listingredienti, imageClick))) {
                    listingredienti.get(Pizza.trovaIngrediente(listingredienti, imageClick)).setIngrediente(0);
                    countIngredienti--;
                    updatePizza(null);
                    Toast.makeText(CreaPizza.this,"Hai rimosso "+ imageClick,Toast.LENGTH_SHORT).show();
                }
            }
        }


    };


    View.OnDragListener dropListener; {
        dropListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragEvent = event.getAction();
                final ImageView dropImage = (ImageView) v;
                //TextView dropText2 = (TextView) v;

                switch (dragEvent) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                         Log.i("Drag Event", "Entered");

                        break;

                    case DragEvent.ACTION_DRAG_EXITED:


                        break;

                    case DragEvent.ACTION_DROP:
                        //aggiorna immagine pizza
                       String draggedImageText = (String) ((ImageView) event.getLocalState()).getContentDescription();
                        if (!(Pizza.trovaIngredientiInseriti(listingredienti,draggedImageText))) {
                           listingredienti.get(Pizza.trovaIngrediente(listingredienti,draggedImageText)).addIngrediente();
                           countIngredienti++;
                           updatePizza(draggedImageText);

                            Toast.makeText(CreaPizza.this,"Hai aggiunto "+ draggedImageText,Toast.LENGTH_SHORT).show();

                        }else{//ingrediente gia inserito nell immagine
                            listingredienti.get(Pizza.trovaIngrediente(listingredienti,draggedImageText)).addIngrediente();
                                System.out.println("hai inserito troppi ingredienti dello stesso tipo");
                        }


                        break;
                }

                return true;
            }

        };
    }



private Bitmap trovaIngredienteBitmap(ListaIngrediente ingrediente, Resources resources){
    Bitmap bm;
    switch (ingrediente.getNome()) {
        case SUGO:
            bm = BitmapFactory.decodeResource(resources, R.drawable.sugo);
            break;
        case MOZZARELLA:
            bm = BitmapFactory.decodeResource(resources, R.drawable.mozzarella);
            break;
        case BASILICO:
            bm = BitmapFactory.decodeResource(resources, R.drawable.basilico);
            break;
        case FUNGHI:
            bm = BitmapFactory.decodeResource(resources, R.drawable.funghi);
            break;
        case SALMONE:
            bm = BitmapFactory.decodeResource(resources, R.drawable.salmone);
            break;
        default:
            System.out.println("ingrediente non disponibile");
            bm=null;
            break;
    }
    return bm;
}





private void updatePizza(String nuovoIngrediente) {
    Drawable[] layers = new Drawable[countIngredienti+1];
    Resources resources = getResources();
    ImageView imgMain = (ImageView) findViewById(R.id.imageMain);

    Bitmap bm = BitmapFactory.decodeResource(resources, R.drawable.pastapizza);
    BitmapDrawable bmd = new BitmapDrawable(bm);
    bmd.setGravity(Gravity.TOP);

    layers[0] = bmd;

    int i=1;
    if (countIngredienti>0) {
        for (ListaIngrediente ingrediente : listingredienti)
            if (ingrediente.getCount()>0){
                bmd = new BitmapDrawable(trovaIngredienteBitmap(ingrediente, resources));
                bmd.setGravity(Gravity.TOP);
                //  bmd.setTargetDensity(metrics);
                if (i < countIngredienti + 1) {
                    layers[i] = bmd;
                    i++;
                } else {
                    System.out.println("errore nel conteggio degli ingredienti");
                }
            }
    }

    layerDrawable = new LayerDrawable(layers);


    imgMain.setImageDrawable(layerDrawable);
}




    private void doPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.popup_one:
                                Toast.makeText(CreaPizza.this, "Popup item" +
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

        inflater.inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.show();

    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreaPizza Page", // TODO: Define a title for the content shown.
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
                "CreaPizza Page", // TODO: Define a title for the content shown.
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
