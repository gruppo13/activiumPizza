package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Art Brewery.ttf");
        btnElenco.setTypeface(font);
        btnCrea.setTypeface(font);

        bundle = getIntent().getExtras();

        if(savedInstanceState != null)
            bundle = savedInstanceState;

        findViewById(R.id.imgPizzeria).setBackgroundResource(bundle.getInt(Main.PIZZERIA));

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
