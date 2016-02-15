package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
                Bundle b = getIntent().getExtras();


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
//findViewById(R.id.image1).lis

        //immagini da modificare
        findViewById(R.id.imageMain).setOnDragListener(dropListener);


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    View.OnLongClickListener longListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            View.DragShadowBuilder dragShadow = new View.DragShadowBuilder(v);
            ClipData data = ClipData.newPlainText("", "");
            v.startDrag(data, dragShadow, v, 0);
            // v.startDrag()
            return false;

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
                        //dropText.setTextColor(Color.GREEN);
                        Log.i("Drag Event", "Entered");
                        //dropImage.setBackgroundColor(Color.YELLOW);



                       /* DrawableDragShadow dragShadow = new DragShadow(v);

                        ClipData data = ClipData.newPlainText("","");

                        v.startDrag(data, dragShadow, v, 0);*/
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:

                        //dropText.setTextColor(Color.RED);
                        break;

                    case DragEvent.ACTION_DROP:
                        //aggiorna immagine pizza
                       String draggedImageText = (String) ((ImageView) event.getLocalState()).getContentDescription();


                        if (!(Pizza.trovaIngredientiInseriti(listingredienti,draggedImageText))) {
                           listingredienti.get(Pizza.trovaIngrediente(listingredienti,draggedImageText)).addIngrediente();
                           countIngredienti++;
                           updatePizza(draggedImageText);
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
