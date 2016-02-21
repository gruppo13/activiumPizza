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
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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

        String pizza;
        Bundle bundle;

        bundle = getIntent().getExtras();
        List<ListaPizza> elenco = new ArrayList<>();

        setContentView(R.layout.activity_creapizza);
        this.countIngredienti = 0;
        listingredienti = new ArrayList<>();
        listingredienti = Pizza.resetIngredienti();

        if (bundle.getString("aggiunte")!= null) {
            pizza = bundle.getString("aggiunte");
            if (bundle.getString("aggiunte").equals("not valid")) {
                if (bundle.getStringArrayList("aggiunteCreata")==null)
                    Log.d("aggiunte","null");
                for(String nome : bundle.getStringArrayList("aggiunteCreata")){
                    listingredienti.get((Pizza.trovaIngrediente(listingredienti,nome))).addIngrediente();
                    countIngredienti++;
                    setGoneIngrediente(listingredienti.get(Pizza.trovaIngrediente(listingredienti, nome)));
                }


            }else{
                ListaPizza pizzanuova = new ListaPizza(ListaPizza.getClassicaS(pizza));
                for (ListaIngrediente ingrediente : pizzanuova.getIngredienti()) {
                    listingredienti.get(Pizza.trovaIngrediente(listingredienti, ingrediente.getStringNome())).addIngrediente();
                    countIngredienti++;
                    setGoneIngrediente(listingredienti.get(Pizza.trovaIngrediente(listingredienti, ingrediente.getStringNome())));

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

                //ArrayList<ArrayList<Integer>>
                Bundle b = getIntent().getExtras();

               /* for (ListaIngrediente ingrediente : listingredienti) {
                    ArrayList<Integer> ingredientiCreata;
                    if (b.getStringArrayList(ingrediente.getStringNome()) != null)
                        ingredientiCreata= b.getIntegerArrayList(ingrediente.getStringNome());
                    else
                        ingredientiCreata = new ArrayList<Integer>();
                    ingredientiCreata.add(ingrediente.getCount());
                    b.putIntegerArrayList(ingrediente.getStringNome(), ingredientiCreata);
                }*/

                ArrayList<String> ingredienti = new ArrayList<String>();
                for (ListaIngrediente ingrediente : listingredienti){
                    if (ingrediente.getCount()>0)
                        ingredienti.add(ingrediente.getStringNome());
                }
                if (b.getInt("creata")>0)
                    b.putInt("creata", b.getInt("creata")+1);
                else
                    b.putInt("creata",1);

                b.putStringArrayList(String.valueOf(b.getInt("creata")), ingredienti);


               // b.putStringArrayList("lista", new ArrayList<String>(listingredienti.keySet()));
                intent.putExtras(b);
                onResume();

                startActivityForResult(intent, 0);

            }
        });


        updatePizza();

        //immagini da modificare
        findViewById(R.id.imageMain).setOnDragListener(dropListener);

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

    final Dialog dialog = new Dialog (CreaPizza.this);
    dialog.setTitle("rimuovi ingredienti");
    dialog.setContentView(R.layout.dialog_ingredienti);

    final TableLayout table = (TableLayout) dialog.findViewById(R.id.table);



    LayoutInflater infalInflater;
    View convertView;

    for (ListaIngrediente ingrediente : listingredienti) {
        if (ingrediente.getCount() > 0) {
            infalInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_check, null);
            CheckBox check = (CheckBox)convertView.findViewById(R.id.checkBox1);
            check.setText(ingrediente.getStringNome());
            table.addView(convertView);
        }
    }



    dialog.show();

    dialog.findViewById(R.id.txtRimuoviTutto).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < listingredienti.size(); i++) {
                listingredienti.get(i).setIngrediente(0);
            }
            countIngredienti = 0;
            updatePizza();
            Toast.makeText(CreaPizza.this, "Hai rimosso tutti gli ingredienti", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < listingredienti.size(); i++) {
                setVisibilityIngrediente(listingredienti.get(i));
            }

            dialog.cancel();
        }
    });
    dialog.findViewById(R.id.txtRimuoviSelezionati).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i=0;i<table.getChildCount();i++){
                //LayoutInflater infalInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View convertView = table.getChildAt(i);
                if (convertView==null)
                    Log.i("View check ", "non esiste");
                CheckBox check = (CheckBox)convertView.findViewById(R.id.checkBox1);
                if (check==null)
                    Log.i("View checkbox ", "non esiste");
                if (check.isChecked()){
                    Log.i("View checkbox testo", check.getText().toString());
                    if ((Pizza.trovaIngredientiInseriti(listingredienti, (check.getText().toString())))) {
                        listingredienti.get(Pizza.trovaIngrediente(listingredienti, (check.getText().toString()))).setIngrediente(0);
                        countIngredienti--;
                        updatePizza();
                        setVisibilityIngrediente(listingredienti.get(Pizza.trovaIngrediente(listingredienti, (check.getText().toString()))));
                    }

                }

            }





            dialog.cancel();
        }
    });









}





/*
       @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = getMenuInflater();
            menu.setHeaderTitle("rimuovi ingrediente");
            menu.add("rimuovi tutti gli ingredienti");
            menu.add("rimuovi ingredienti selezionati(funzionalita da sviluppare)");

            int i = 2;

            for (ListaIngrediente ingrediente : listingredienti) {
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
                        for (int i = 0; i < listingredienti.size(); i++) {
                            listingredienti.get(i).setIngrediente(0);
                        }
                        countIngredienti = 0;
                        updatePizza();
                        Toast.makeText(CreaPizza.this, "Hai rimosso tutti gli ingredienti", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < listingredienti.size(); i++) {
                            setVisibilityIngrediente(listingredienti.get(i));
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
                            if ((Pizza.trovaIngredientiInseriti(listingredienti, item.toString()))) {
                                listingredienti.get(Pizza.trovaIngrediente(listingredienti, item.toString())).setIngrediente(0);
                                countIngredienti--;
                                updatePizza();
                                Toast.makeText(CreaPizza.this, "Hai rimosso " + item.toString(), Toast.LENGTH_SHORT).show();
                                setVisibilityIngrediente(listingredienti.get(Pizza.trovaIngrediente(listingredienti, item.toString())));
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
        for(ListaIngrediente ingrediente: listingredienti)
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
    private void setVisibilityIngrediente(ListaIngrediente ingrediente){

        switch (ingrediente.getNome()) {
            case SUGO:
                findViewById(R.id.sugo).setVisibility(View.VISIBLE);
                break;
            case MOZZARELLA:
                findViewById(R.id.mozzarella).setVisibility(View.VISIBLE);
                break;
            case BASILICO:
               findViewById(R.id.basilico).setVisibility(View.VISIBLE);
                break;
            case FUNGHI:
                findViewById(R.id.funghi).setVisibility(View.VISIBLE);
                break;
            case BECON:
                findViewById(R.id.becon).setVisibility(View.VISIBLE);
                break;
            case BROCCOLI:
                findViewById(R.id.broccoli).setVisibility(View.VISIBLE);
                break;
            case CIPOLLE:
                findViewById(R.id.cipolle).setVisibility(View.VISIBLE);
                break;
            case FORMAGGIO:
                findViewById(R.id.formaggio).setVisibility(View.VISIBLE);
                break;
            case GAMBERETTI:
                findViewById(R.id.gamberetti).setVisibility(View.VISIBLE);
                break;
            case MELANZANE:
                findViewById(R.id.melanzane).setVisibility(View.VISIBLE);
                break;
            case OLIVE:
                findViewById(R.id.olive).setVisibility(View.VISIBLE);
                break;
            case PATATINE:
                findViewById(R.id.patatine).setVisibility(View.VISIBLE);
                break;
            case PEPERONI:
                findViewById(R.id.peperoni).setVisibility(View.VISIBLE);
                break;
            case PEPERONCINI:
                findViewById(R.id.peperoncini).setVisibility(View.VISIBLE);
                break;
            case POMODORI:
                findViewById(R.id.pomodori).setVisibility(View.VISIBLE);
                break;
            case SALAME:
                findViewById(R.id.salame).setVisibility(View.VISIBLE);
                break;
            case UOVA:
                findViewById(R.id.uova).setVisibility(View.VISIBLE);
                break;
            case WURSTEL:
                findViewById(R.id.wurstel).setVisibility(View.VISIBLE);
                break;
            case ZUCCHINE:
                findViewById(R.id.zucchine).setVisibility(View.VISIBLE);
                break;
            case COTTO:
                findViewById(R.id.cotto).setVisibility(View.VISIBLE);
                break;
            case ACCIUGHE:
                findViewById(R.id.acciughe).setVisibility(View.VISIBLE);
                break;
            case CAPPERI:
                findViewById(R.id.capperi).setVisibility(View.VISIBLE);
                break;
            default:
                Log.d("No Found", "ingrediente non ancora disponibile");
                break;

        }
    }


    private void setGoneIngrediente(ListaIngrediente ingrediente){

        switch (ingrediente.getNome()) {
            case SUGO:
                findViewById(R.id.sugo).setVisibility(View.GONE);
                break;
            case MOZZARELLA:
                findViewById(R.id.mozzarella).setVisibility(View.GONE);
                break;
            case BASILICO:
                findViewById(R.id.basilico).setVisibility(View.GONE);
                break;
            case FUNGHI:
                findViewById(R.id.funghi).setVisibility(View.GONE);
            case BECON:
                findViewById(R.id.becon).setVisibility(View.GONE);
                break;
            case BROCCOLI:
                findViewById(R.id.broccoli).setVisibility(View.GONE);
                break;
            case CIPOLLE:
                findViewById(R.id.cipolle).setVisibility(View.GONE);
                break;
            case FORMAGGIO:
                findViewById(R.id.formaggio).setVisibility(View.GONE);
                break;
            case GAMBERETTI:
                findViewById(R.id.gamberetti).setVisibility(View.GONE);
                break;
            case MELANZANE:
                findViewById(R.id.melanzane).setVisibility(View.GONE);
                break;
            case OLIVE:
                findViewById(R.id.olive).setVisibility(View.GONE);
                break;
            case PATATINE:
                findViewById(R.id.patatine).setVisibility(View.GONE);
                break;
            case PEPERONI:
                findViewById(R.id.peperoni).setVisibility(View.GONE);
                break;
            case PEPERONCINI:
                findViewById(R.id.peperoncini).setVisibility(View.GONE);
                break;
            case POMODORI:
                findViewById(R.id.pomodori).setVisibility(View.GONE);
                break;
            case SALAME:
                findViewById(R.id.salame).setVisibility(View.GONE);
                break;
            case UOVA:
                findViewById(R.id.uova).setVisibility(View.GONE);
                break;
            case WURSTEL:
                findViewById(R.id.wurstel).setVisibility(View.GONE);
                break;
            case ZUCCHINE:
                findViewById(R.id.zucchine).setVisibility(View.GONE);
                break;
            case COTTO:
                findViewById(R.id.cotto).setVisibility(View.GONE);
                break;
            case CAPPERI:
                findViewById(R.id.capperi).setVisibility(View.GONE);
                break;
            case ACCIUGHE:
                findViewById(R.id.acciughe).setVisibility(View.GONE);
                break;
            default:
                Log.d("No Found", "ingrediente non ancora disponibile");
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
// aggiunge ingrediente nella pizza e lo elimina dalla lista degli ingredienti da inserire
            if (!(Pizza.trovaIngredientiInseriti(listingredienti,imageClick))) {
                listingredienti.get(Pizza.trovaIngrediente(listingredienti,imageClick)).addIngrediente();
                countIngredienti++;
                updatePizza();
                Toast.makeText(CreaPizza.this,"Hai aggiunto "+ imageClick,Toast.LENGTH_SHORT).show();
                v.setVisibility(View.GONE);

            } /*else{//ingrediente gia inserito nell immagine allora toglielo
                if ((Pizza.trovaIngredientiInseriti(listingredienti, imageClick))) {
                    listingredienti.get(Pizza.trovaIngrediente(listingredienti, imageClick)).setIngrediente(0);
                    countIngredienti--;
                    updatePizza();
                    Toast.makeText(CreaPizza.this,"Hai rimosso "+ imageClick,Toast.LENGTH_SHORT).show();
                }
            }*/
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
                           updatePizza();
                            setGoneIngrediente(listingredienti.get(Pizza.trovaIngrediente(listingredienti, draggedImageText)));

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
    Bitmap bm = null;
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
        case BECON:
          //  bm = BitmapFactory.decodeResource(resources, R.drawable.becon);
            break;
        case BROCCOLI:
           // bm = BitmapFactory.decodeResource(resources, R.drawable.broccoli);
            break;
        case CIPOLLE:
          //  bm = BitmapFactory.decodeResource(resources, R.drawable.cipolle);
            break;
        case FORMAGGIO:
           // bm = BitmapFactory.decodeResource(resources, R.drawable.formaggio);
            break;
        case GAMBERETTI:
            //bm = BitmapFactory.decodeResource(resources, R.drawable.gamberetti);
            break;
        case MELANZANE:
           // bm = BitmapFactory.decodeResource(resources, R.drawable.melanzane);
            break;
        case OLIVE:
          //  bm = BitmapFactory.decodeResource(resources, R.drawable.olive);
            break;
        case PATATINE:
           // bm = BitmapFactory.decodeResource(resources, R.drawable.patatine);
            break;
        case PEPERONI:
           // bm = BitmapFactory.decodeResource(resources, R.drawable.peperoni);
            break;
        case PEPERONCINI:
           // bm = BitmapFactory.decodeResource(resources, R.drawable.peperoncini);
            break;
        case POMODORI:
           // bm = BitmapFactory.decodeResource(resources, R.drawable.pomodori);
            break;
        case SALAME:
           // bm = BitmapFactory.decodeResource(resources, R.drawable.salame);
            break;
        case UOVA:
            //bm = BitmapFactory.decodeResource(resources, R.drawable.uova);
            break;
        case WURSTEL:
            //bm = BitmapFactory.decodeResource(resources, R.drawable.wurstel);
            break;
        case ZUCCHINE:
            //bm = BitmapFactory.decodeResource(resources, R.drawable.zucchine);
            break;
        case CAPPERI:
            //bm = BitmapFactory.decodeResource(resources, R.drawable.capperi);
            break;
        case ACCIUGHE:
            //bm = BitmapFactory.decodeResource(resources, R.drawable.acciughe);
            break;
        case COTTO:
            //bm = BitmapFactory.decodeResource(resources, R.drawable.cotto);
            break;
        default:
            Toast.makeText(CreaPizza.this,"Ci siamo dimenticati un ingrediente", Toast.LENGTH_SHORT).show();
            break;
    }
    return bm;
}





private void updatePizza() {
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
