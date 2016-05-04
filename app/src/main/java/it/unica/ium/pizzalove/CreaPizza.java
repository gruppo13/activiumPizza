package it.unica.ium.pizzalove;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.readystatesoftware.viewbadger.BadgeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by perlo on 13/02/16.
 */
public class CreaPizza extends Activity {


    private Pizza nuovaPizza = new Pizza("creata");
    protected ImageView imgMain;
    private int[] countPizze = new int[22];
    private BadgeView[] badge;
    private Bundle bundle;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getIntent().getExtras();
        setContentView(R.layout.activity_creapizza);

        imgMain  = (ImageView) findViewById(R.id.imageMain);

        //immagini da click
        findViewById(R.id.sugo).setOnClickListener(clickListener);
        findViewById(R.id.mozzarella).setOnClickListener(clickListener);
        findViewById(R.id.funghi).setOnClickListener(clickListener);
        findViewById(R.id.bacon).setOnClickListener(clickListener);
        findViewById(R.id.broccoli).setOnClickListener(clickListener);
        findViewById(R.id.cipolle).setOnClickListener(clickListener);
        findViewById(R.id.formaggio).setOnClickListener(clickListener);
        findViewById(R.id.gamberetti).setOnClickListener(clickListener);
        findViewById(R.id.melanzane).setOnClickListener(clickListener);
        findViewById(R.id.olive).setOnClickListener(clickListener);
        findViewById(R.id.patatine).setOnClickListener(clickListener);
        findViewById(R.id.peperoncini).setOnClickListener(clickListener);
        findViewById(R.id.peperoni).setOnClickListener(clickListener);
        findViewById(R.id.pomodori).setOnClickListener(clickListener);
        findViewById(R.id.salame).setOnClickListener(clickListener);
        findViewById(R.id.uova).setOnClickListener(clickListener);
        findViewById(R.id.wurstel).setOnClickListener(clickListener);
        findViewById(R.id.zucchine).setOnClickListener(clickListener);
        findViewById(R.id.capperi).setOnClickListener(clickListener);
        findViewById(R.id.acciughe).setOnClickListener(clickListener);
        findViewById(R.id.basilico).setOnClickListener(clickListener);
        findViewById(R.id.cotto).setOnClickListener(clickListener);

        badge = new BadgeView[]{
                new BadgeView(this, findViewById(R.id.sugo)),
                new BadgeView(this, findViewById(R.id.mozzarella)),
                new BadgeView(this, findViewById(R.id.basilico)),
                new BadgeView(this, findViewById(R.id.funghi)),
                new BadgeView(this, findViewById(R.id.acciughe)),
                new BadgeView(this, findViewById(R.id.capperi)),
                new BadgeView(this, findViewById(R.id.bacon)),
                new BadgeView(this, findViewById(R.id.broccoli)),
                new BadgeView(this, findViewById(R.id.cipolle)),
                new BadgeView(this, findViewById(R.id.formaggio)),
                new BadgeView(this, findViewById(R.id.gamberetti)),
                new BadgeView(this, findViewById(R.id.melanzane)),
                new BadgeView(this, findViewById(R.id.olive)),
                new BadgeView(this, findViewById(R.id.patatine)),
                new BadgeView(this, findViewById(R.id.peperoncini)),
                new BadgeView(this, findViewById(R.id.peperoni)),
                new BadgeView(this, findViewById(R.id.pomodori)),
                new BadgeView(this, findViewById(R.id.salame)),
                new BadgeView(this, findViewById(R.id.uova)),
                new BadgeView(this, findViewById(R.id.wurstel)),
                new BadgeView(this, findViewById(R.id.zucchine)),
                new BadgeView(this, findViewById(R.id.cotto))
        };

        if(bundle.getSerializable(Bundles.MODIFICA_PIZZA.getBundle())!= null){
            //preleva tutti gli ingredienti
            for (Ingredienti ingredienti : (List<Ingredienti>)bundle.getSerializable(Bundles.MODIFICA_PIZZA.getBundle()))
                setBadge(ingredienti.toString());
        }

        Button btnAddPizzaCreate = (Button) findViewById(R.id.btnAddPizzaCreate);
        FloatingActionButton btnNuovaPizza = (FloatingActionButton) findViewById(R.id.addPizza);

        btnNuovaPizza.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bundle = aggiornaBundle(bundle, nuovaPizza);
                onCreate(bundle);
            }
        });

        btnAddPizzaCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //aggiunge la pizza creata al bundle
                Intent intent = new Intent(CreaPizza.this, Carrello.class);
                bundle = aggiornaBundle(bundle, nuovaPizza);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });

        updatePizza();

        ImageView imageViewcontextMenu = (ImageView) findViewById(R.id.imageMain);
        imageViewcontextMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogRimuoviIngredienti();
                return true;
            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public static Bundle aggiornaBundle(Bundle b, Pizza nuovaPizza) {
        ((List<Pizza>)b.get(Bundles.ELENCO_PIZZE.getBundle())).add(nuovaPizza);
        return b;
    }

    private void dialogRimuoviIngredienti(){

    if (!nuovaPizza.getIngredienti().isEmpty()) {

        final Dialog dialog = new Dialog(CreaPizza.this);
        dialog.setTitle("rimuovi ingredienti");
        dialog.setContentView(R.layout.dialog_ingredienti);

        final TableLayout table = (TableLayout) dialog.findViewById(R.id.table);
        LayoutInflater infalInflater;
        View convertView;

        for (Ingredienti ingrediente : nuovaPizza.getIngredienti()) {
            infalInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_check, null);
            CheckBox check = (CheckBox) convertView.findViewById(R.id.checkBox1);
            check.setText(ingrediente.toString());
            table.addView(convertView);
        }
        dialog.show();

        dialog.findViewById(R.id.txtRimuoviTutto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < nuovaPizza.countIngredienti(); i++) {
                    setEnableIngrediente(nuovaPizza.getIngrediente(i).toString(), true);
                    nuovaPizza.removeIngrediente(i);
                }
                updatePizza();
                Toast.makeText(CreaPizza.this, "Hai rimosso tutti gli ingredienti", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });



        final int[] flagcheck = {0};
        for(int i=0; i < table.getChildCount();i++) {
            convertView = table.getChildAt(i);
            CheckBox check = (CheckBox) convertView.findViewById(R.id.checkBox1);

            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    flagcheck[0]++;
                else
                    flagcheck[0]--;

                if (flagcheck[0]>0)
                    dialog.findViewById(R.id.txtRimuoviSelezionati).setEnabled(true);
                else
                    dialog.findViewById(R.id.txtRimuoviSelezionati).setEnabled(false);
                Log.i("Rimuovi Selezionati","abilitato");

            }
        });}


        dialog.findViewById(R.id.txtRimuoviSelezionati).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < table.getChildCount(); i++) {
                    //LayoutInflater infalInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View convertView = table.getChildAt(i);
                    CheckBox check = (CheckBox) convertView.findViewById(R.id.checkBox1);
                    if (check.isChecked()) {
                        nuovaPizza.removeIngrediente(Ingredienti.valueOf(check.getText().toString()));
                        updatePizza();
                        setEnableIngrediente(check.getText().toString(), true);
                    }
                }
                dialog.cancel();
            }
        });
    }
}




    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getTitle().toString()) {
            case "true":
                Log.d("check ", "first case");
                //editNote(info.id);
                return true;
            case "bo":
                Log.d("check ", "second case");


                return super.onContextItemSelected(item);
            default:
                return false;
        }
    }


    /* rende visibile gli ingredienti */
    private void setEnableIngrediente(String ingrediente, boolean value){

        switch (ingrediente) {
            case "Sugo":
                findViewById(R.id.sugo).setEnabled(value);
                break;
            case "Mozzarella":
                findViewById(R.id.mozzarella).setEnabled(value);
                break;
            case "Basilico":
               findViewById(R.id.basilico).setEnabled(value);
                break;
            case "Funghi":
                findViewById(R.id.funghi).setEnabled(value);
                break;
            case "Bacon":
                findViewById(R.id.bacon).setEnabled(value);
                break;
            case "Broccoli":
                findViewById(R.id.broccoli).setEnabled(value);
                break;
            case "Cipolle":
                findViewById(R.id.cipolle).setEnabled(value);
                break;
            case "Grana":
                findViewById(R.id.formaggio).setEnabled(value);
                break;
            case "Gamberetti":
                findViewById(R.id.gamberetti).setEnabled(value);
                break;
            case "Melanzane":
                findViewById(R.id.melanzane).setEnabled(value);
                break;
            case "Olive":
                findViewById(R.id.olive).setEnabled(value);
                break;
            case "Patatine":
                findViewById(R.id.patatine).setEnabled(value);
                break;
            case "Peperoni":
                findViewById(R.id.peperoni).setEnabled(value);
                break;
            case "Peperoncino":
                findViewById(R.id.peperoncini).setEnabled(value);
                break;
            case "Pomodori":
                findViewById(R.id.pomodori).setEnabled(value);
                break;
            case "Salame":
                findViewById(R.id.salame).setEnabled(value);
                break;
            case "Uova":
                findViewById(R.id.uova).setEnabled(value);
                break;
            case "Wurstel":
                findViewById(R.id.wurstel).setEnabled(value);
                break;
            case "Zucchine":
                findViewById(R.id.zucchine).setEnabled(value);
                break;
            case "Cotto":
                findViewById(R.id.cotto).setEnabled(value);
                break;
            case "Acciughe":
                findViewById(R.id.acciughe).setEnabled(value);
                break;
            case "Capperi":
                findViewById(R.id.capperi).setEnabled(value);
                break;
            default:
                Log.d("Not Found", "ingrediente non ancora disponibile");
                break;

        }
    }


    View.OnLongClickListener longListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    };




    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String imageClick = (String) (v.getContentDescription());
            Log.e("errore grana", imageClick);
            setBadge(imageClick);
            for(int i : countPizze) Log.e("numeri", Integer.toString(i));
            updatePizza();
        }
    };

    private void setBadge(String img) {
        int i = Ingredienti.valueOf(img).getNumber() - 1;
        if(countPizze[i] < 2) {
            badge[i].setText(String.format("%d", ++countPizze[i]));
            badge[i].show();
            nuovaPizza.addIngrediente(Ingredienti.valueOf(img));
        }
        else{
            nuovaPizza.sort();
            while(nuovaPizza.getIngredienti().contains(Ingredienti.valueOf(img)))
                nuovaPizza.removeIngrediente(Ingredienti.valueOf(img));
            badge[i].hide();
            countPizze[i] = 0;
        }
        bundle.putSerializable(Bundles.MODIFICA_PIZZA.getBundle(), (Serializable)nuovaPizza.getIngredienti());
    }




protected static Bitmap trovaIngredienteBitmap(Ingredienti ingrediente, Resources resources){
    Bitmap bm = null;
    switch (ingrediente) {
        case Sugo:
            bm = BitmapFactory.decodeResource(resources, R.drawable.sugo);
            break;
        case Mozzarella:
            bm = BitmapFactory.decodeResource(resources, R.drawable.mozzarella);
            break;
        case Basilico:
            bm = BitmapFactory.decodeResource(resources, R.drawable.basilico);
            break;
        case Funghi:
            bm = BitmapFactory.decodeResource(resources, R.drawable.funghi);
            break;
        case Bacon:
           bm = BitmapFactory.decodeResource(resources, R.drawable.bacon);
            break;
        case Broccoli:
           bm = BitmapFactory.decodeResource(resources, R.drawable.broccoli);
            break;
        case Cipolle:
          bm = BitmapFactory.decodeResource(resources, R.drawable.cipolle);
            break;
        case Grana:
            bm = BitmapFactory.decodeResource(resources, R.drawable.formaggio);
            break;
        case Gamberetti:
            bm = BitmapFactory.decodeResource(resources, R.drawable.gamberetti);
            break;
        case Melanzane:
            bm = BitmapFactory.decodeResource(resources, R.drawable.melanzane);
            break;
        case Olive:
           bm = BitmapFactory.decodeResource(resources, R.drawable.olive);
            break;
        case Patatine:
            bm = BitmapFactory.decodeResource(resources, R.drawable.patatine);
            break;
        case Peperoni:
            bm = BitmapFactory.decodeResource(resources, R.drawable.peperoni);
            break;
        case Peperoncino:
            bm = BitmapFactory.decodeResource(resources, R.drawable.peperoncini);
            break;
        case Pomodoro:
            bm = BitmapFactory.decodeResource(resources, R.drawable.pomodori);
            break;
        case Salame:
            bm = BitmapFactory.decodeResource(resources, R.drawable.salame);
            break;
        case Uova:
            bm = BitmapFactory.decodeResource(resources, R.drawable.uova);
            break;
        case Wurstel:
            bm = BitmapFactory.decodeResource(resources, R.drawable.wurstel);
            break;
        case Zucchine:
            bm = BitmapFactory.decodeResource(resources, R.drawable.zucchine);
            break;
        case Capperi:
            bm = BitmapFactory.decodeResource(resources, R.drawable.capperi);
            break;
        case Acciughe:
            bm = BitmapFactory.decodeResource(resources, R.drawable.acciughe);
            break;
        case Cotto:
            bm = BitmapFactory.decodeResource(resources, R.drawable.cotto);
            break;
        default:
            break;
    }
    return bm;
}





    private void updatePizza() {
        List<Drawable> layers = new ArrayList<>();
        Resources resources = getResources();
        ImageView imgMain = (ImageView) findViewById(R.id.imageMain);

        Bitmap bm = BitmapFactory.decodeResource(resources, R.drawable.pastapizza);
        BitmapDrawable bmd = new BitmapDrawable(resources, bm);
        bmd.setGravity(Gravity.TOP);
        layers.add(bmd);

        if (!nuovaPizza.getIngredienti().isEmpty()){
            for (Ingredienti ingrediente : nuovaPizza.getIngredienti()) {
                Log.e("i tuoi ingredienti ", ingrediente.toString());
                bmd = new BitmapDrawable(resources, trovaIngredienteBitmap(ingrediente, resources));
                bmd.setGravity(Gravity.TOP);
                layers.add(bmd);
            }
            findViewById(R.id.btnAddPizzaCreate).setEnabled(true);
        }
        else{//non ci sono ingredienti quindi non puoi creare una pizza vuota
            findViewById(R.id.btnAddPizzaCreate).setEnabled(false);
        }

        Drawable[] temp = new Drawable[layers.size()];
        temp = layers.toArray(temp);
        LayerDrawable layerDrawable = new LayerDrawable(temp);
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
