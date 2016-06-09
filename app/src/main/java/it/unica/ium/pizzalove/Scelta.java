package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by perlo on 14/02/16.
 */
public class Scelta extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta);

        Button btnElenco = (Button) findViewById(R.id.btnElenco);
        Button btnCrea = (Button) findViewById(R.id.btnCrea);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Art Brewery.ttf");
        btnElenco.setTypeface(font);
        btnCrea.setTypeface(font);

        final Bundle bundle = getIntent().getExtras();

        /**
         **********************MODIFICARE IMMAGINE CON L'EXTRA Main.PIZZERIA************************
         */

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
