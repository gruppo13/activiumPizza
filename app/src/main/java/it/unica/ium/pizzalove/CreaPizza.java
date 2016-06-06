package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.readystatesoftware.viewbadger.BadgeView;
import java.util.ArrayList;
import java.util.Collections;

public class CreaPizza extends FragmentActivity {


    protected ImageView imgMain;
    private int[] countPizze = new int[22];
    private Bundle bundle;
    private BadgeView[] badge;
    private ArrayList<Pizza> elenco = new ArrayList<>();
    private ArrayList<Ingredienti> listIngredienti = new ArrayList<>();
    private ViewGroup mViewGroup;

    @Override
    public void onStart() {
        super.onStart();
        if(bundle.keySet().contains(Carrello.ELENCO_PIZZE))
            elenco = bundle.getParcelableArrayList(Carrello.ELENCO_PIZZE);
        if (bundle.keySet().contains(Carrello.PIZZA_MODIFICA) &&
            bundle.getParcelable(Carrello.PIZZA_MODIFICA) != null) {
            for (Ingredienti i : ((Pizza)(bundle.getParcelable(Carrello.PIZZA_MODIFICA))).getIngredienti())
                setBadge(i.toString());
            bundle.putParcelable(Carrello.PIZZA_MODIFICA, null);
        }
        updatePizza();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creapizza);

        imgMain  = (ImageView) findViewById(R.id.imageMain);
        mViewGroup = (ViewGroup)findViewById(R.id.container);
        if(getResources().getBoolean(R.bool.is_landscape))
            addPizza();


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

        bundle = getIntent().getExtras();


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


        Button btnAddPizzaCreate = (Button) findViewById(R.id.btnAddPizzaCreate);
        FloatingActionButton btnNuovaPizza = (FloatingActionButton) findViewById(R.id.addPizza);

        btnNuovaPizza.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Pizza p = new Pizza(listIngredienti);
                elenco.add(p);
                if(getResources().getBoolean(R.bool.is_landscape))
                    addPizza();
                rimuoviTutto();
            }
        });

        btnAddPizzaCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pizza p = new Pizza(listIngredienti);
                elenco.add(p);
                bundle.putParcelableArrayList(Carrello.ELENCO_PIZZE, elenco);
                startActivityForResult(new Intent(CreaPizza.this, Carrello.class).putExtras(bundle), 0);
            }
        });
    }

    private void addPizza() {
    }

    /**
    private void addIngrediente(Ingredienti ingrediente, int nIngrediente) {
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.list_item_ingredientipizza, mIViewGroup, false);

        ((TextView) newView.findViewById(R.id.txtNIngredienti)).setText(String.valueOf(nIngrediente));
        ((TextView) newView.findViewById(R.id.txtNomeIngrediente)).setText(ingrediente.toString());
        ((TextView) newView.findViewById(R.id.txtPrezzo)).setText(ingrediente.getPrice().toString());

        mIViewGroup.addView(newView);
    }

    private void addPizza() {
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.list_item_creapizza, mViewGroup, false);

        newView.findViewById(R.id.btnReloadPizza).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        mViewGroup.addView(newView);
    }

    /**
 * -----------------------------------------RIMUOVI INGREDIENTI-------------------------------------
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
                rimuoviTutto();
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
}*/

    private void rimuoviTutto() {
        listIngredienti.clear();
        for (int i = 0; i < countPizze.length; i++) {
            badge[i].hide();
            countPizze[i] = 0;
        }
        updatePizza();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "true":
                Log.d("check ", "first case");
                return true;
            case "bo":
                Log.d("check ", "second case");
                return super.onContextItemSelected(item);
            default:
                return false;
        }
    }


    /**
     * --------------------------------------INUTILIZZATO-------------------------------------------
     * rende visibile gli ingredienti
     * */
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


    /**
     * ------------------------CLICK INGREDIENTE----------------------------------------------------
     */
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String imageClick = (String) (v.getContentDescription());
            Log.e("errore grana", imageClick);
            setBadge(imageClick);
            updatePizza();
        }
    };

    /**
     * Modifica i badge
     * @param img
     */
    private void setBadge(String img) {
        int i = Ingredienti.valueOf(img).getNumber() - 1;
        if(countPizze[i] < 2) {
            badge[i].setText(String.format("%d", ++countPizze[i]));
            badge[i].show();
            listIngredienti.add(Ingredienti.valueOf(img));
            if(getResources().getBoolean(R.bool.is_landscape))
                addIngrediente(Ingredienti.valueOf(img), countPizze[i]);
        }
        else{
            //nuovaPizza.sort();
            Collections.sort(listIngredienti);
            while(listIngredienti.contains(Ingredienti.valueOf(img)))
                listIngredienti.remove(Ingredienti.valueOf(img));
            badge[i].hide();
            countPizze[i] = 0;
        }
    }

    private void addIngrediente(Ingredienti ingredienti, int i) {
        final ViewGroup newView = (ViewGroup)LayoutInflater.from(this).inflate(R.layout.list_item_ingredientipizza, mViewGroup, false);
        ((TextView)newView.findViewById(R.id.txtNIngredienti)).setText(String.valueOf(i));
        ((TextView)newView.findViewById(R.id.txtNomeIngrediente)).setText(ingredienti.toString());
        ((TextView)newView.findViewById(R.id.txtPrezzo)).setText(String.valueOf(ingredienti.getPrice()));

        newView.findViewById(R.id.rmvIngrediente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mViewGroup.addView(newView);
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
        Drawable[] layers = new Drawable[listIngredienti.size()+ 1];
        Resources resources = getResources();
        ImageView imgMain = (ImageView) findViewById(R.id.imageMain);
        LayerDrawable layerDrawable;
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inMutable = true;
        Bitmap bm = BitmapFactory.decodeResource(resources, R.drawable.pastapizza);
        BitmapDrawable bmd = new BitmapDrawable(resources, bm);
        bmd.setGravity(Gravity.TOP);
        layers[0] = bmd;

        int i = 1;
        if (!listIngredienti.isEmpty()){
            //ordina ingredienti
            Collections.sort(listIngredienti);
            for (Ingredienti ingrediente : listIngredienti) {
                Log.e("i tuoi ingredienti ", ingrediente.toString());
                bmd = new BitmapDrawable(resources, trovaIngredienteBitmap(ingrediente, resources));
                bmd.setGravity(Gravity.TOP);
                layers[i] = bmd;
                i++;
            }
            findViewById(R.id.btnAddPizzaCreate).setEnabled(true);
            findViewById(R.id.addPizza).setEnabled(true);
        }
        else{//non ci sono ingredienti quindi non puoi creare una pizza vuota
            findViewById(R.id.btnAddPizzaCreate).setEnabled(false);
            findViewById(R.id.addPizza).setEnabled(false);
        }
        layerDrawable = new LayerDrawable(layers);
        imgMain.setImageDrawable(layerDrawable);
    }


    @Override
    public void onStop() {
        super.onStop();
    }
}
