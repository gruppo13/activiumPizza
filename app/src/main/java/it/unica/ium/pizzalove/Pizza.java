package it.unica.ium.pizzalove;

import android.support.annotation.NonNull;
import android.widget.Switch;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


import static java.util.Arrays.*;

/**
 * Created by perlo on 14/02/16.
 */
public class Pizza {
    final static float prezzopartenza=2.0f;


    //final static String[] pizzeClassiche = { "Margherita","Quattro Stagioni", "Napoli"};
   // final static String[] ingredienti = {"sugo","mozzarella","basilico", "funghi" ,"salmone"};


    public enum Ingrediente {
        SUGO, MOZZARELLA, BASILICO, FUNGHI,
        SALMONE, ACCIUGHE, ORIGANO,CAPPERI
    }

    public enum Classica {
        Margherita,QuattroStagioni, Napoli, Creata
    }

    public static Ingrediente getIngredienteS(String cercaIngrediente) {
        switch (cercaIngrediente) {
            case "sugo":
                return Ingrediente.SUGO;
            case "mozzarella":
                return Ingrediente.MOZZARELLA;
            case "basilico":
                return Ingrediente.BASILICO;
            case "funghi":
                return Ingrediente.FUNGHI;
            case "salmone":
                return Ingrediente.SALMONE;
            case "acciughe":
                return Ingrediente.ACCIUGHE;
            case "origano":
                return Ingrediente.ORIGANO;
            case "capperi":
                return Ingrediente.CAPPERI;
            default:
                return null;
        }
    }

    public static String getIngrediente(Ingrediente cercaIngrediente) {
        switch (cercaIngrediente) {
            case SUGO:
                return "sugo";
            case MOZZARELLA:
                return "mozzarella";
            case BASILICO:
                return "basilico";
            case FUNGHI:
                return "funghi";
            case SALMONE:
                return "salmone";
            case ACCIUGHE:
                return "acciughe";
            case ORIGANO:
                return "origano";
            case CAPPERI:
                return "capperi";
            default:
                return "not valid";
        }
    }

    public static Classica getClassicaS(String classica) {
        switch (classica) {
            case "Margherita":
                return Classica.Margherita;
            case "Napoli":
                return Classica.Napoli;
            case "Quattro Stagioni":
                return Classica.QuattroStagioni;
            default:
                return null;
        }
    }

    public static String getClassica(Classica classica) {
        switch (classica) {
            case Margherita:
                return "Margherita";
            case Napoli:
                return "Napoli";
            case QuattroStagioni:
                return "Quattro Stagioni";
            default:
                return "not valid";
        }
    }


    public static List<Ingrediente> getIngredientiClassica (Classica nome){
        List<Ingrediente> ingredientiClassica = new ArrayList<>();
        switch (nome){
            case Margherita:
                ingredientiClassica.add(Ingrediente.SUGO);
                ingredientiClassica.add(Ingrediente.MOZZARELLA);
                break;
            case Napoli:
                ingredientiClassica.add(Ingrediente.SUGO);
                ingredientiClassica.add(Ingrediente.MOZZARELLA);
                ingredientiClassica.add(Ingrediente.CAPPERI);
                ingredientiClassica.add(Ingrediente.ACCIUGHE);
                break;
            case QuattroStagioni:
                ingredientiClassica.add(Ingrediente.SUGO);
                ingredientiClassica.add(Ingrediente.MOZZARELLA);
                break;
            default:

                break;


        }

            return ingredientiClassica;

    }




    public static HashMap<Classica,List<Ingrediente>> getPizzaCreata(HashMap<String, Integer> listingredienti, Classica classica) {
        List<Classica> listDataHeader = new ArrayList<>();
        HashMap<Classica,List<Ingrediente>> listDataChild = new HashMap<>();
        // Adding child data
        listDataHeader.add(classica);
        // Adding child data
        List<Ingrediente>  creazione= new ArrayList<Ingrediente>();
        for (String ingrediente : listingredienti.keySet())
            if (listingredienti.get(ingrediente)>0){
                creazione.add(Pizza.getIngredienteS(ingrediente));
            }
        listDataChild.put(listDataHeader.get(0), creazione); // Header, Child data
        return listDataChild;
    }

    public static HashMap<Classica,List<Ingrediente>> getPizzeClassiche(){
        List<Classica> tmp = new ArrayList<>();
        //listDataHeader = new HashMap<String>();

        HashMap<Classica,List<Ingrediente>> listDataChild = new HashMap<>();
        tmp.add(Classica.Margherita);
        listDataChild.put(tmp.get(0), getIngredientiClassica(Classica.Margherita)) ;

        tmp.add(Classica.Napoli);
        listDataChild.put(tmp.get(1), getIngredientiClassica(Classica.Napoli));

        tmp.add(Classica.QuattroStagioni);
        listDataChild.put(tmp.get(2), getIngredientiClassica(Classica.QuattroStagioni));

        return listDataChild;
    }



/* calcola quante volte un ingrediente viene inserito */

    public static HashMap<String,Integer > resetIngredienti() {
        HashMap <String,Integer> listingredienti = new HashMap<>();
        // Adding child data

        listingredienti.put(Pizza.getIngrediente(Ingrediente.SUGO), 0);
        listingredienti.put(Pizza.getIngrediente(Ingrediente.MOZZARELLA), 0);
        listingredienti.put(Pizza.getIngrediente(Ingrediente.BASILICO), 0);
        listingredienti.put(Pizza.getIngrediente(Ingrediente.ACCIUGHE), 0);
        listingredienti.put(Pizza.getIngrediente(Ingrediente.CAPPERI), 0);
        listingredienti.put(Pizza.getIngrediente(Ingrediente.ORIGANO), 0);
        listingredienti.put(Pizza.getIngrediente(Ingrediente.SALMONE), 0);
        listingredienti.put(Pizza.getIngrediente(Ingrediente.FUNGHI), 0);


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

/* ritorna il costo del ingrediente */
    public static float calcolaCostoIngrediente(Ingrediente ingrediente){
        switch (ingrediente) {
            case SUGO:
                return 0.50f;
            case MOZZARELLA:
                return 0.50f;
            case BASILICO:
                return 0.5f;
            case ACCIUGHE:
                return 0.50f;
            case FUNGHI:
                return 1.50f;
            case ORIGANO:
                return 0.50f;
            case CAPPERI:
                return 0.50f;
            case SALMONE:
                return 1.50f;
            default:
                return 0.0f;
        }
    }




/* ritorna il formato standard */
    public static String formatoPrezzo(float prezzo) {
        DecimalFormat form = new DecimalFormat("0.00");
        return form.format(prezzo) + " €";
    }


    public static boolean namePizzaValid(Classica name){
        switch (name){
            case Margherita:
                return true;

            case Napoli:
                return true;

            case QuattroStagioni:
              return true;
            default:
                return false;
        }

    }

    public static String prezzoPizzaClassica(Classica nome){
        float prezzo;
        switch (nome){
            case Margherita:
                prezzo=3.00f;
                break;
            case Napoli:
                prezzo=4.00f;
                break;
            case QuattroStagioni:
                prezzo=5.00f;
                break;
            default:
                prezzo=0.00f;
                break;
        }
        return formatoPrezzo(prezzo);


    }


}
