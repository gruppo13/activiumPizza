package it.unica.ium.pizzalove;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class Ordina extends Activity {

    private ArrayList<Pizza> elenco = new ArrayList<>();
    private Bundle bundle;
    private float totale;


    @Override
    public void onSaveInstanceState(Bundle onSaveInstanceState) {
        super.onSaveInstanceState(onSaveInstanceState);
        onSaveInstanceState.putAll(bundle);
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState){

        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_ordina);
        bundle = getIntent().getExtras();

        if(onSavedInstanceState != null){
            bundle = onSavedInstanceState;
        }

        elenco = bundle.getParcelableArrayList(Carrello.ELENCO_PIZZE);
        totale = bundle.getFloat(Carrello.TOTALE);

        ViewGroup mViewGroup = (ViewGroup)findViewById(R.id.lblListOrdina);
        Button btnAnnulla = (Button)findViewById(R.id.btnAnnulla);
        Button btnPaga = (Button)findViewById(R.id.btnPaga);

        for(Pizza p : elenco) {
            ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.listgroup, mViewGroup, false);
            ((TextView) newView.findViewById(R.id.lblListHeader)).setText(String.valueOf(p.getCount()));
            ((TextView) newView.findViewById(R.id.lblListHeaderPrezzo)).setText(p.getNomePizza());
            mViewGroup.addView(newView);
        }

        switch(getResources().getResourceName(bundle.getInt(Main.PIZZERIA))){
            case "it.unica.ium.pizzalove:drawable/pizzeria1":
                findViewById(R.id.resumePizzeria).setBackgroundResource(getResources().getIdentifier("@drawable/tarcisio", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria2":
                findViewById(R.id.resumePizzeria).setBackgroundResource(getResources().getIdentifier("@drawable/italian_pizza", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria3":
                findViewById(R.id.resumePizzeria).setBackgroundResource(getResources().getIdentifier("@drawable/pizza74", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria4":
                findViewById(R.id.resumePizzeria).setBackgroundResource(getResources().getIdentifier("@drawable/baffo_pizza", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria5":
                findViewById(R.id.resumePizzeria).setBackgroundResource(getResources().getIdentifier("@drawable/a_tutta_pizza", null, getPackageName()));
                break;
            default:
                break;
        }

        ((TextView)findViewById(R.id.edtIndirizzo)).setText(bundle.getString(Main.TEXT_INDIRIZZO));
        ((TextView)findViewById(R.id.resumeTotale)).setText(Pizza.formatoPrezzo(totale));

    }
}
