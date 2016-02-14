package it.unica.ium.pizzalove;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by perlo on 14/02/16.
 */
public class Pizza {
    final static float prezzopartenza=2.0f;
    //String[10] pizze = new String {"Margherita","Quattro Stagioni", "Napoli"};


    public static HashMap<String,Integer > resetIngredienti() {
        HashMap <String,Integer> listingredienti = new HashMap<String, Integer>();
        // Adding child data
        listingredienti.put("sugo", 0); // Header, Child data
        listingredienti.put("mozzarella", 0);
        listingredienti.put("basilico", 0);
        listingredienti.put("funghi", 0);
        listingredienti.put("salmone", 0);
        return listingredienti;

    }

/*
    public static HashMap<String,Integer> updateCarrello() {
        ArrayList<String> listDataHeader = new ArrayList<String>();
        HashMap<String,Integer> listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Margherita");
        listDataHeader.add("Napoli");
        listDataHeader.add("Quattro Stagioni");

        // Adding child data
        List<String> margherita = new ArrayList<String>();
        margherita.add("sugo");
        margherita.add("mozzarella");


        List<String> napoli = new ArrayList<String>();
        napoli.add("sugo");
        napoli.add("sugo");
        napoli.add("mozzarella");
        napoli.add("sugo");

        List<String> quattroStagioni = new ArrayList<String>();
        quattroStagioni.add("sugo");
        quattroStagioni.add("mozzarella");
        quattroStagioni.add("funghi");

        listDataChild.put(listDataHeader.get(0), margherita); // Header, Child data
        listDataChild.put(listDataHeader.get(1), napoli);
        listDataChild.put(listDataHeader.get(2), quattroStagioni);
    }*/

    /* trova la pizza in base agli ingredienti */
    public static void trovaPizzaIngredienti(){


    }


    public static  float calcolaCostoIngrediente(String ingrediente){
        switch (ingrediente){
            case "sugo":
                return 0.50f;
            case "mozzarella":
                return 0.50f;
            case "funghi":
                return 1.50f;
            case "salmone":
                return 1.50f;
            case "basilico":
                return 0.50f;
            default:
                return 0.0f;

        }


    }

    public static String formatoPrezzo(float prezzo) {
        DecimalFormat form = new DecimalFormat("0.00");
        return form.format(prezzo) + " €";
    }


    public static boolean namePizzaValid(String name){
        switch (name){
            case "Margherita":
                return true;

            case "Napoli":
                return true;

            case "Quattro Stagioni":
              return true;
            default:
                return false;
        }

    }

    public static String trovaPrezzo(String nome){
        float prezzo;
        switch (nome){
            case "Margherita":
                prezzo=3.00f;
                break;
            case "Napoli":
                prezzo=4.00f;
                break;
            case "Quattro Stagioni":
                prezzo=5.00f;
                break;
            default:
                prezzo=0.00f;
                break;
        }
        return formatoPrezzo(prezzo);


    }


}
