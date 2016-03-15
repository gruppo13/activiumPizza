package it.unica.ium.pizzalove;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perlo on 13/02/16.
 */
public class CreaPizza extends AppCompatActivity  {


    //int countIngredienti;
    Pizza nuovaPizza = new Pizza("creata");
    LayerDrawable layerDrawable;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
    setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);*/

        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_creapizza);
        //this.countIngredienti = 0;

        //modifiche pizza
        if(bundle.getString("aggiunte")!= null) {
            String pizzaModificare = bundle.getString("aggiunte");
            //modifica pizza carrello
            if (pizzaModificare.equals("not valid")) {

                for(String nome : bundle.getStringArrayList("aggiunteCreata")){
                    nuovaPizza.addIngrediente(Ingredienti.valueOf(nome));
                    //countIngredienti++;
                    //setGoneIngrediente(listIngredienti.get(Pizza.trovaIngrediente(listIngredienti, nome)));
                    this.setEnableIngrediente(nome, false);
                }
            }
            else{
                //modifica pizza esistente (elenco / carrello)
                Pizza nuovaPizza = new Pizza(pizzaModificare);
                for (Ingredienti ingrediente : nuovaPizza.getIngredienti()){
                    nuovaPizza.addIngrediente(ingrediente);
                    //countIngredienti++;
                    //setGoneIngrediente(listIngredienti.get(Pizza.trovaIngrediente(listIngredienti, ingrediente.getStringNome())));
                    this.setEnableIngrediente(ingrediente.toString(), false);

                }
            }
        }


            // immagini da trascinare
            findViewById(R.id.sugo).setOnLongClickListener(longListener);
            findViewById(R.id.mozzarella).setOnLongClickListener(longListener);
            findViewById(R.id.funghi).setOnLongClickListener(longListener);
            findViewById(R.id.broccoli).setOnLongClickListener(longListener);
            findViewById(R.id.becon).setOnLongClickListener(longListener);
            findViewById(R.id.cipolle).setOnLongClickListener(longListener);
            findViewById(R.id.formaggio).setOnLongClickListener(longListener);
            findViewById(R.id.gamberetti).setOnLongClickListener(longListener);
            findViewById(R.id.melanzane).setOnLongClickListener(longListener);
            findViewById(R.id.olive).setOnLongClickListener(longListener);
            findViewById(R.id.patatine).setOnLongClickListener(longListener);
            findViewById(R.id.peperoncini).setOnLongClickListener(longListener);
            findViewById(R.id.peperoni).setOnLongClickListener(longListener);
            findViewById(R.id.pomodori).setOnLongClickListener(longListener);
            findViewById(R.id.salame).setOnLongClickListener(longListener);
            findViewById(R.id.uova).setOnLongClickListener(longListener);
            findViewById(R.id.wurstel).setOnLongClickListener(longListener);
            findViewById(R.id.salame).setOnLongClickListener(longListener);


            //immagini da click
            findViewById(R.id.sugo).setOnClickListener(clickListener);
            findViewById(R.id.mozzarella).setOnClickListener(clickListener);
            findViewById(R.id.funghi).setOnClickListener(clickListener);
            findViewById(R.id.becon).setOnClickListener(clickListener);
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





        Button btnAddPizzaCreate = (Button) findViewById(R.id.btnAddPizzaCreate);

        btnAddPizzaCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreaPizza.this, Carrello.class);
                Bundle b = getIntent().getExtras();

                List<Ingredienti> ingredientib = nuovaPizza.getIngredienti();
                ArrayList<String> ingredienti = new ArrayList<>();
                for(Ingredienti i : ingredientib){
                    ingredienti.add(i.toString());
                }
                if (b.getInt("creata")>0)
                    b.putInt("creata", b.getInt("creata")+1);
                else
                    b.putInt("creata",1);
                b.putStringArrayList(String.valueOf(b.getInt("creata")), ingredienti);
                intent.putExtras(b);

                onResume();
                startActivityForResult(intent, 0);

            }
        });


        updatePizza();

        //immagini da modificare
        //findViewById(R.id.imageMain).setOnDragListener(dropListener);

        ImageView imageViewcontextMenu = (ImageView) findViewById(R.id.imageMain);
        //imageViewcontextMenu.setImageResource(R.drawable.pastapizza);
        imageViewcontextMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
              //  openContextMenu(v);
                //registerForContextMenu(v);
                dialogRimuoviIngredienti();

                return true;
            }
        });
        //imageViewcontextMenu.setOnCreateContextMenuListener(menuPizza);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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


private boolean leastOneCheck(TableLayout table){
    for (int i = 0; i < table.getChildCount(); i++) {
        View convertView = table.getChildAt(i);
        CheckBox check = (CheckBox) convertView.findViewById(R.id.checkBox1);
        if (check.isChecked())
            return true;
    }

    return false;

}


/*
       @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = getMenuInflater();
            menu.setHeaderTitle("rimuovi ingrediente");
            menu.add("rimuovi tutti gli ingredienti");
            menu.add("rimuovi ingredienti selezionati(funzionalita da sviluppare)");

            int i = 2;

            for (ListaIngrediente ingrediente : listIngredienti) {
                if (ingrediente.getCount() > 0) {
                    menu.addSubMenu(2, i + 1, menu.NONE, ingrediente.getStringNome());
                    menu.setGroupCheckable(2, true, false);

                    // menu.add(ingrediente.getStringNome());
                    //menu.setGroupCheckable(1, true, true);            //rimuovi tutti gli ingredienti
                    i++;
                }
            }
            if (i > 2) {

                inflater.inflate(R.menu.menu_context_pizza, menu);

                menu.getItem(1).setEnabled(false);

                menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        for (int i = 0; i < listIngredienti.size(); i++) {
                            listIngredienti.get(i).setIngrediente(0);
                        }
                        countIngredienti = 0;
                        updatePizza();
                        Toast.makeText(CreaPizza.this, "Hai rimosso tutti gli ingredienti", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < listIngredienti.size(); i++) {
                            setVisibilityIngrediente(listIngredienti.get(i));
                        }

                        return true;
                    }
                });
//seleziona



                                //elimina solo gli elementi selezionati
                menu.getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        return true;
                    }
                });


                // rimuove solo l-ingrediente selezionato
/*
                for (int j = 2; j < menu.size(); j++) {
                    menu.getItem(j).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if ((Pizza.trovaIngredientiInseriti(listIngredienti, item.toString()))) {
                                listIngredienti.get(Pizza.trovaIngrediente(listIngredienti, item.toString())).setIngrediente(0);
                                countIngredienti--;
                                updatePizza();
                                Toast.makeText(CreaPizza.this, "Hai rimosso " + item.toString(), Toast.LENGTH_SHORT).show();
                                setVisibilityIngrediente(listIngredienti.get(Pizza.trovaIngrediente(listIngredienti, item.toString())));
                            }
                            return true;
                        }
                    });
                }
            }
        }
*/




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


/*
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        for(ListaIngrediente ingrediente: listIngredienti)
            if (item.getTitle().equals(ingrediente.getStringNome())){
                item.setChecked(true);
                Log.d("checked", ingrediente.getStringNome());
                //Log.d("info", String.valueOf(info.position));
                //onPrepareOptionsMenu((Menu)item);

                return super.onOptionsItemSelected(item);


            }

        Log.d("checked", "false");

        return false;
    }*/




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
                findViewById(R.id.becon).setEnabled(value);
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
            //aggiunge ingrediente nella pizza e lo elimina dalla lista degli ingredienti da inserire
            nuovaPizza.addIngrediente(Ingredienti.valueOf(imageClick));
            updatePizza();
            Toast.makeText(CreaPizza.this,"Hai aggiunto "+ imageClick,Toast.LENGTH_SHORT).show();
            v.setEnabled(false);
        }
    };

/*
    View.OnDragListener dropListener; {
        dropListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragEvent = event.getAction();
                final ImageView dropImage = (ImageView) v;

                switch (dragEvent) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                         Log.i("Drag Event", "Entered");

                        break;

                    case DragEvent.ACTION_DRAG_EXITED:


                        break;

                    case DragEvent.ACTION_DROP:
                        //aggiorna immagine pizza
                       String draggedImageText = (String) ((ImageView) event.getLocalState()).getContentDescription();
                        if (!(Pizza.trovaIngredientiInseriti(listIngredienti,draggedImageText))) {
                           listIngredienti.get(Pizza.trovaIngrediente(listIngredienti,draggedImageText)).addIngrediente();
                           countIngredienti++;
                           updatePizza();
                            setGoneIngrediente(listIngredienti.get(Pizza.trovaIngrediente(listIngredienti, draggedImageText)));

                            Toast.makeText(CreaPizza.this,"Hai aggiunto "+ draggedImageText,Toast.LENGTH_SHORT).show();

                        }else{//ingrediente gia inserito nell immagine
                            listIngredienti.get(Pizza.trovaIngrediente(listIngredienti,draggedImageText)).addIngrediente();
                                System.out.println("hai inserito troppi ingredienti dello stesso tipo");
                        }


                        break;
                }

                return true;
            }

        };
    }*/



private Bitmap trovaIngredienteBitmap(Ingredienti ingrediente, Resources resources){
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
        case Pepeperoni:
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
            //bm = BitmapFactory.decodeResource(resources, R.drawable.capperi);
            break;
        case Acciughe:
            bm = BitmapFactory.decodeResource(resources, R.drawable.acciughe);
            break;
        case Cotto:
            bm = BitmapFactory.decodeResource(resources, R.drawable.cotto);
            break;
        default:
            Toast.makeText(CreaPizza.this,"Ci siamo dimenticati un ingrediente", Toast.LENGTH_SHORT).show();
            break;
    }
    return bm;
}





private void updatePizza() {
    Drawable[] layers = new Drawable[nuovaPizza.countIngredienti()+1];
    Resources resources = getResources();
    ImageView imgMain = (ImageView) findViewById(R.id.imageMain);

    Bitmap bm = BitmapFactory.decodeResource(resources, R.drawable.pastapizza);
    BitmapDrawable bmd = new BitmapDrawable(resources, bm);
    bmd.setGravity(Gravity.TOP);

    layers[0] = bmd;

    int i=1;
    if (!nuovaPizza.getIngredienti().isEmpty()) {
        for (Ingredienti ingrediente : nuovaPizza.getIngredienti())
                bmd = new BitmapDrawable(resources, trovaIngredienteBitmap(ingrediente, resources));
                bmd.setGravity(Gravity.TOP);
                //  bmd.setTargetDensity(metrics);
                if (i < nuovaPizza.countIngredienti() + 1) {
                    layers[i] = bmd;
                    i++;
                }
        findViewById(R.id.btnAddPizzaCreate).setEnabled(true);
    }
    else{//non ci sono ingredienti quindi non puoi creare una pizza vuota
        findViewById(R.id.btnAddPizzaCreate).setEnabled(false);

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
