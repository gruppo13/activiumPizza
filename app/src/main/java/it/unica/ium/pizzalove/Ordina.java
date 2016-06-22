package it.unica.ium.pizzalove;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Ordina extends Activity {

    private static final String NOME_CONSEGNA = "it.unica.ium.pizzalove.NOME_CONSEGNA";
    private ArrayList<Pizza> elenco = new ArrayList<>();
    private Bundle bundle;
    private String viaAttuale = null;
    private String nomeAttuale = "";


    @Override
    public void onSaveInstanceState(Bundle onSaveInstanceState) {
        super.onSaveInstanceState(onSaveInstanceState);
        if(viaAttuale != null)
            bundle.putString(Main.TEXT_INDIRIZZO, viaAttuale);
        bundle.putString(NOME_CONSEGNA, nomeAttuale);
        onSaveInstanceState.putAll(bundle);
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState){

        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_ordina);
        bundle = getIntent().getExtras();

        if(onSavedInstanceState != null){
            bundle = onSavedInstanceState;
            if(bundle.keySet().contains(NOME_CONSEGNA)) {
                nomeAttuale = bundle.getString(NOME_CONSEGNA);
                ((EditText)findViewById(R.id.edtNome)).setText(nomeAttuale);
            }
        }

        elenco = bundle.getParcelableArrayList(Carrello.ELENCO_PIZZE);
        float totale = bundle.getFloat(Carrello.TOTALE);

        ViewGroup mViewGroup = (ViewGroup)findViewById(R.id.lblListOrdina);
        Button btnAnnulla = (Button)findViewById(R.id.btnAnnulla);
        Button btnPaga = (Button)findViewById(R.id.btnPaga);

        String tot = "Paga " + Pizza.formatoPrezzo(totale);
        btnPaga.setText(tot);

        float totCreate = 0.f;
        int count = 0;
        ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.listgroup_ordina, mViewGroup, false);
        for(Pizza p : elenco) {
            /**
             * evitare creazioni 0
             * metodo formatta nome pizza
             */
            if(p.getNomePizza().equals("creata")){
                count++;
                totCreate += p.getPrezzo();
            }
            else {
                ((TextView) newView.findViewById(R.id.txtNPizze)).setText(String.valueOf(p.getCount()));
                ((TextView) newView.findViewById(R.id.txtNomePizza)).setText(p.getNomePizza());
                ((TextView) newView.findViewById(R.id.txtResumePrezzo)).setText(Pizza.formatoPrezzo(p.getPrezzo()*p.getCount()));
                mViewGroup.addView(newView);
            }
        }
        ViewGroup newView2 = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.listgroup_ordina, mViewGroup, false);
        ((TextView) newView2.findViewById(R.id.txtNPizze)).setText(String.valueOf(count));
        ((TextView) newView2.findViewById(R.id.txtNomePizza)).setText("Creazioni");
        ((TextView) newView2.findViewById(R.id.txtResumePrezzo)).setText(Pizza.formatoPrezzo(totCreate));
        mViewGroup.addView(newView2);

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

        ((EditText)findViewById(R.id.edtIndirizzo)).setText(bundle.getString(Main.TEXT_INDIRIZZO));

        ((EditText)findViewById(R.id.edtIndirizzo)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                viaAttuale = s.toString();
            }
        });

        ((EditText)findViewById(R.id.edtNome)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nomeAttuale = s.toString();
            }
        });

        btnAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Ordina.this, Carrello.class).putExtras(bundle), 0);
            }
        });

        btnPaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
