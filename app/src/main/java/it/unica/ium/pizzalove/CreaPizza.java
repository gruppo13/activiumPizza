package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.ClipData;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
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
    final int maxIngredienti=1;
    HashMap<String, Integer> listingredienti;

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
        listingredienti = new HashMap<String,Integer>();
        updatePizza(null);
        prepareIngredienti();

        // immagini da trascinare
        findViewById(R.id.image).setOnLongClickListener(longListener);
        findViewById(R.id.image1).setOnLongClickListener(longListener);
        findViewById(R.id.image2).setOnLongClickListener(longListener);
        findViewById(R.id.image3).setOnLongClickListener(longListener);
        findViewById(R.id.image4).setOnLongClickListener(longListener);


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

                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        //dropText.setTextColor(Color.RED);
                        break;

                    case DragEvent.ACTION_DROP:
                        //aggiorna immagine pizza
                       String draggedImageText = (String) ((ImageView) event.getLocalState()).getContentDescription();
                        if (listingredienti.get(draggedImageText)==0) {
                           listingredienti.put(draggedImageText, listingredienti.get(draggedImageText)+1);
                           countIngredienti++;
                           updatePizza(draggedImageText);
                        }else{
                                System.out.println("hai inserito troppi ingredienti dello stesso tipo");
                        }
                        break;
                }

                return true;
            }

        };
    }



private Bitmap trovaIngrediente(String ingrediente, Resources resources){
    Bitmap bm;
    switch (ingrediente) {
        case "sugo":
            bm = BitmapFactory.decodeResource(resources, R.drawable.sugo);
            break;
        case "mozzarella":
            bm = BitmapFactory.decodeResource(resources, R.drawable.mozzarella);
            break;
        case "basilico":
            bm = BitmapFactory.decodeResource(resources, R.drawable.basilico);
            break;
        case "funghi":
            bm = BitmapFactory.decodeResource(resources, R.drawable.funghi);
            break;
        case "salmone":
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
    Drawable[] layers = new Drawable[countIngredienti+ 1];
    Resources resources = getResources();
    ImageView imgMain = (ImageView) findViewById(R.id.imageMain);

    Bitmap bm = BitmapFactory.decodeResource(resources, R.drawable.pastapizza);
    BitmapDrawable bmd = new BitmapDrawable(bm);
    bmd.setGravity(Gravity.TOP);

    layers[0] = bmd;

    int i=1;
    if (countIngredienti>0) {

        //attenzioni si considera un solo ingrediente da poter inserire
        for (String ingrediente : listingredienti.keySet())

            if ((listingredienti.get(ingrediente) == 1) && (!(nuovoIngrediente.equals(ingrediente)))) {
                bmd = new BitmapDrawable(trovaIngrediente(ingrediente, resources));
                bmd.setGravity(Gravity.TOP);
                //  bmd.setTargetDensity(metrics);
                if (i < countIngredienti + 1) {
                    layers[i] = bmd;

                    i++;
                } else {
                    System.out.println("errore nel conteggio degli ingredienti");
                }
            }
//inserisce l ultimo ingrediente in cima
        if (nuovoIngrediente != null) {
            if (listingredienti.get(nuovoIngrediente) == 1) {
                bmd = new BitmapDrawable(trovaIngrediente(nuovoIngrediente, resources));
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
    }





    layerDrawable = new LayerDrawable(layers);


    imgMain.setImageDrawable(layerDrawable);
}






    private void prepareIngredienti() {
        listingredienti = new HashMap<String, Integer>();

        // Adding child data


        listingredienti.put("sugo", 0); // Header, Child data
        listingredienti.put("mozzarella", 0);
        listingredienti.put("basilico", 0);
        listingredienti.put("funghi", 0);
        listingredienti.put("salmone", 0);


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
