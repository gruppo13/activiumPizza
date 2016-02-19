package it.unica.ium.pizzalove;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by perlo on 14/02/16.
 */
public class Main extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn = (Button) findViewById(R.id.button);
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

      /*  ListView view = (ListView) findViewById(R.id.expListPizzerie);
        LayoutInflater infalInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view.addHeaderView(infalInflater.inflate(R.layout.list_item, null));

*/
        /*LayoutInflater infalInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = (ListView) infalInflater.inflate(R.layout.list_item, null);
*/



        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }



}
