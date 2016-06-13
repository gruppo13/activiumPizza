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
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.readystatesoftware.viewbadger.BadgeView;
import java.util.ArrayList;
import java.util.Collections;

public class CreaPizza extends Activity {

    private final static String PIZZA_STATE = "it.unica.ium.pizzalove.PIZZA_STATE";
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
        if(bundle.keySet().contains(Carrello.ELENCO_PIZZE)) {
            elenco = bundle.getParcelableArrayList(Carrello.ELENCO_PIZZE);
            Log.e(Carrello.ELENCO_PIZZE, " caricato");
        }
        if (bundle.keySet().contains(Carrello.PIZZA_MODIFICA) &&
                bundle.getParcelable(Carrello.PIZZA_MODIFICA) != null) {
            for (Ingredienti i : ((Pizza)(bundle.getParcelable(Carrello.PIZZA_MODIFICA))).getIngredienti())
                setBadge(i.toString());
            bundle.remove(Carrello.PIZZA_MODIFICA);
        }
        else if(bundle.keySet().contains(PIZZA_STATE) &&
                bundle.getParcelableArrayList(PIZZA_STATE) != null){
            for(Parcelable i : bundle.getParcelableArrayList(PIZZA_STATE))
                setBadge(i.toString());
            bundle.remove(PIZZA_STATE);
        }
        updatePizza();
    }

    @Override
    public void onSaveInstanceState(Bundle onSaveInstanceState){
        super.onSaveInstanceState(onSaveInstanceState);
        onSaveInstanceState.putParcelableArrayList(Carrello.ELENCO_PIZZE, elenco);
        onSaveInstanceState.putParcelableArrayList(PIZZA_STATE, listIngredienti);
        onSaveInstanceState.putAll(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creapizza);

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

        if(savedInstanceState != null)
            bundle = savedInstanceState;

        imgMain  = (ImageView) findViewById(R.id.imageMain);
        mViewGroup = (ViewGroup)findViewById(R.id.container);

        if(getResources().getBoolean(R.bool.is_landscape))
            updatePrezzo();

        btnNuovaPizza.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Pizza p = new Pizza(listIngredienti);
                elenco.add(p);
                if(getResources().getBoolean(R.bool.is_landscape)) {
                    addPizza();
                    mViewGroup.removeAllViews();
                }
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


    private void rimuoviTutto() {
        listIngredienti.clear();
        bundle.remove(PIZZA_STATE);
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
     * ------------------------CLICK INGREDIENTE----------------------------------------------------
     */
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String imageClick = (String) (v.getContentDescription());
            Log.e("errore grana", imageClick);
            setBadge(imageClick);
            updatePizza();
            updatePrezzo();
        }
    };

    private void updatePrezzo() {
        float totale = 3.f;
        int i = 0;
        for(Ingredienti ingredienti : Ingredienti.values()){
            if(countPizze[i] != 0)
                totale += ingredienti.getPrice() * countPizze[i];
            i++;
        }
        if(getResources().getBoolean(R.bool.is_landscape))
            ((TextView)findViewById(R.id.tot)).setText(Pizza.formatoPrezzo(totale));
    }


    /**
     * Modifica i badge
     * aggiunge gli ingredienti ai badge
     * @param img ingrediente da aggiungere
     */
    private void setBadge(String img) {
        int i = Ingredienti.valueOf(img).getNumber() - 1;
        if(countPizze[i] < 2) {
            badge[i].setText(String.format("%d", ++countPizze[i]));
            badge[i].show();
            if(getResources().getBoolean(R.bool.is_landscape))
                addIngrediente(Ingredienti.valueOf(img), countPizze[i]);
            listIngredienti.add(Ingredienti.valueOf(img));

        }
        else
            Toast.makeText(CreaPizza.this, "-----------------------------", Toast.LENGTH_SHORT).show();
        }


    /**
     * Modifica i badge
     * elimina gli ingredienti dai badge
     * @param ingrediente ingrediente da eliminare
     */
    private Ingredienti eliminaIngrediente(String ingrediente) {
        int i = Ingredienti.valueOf(ingrediente).getNumber() - 1;
        countPizze[i]--;
        if(countPizze[i] == 1){
            badge[i].setText(String.format("%d", 1));
            badge[i].show();
        }
        else
            badge[i].hide();
        if(listIngredienti.contains(Ingredienti.valueOf(ingrediente)))
            listIngredienti.remove(Ingredienti.valueOf(ingrediente));
        updatePizza();
        return(Ingredienti.valueOf(ingrediente));
    }

    private void addIngrediente(Ingredienti ingredienti, int i) {
        ViewGroup newView = (ViewGroup)LayoutInflater.from(this).inflate(R.layout.list_item_ingredientipizza, mViewGroup, false);
        if(listIngredienti.contains(ingredienti)) {
            for(int j = 0; j < mViewGroup.getChildCount(); j++){
                newView = (ViewGroup) mViewGroup.getChildAt(j);
                if(((TextView)newView.findViewById(R.id.txtNomeIngrediente)).getText().equals(ingredienti.toString())){
                    ((TextView) newView.findViewById(R.id.txtNIngredienti)).setText(String.valueOf(i));
                    ((TextView) newView.findViewById(R.id.txtPrezzo)).setText(String.valueOf(Pizza.formatoPrezzo(ingredienti.getPrice()*2)));
                    mViewGroup.removeViewAt(j);
                    mViewGroup.addView(newView);
                }
            }
        }
        else{
            ((TextView) newView.findViewById(R.id.txtNIngredienti)).setText(String.valueOf(i));
            ((TextView) newView.findViewById(R.id.txtNomeIngrediente)).setText(ingredienti.toString());
            ((TextView) newView.findViewById(R.id.txtPrezzo)).setText(String.valueOf(Pizza.formatoPrezzo(ingredienti.getPrice())));
            mViewGroup.addView(newView);
        }

        newView.findViewById(R.id.rmvIngrediente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = ((TextView) ((View) v.getParent()).findViewById(R.id.txtNomeIngrediente)).getText().toString();
                Ingredienti i = eliminaIngrediente(temp);
                if(((TextView)((View)v.getParent()).findViewById(R.id.txtNIngredienti)).getText().toString().equals("2")) {
                    ((TextView) ((View) v.getParent()).findViewById(R.id.txtNIngredienti)).setText("1");
                    ((TextView) ((View) v.getParent()).findViewById(R.id.txtPrezzo)).setText(String.valueOf(Pizza.formatoPrezzo(i.getPrice())));
                }
                else
                    mViewGroup.removeViewAt(mViewGroup.indexOfChild((View) v.getParent()));
            }
        });
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
