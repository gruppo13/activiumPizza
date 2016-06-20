package it.unica.ium.pizzalove;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

        if(onSavedInstanceState != null){
            bundle = onSavedInstanceState;
        }

        elenco = bundle.getParcelableArrayList(Carrello.ELENCO_PIZZE);
        totale = bundle.getFloat(Carrello.TOTALE);

        ImageView pizzeria  = (ImageView)findViewById(R.id.resumePizzeria);
        EditText viaConsegna = (EditText)findViewById(R.id.edtIndirizzo);
        ScrollView listaPizze = (ScrollView)findViewById(R.id.lblListOrdina);
        TextView resumeTotale = (TextView)findViewById(R.id.resumeTotale);
        Button btnAnnulla = (Button)findViewById(R.id.btnAnnulla);
        Button btnPaga = (Button)findViewById(R.id.btnPaga);

        switch(getResources().getResourceName(bundle.getInt(Main.PIZZERIA))){
            case "it.unica.ium.pizzalove:drawable/pizzeria1":
                pizzeria.setBackgroundResource(getResources().getIdentifier("@drawable/tarcisio", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria2":
                pizzeria.setBackgroundResource(getResources().getIdentifier("@drawable/italian_pizza", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria3":
                pizzeria.setBackgroundResource(getResources().getIdentifier("@drawable/pizza74", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria4":
                pizzeria.setBackgroundResource(getResources().getIdentifier("@drawable/baffo_pizza", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria5":
                pizzeria.setBackgroundResource(getResources().getIdentifier("@drawable/a_tutta_pizza", null, getPackageName()));
                break;
            default:
                break;
        }

        viaConsegna.setText(bundle.getString(Main.TEXT_INDIRIZZO));
        resumeTotale.setText(Pizza.formatoPrezzo(totale));

    }
}
