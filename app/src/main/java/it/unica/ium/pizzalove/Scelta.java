package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by perlo on 14/02/16.
 */
public class Scelta extends Activity {

    private List<Pizza> elenco;

    @Override
    protected void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        if(elenco.size() == 0)
            state.putSerializable(Bundles.ELENCO_PIZZE.getBundle(), null);
        else
            state.putSerializable(Bundles.ELENCO_PIZZE.getBundle(), (Serializable) elenco);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        elenco = (List<Pizza>)savedInstanceState.getSerializable(Bundles.ELENCO_PIZZE.getBundle());
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        elenco = (List<Pizza>)savedInstanceState.getSerializable(Bundles.ELENCO_PIZZE.getBundle());

        Button btnElenco = (Button) findViewById(R.id.btnElenco);
        Button btnCrea = (Button) findViewById(R.id.btnCrea);




        btnElenco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveInstanceState(new Bundle());
                startActivityForResult(new Intent(Scelta.this, ElencoPizze.class), 0);

            }
        });

        btnCrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveInstanceState(new Bundle());
                startActivityForResult(new Intent(Scelta.this, CreaPizza.class), 0);

            }
        });
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }



}
