package it.unica.ium.pizzalove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by perlo on 14/02/16.
 */
public class Scelta extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta);

        Button btnElenco = (Button) findViewById(R.id.btnElenco);
        Button btnCrea = (Button) findViewById(R.id.btnCrea);




        btnElenco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Scelta.this, ElencoPizze.class);
                /*Bundle b = getIntent().getExtras();
                intent.putExtras(b);*/

                startActivityForResult(intent, 0);

            }
        });

        btnCrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Scelta.this, CreaPizza.class);
                /*Bundle b = getIntent().getExtras();
                intent.putExtras(b);*/
                startActivityForResult(intent, 0);

            }
        });
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }



}
