package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

public class Main extends Activity {


    private static final String ORIENTATION_CHANGED = "it.unica.ium.pizzalove.oriantationChanged";
    public static final String PIZZERIA = "it.unica.ium.pizzalove.PIZZERIA";
    public static final String TEXT_INDIRIZZO = "it.unica.ium.pizzalove.TEXT_INDIRIZZO";
    private DelayAutoCompleteTextView geo_autocomplete;
    private ImageView geo_autocomplete_clear;
    private boolean flag = false;
    private Button btn1, btn2, btn3, btn4, btn5;
    private Bundle bundle = new Bundle();

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString(TEXT_INDIRIZZO, geo_autocomplete.getText().toString());
        savedInstanceState.putBoolean(ORIENTATION_CHANGED, flag);
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bottoni pizzeria
        btn1 = (Button)findViewById(R.id.bottonePizzeria1);
        btn2 = (Button)findViewById(R.id.bottonePizzeria2);
        btn3 = (Button)findViewById(R.id.bottonePizzeria3);
        btn4 = (Button)findViewById(R.id.bottonePizzeria4);
        btn5 = (Button)findViewById(R.id.bottonePizzeria5);

        geo_autocomplete_clear = (ImageView) findViewById(R.id.geo_autocomplete_clear);
        geo_autocomplete = (DelayAutoCompleteTextView) findViewById(R.id.geo_autocomplete);
        Integer THRESHOLD = 2;
        geo_autocomplete.setThreshold(THRESHOLD);
        geo_autocomplete.setAdapter(new GeoAutoCompleteAdapter(this));


        if(savedInstanceState != null){
            if(savedInstanceState.getBoolean(ORIENTATION_CHANGED)){
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.VISIBLE);
            }
            geo_autocomplete.setText(savedInstanceState.getString(TEXT_INDIRIZZO));
        }

        geo_autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GeoSearchResult result = (GeoSearchResult) adapterView.getItemAtPosition(position);
                geo_autocomplete.setText(result.getAddress());
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                addPizzerie();
                flag = true;
            }
        });

        geo_autocomplete.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    geo_autocomplete_clear.setVisibility(View.VISIBLE);
                } else {
                    geo_autocomplete_clear.setVisibility(View.GONE);
                }
            }
        });

        geo_autocomplete_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geo_autocomplete.setText("");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bundle.putInt(PIZZERIA, R.drawable.pizzeria1);
                startActivity();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bundle.putInt(PIZZERIA, R.drawable.pizzeria2);
                startActivity();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bundle.putInt(PIZZERIA, R.drawable.pizzeria3);
                startActivity();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bundle.putInt(PIZZERIA, R.drawable.pizzeria4);
                startActivity();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bundle.putInt(PIZZERIA, R.drawable.pizzeria5);
                startActivity();
            }
        });

    }

    private void startActivity(){
        bundle.putString(TEXT_INDIRIZZO, geo_autocomplete.getText().toString());
        Intent intent = new Intent(Main.this, Scelta.class).putExtras(bundle);
        startActivityForResult(intent, 0);
    }

    private void addPizzerie() {
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn4.setVisibility(View.VISIBLE);
        btn5.setVisibility(View.VISIBLE);
    }
}
