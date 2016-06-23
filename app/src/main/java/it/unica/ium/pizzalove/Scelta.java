package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Scelta extends Activity {

    Bundle bundle = new Bundle();

    @Override
    public void onSaveInstanceState(Bundle onSaveInstanceState){
        super.onSaveInstanceState(onSaveInstanceState);
        onSaveInstanceState.putAll(bundle);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta);

        Button btnElenco = (Button) findViewById(R.id.btnElenco);
        Button btnCrea = (Button) findViewById(R.id.btnCrea);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-BoldItalic.ttf");
        btnElenco.setTypeface(font);
        btnCrea.setTypeface(font);

        bundle = getIntent().getExtras();

        if(savedInstanceState != null)
            bundle = savedInstanceState;

        switch(getResources().getResourceName(bundle.getInt(Main.PIZZERIA))){
            case "it.unica.ium.pizzalove:drawable/pizzeria1":
                findViewById(R.id.imgPizzeria)
                        .setBackgroundResource(getResources().getIdentifier("@drawable/pizza_info1", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria2":
                findViewById(R.id.imgPizzeria)
                        .setBackgroundResource(getResources().getIdentifier("@drawable/pizza_info2", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria3":
                findViewById(R.id.imgPizzeria)
                        .setBackgroundResource(getResources().getIdentifier("@drawable/pizza_info3", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria4":
                findViewById(R.id.imgPizzeria)
                        .setBackgroundResource(getResources().getIdentifier("@drawable/pizza_info4", null, getPackageName()));
                break;
            case "it.unica.ium.pizzalove:drawable/pizzeria5":
                findViewById(R.id.imgPizzeria)
                        .setBackgroundResource(getResources().getIdentifier("@drawable/pizza_info5", null, getPackageName()));
                break;
            default:
                break;
        }


        btnElenco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Scelta.this, ElencoPizze.class).putExtras(bundle), 0);
            }
        });

        btnCrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Scelta.this, CreaPizza.class).putExtras(bundle), 0);
            }
        });

    }
}
