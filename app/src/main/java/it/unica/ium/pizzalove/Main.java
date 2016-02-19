package it.unica.ium.pizzalove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

/**
 * Created by perlo on 14/02/16.
 */
public class Main extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn = (Button) findViewById(R.id.btnOrdina);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(),CreaPizza.class);
                //startActivityForResult(intent,0);
                Intent intent = new Intent(Main.this, Scelta.class);
                Bundle b = new Bundle();
                b.putInt("creata", 0);
                intent.putExtras(b);

                startActivityForResult(intent, 0);
               // finish();


            }
        });

/*
        ListView view = (ListView) findViewById(R.id.expListPizzerie);
        view.addHeaderView(findViewById();

*/
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }



}
