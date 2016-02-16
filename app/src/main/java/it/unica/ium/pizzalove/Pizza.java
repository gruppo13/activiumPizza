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

    public static List<ListaPizza> getPizzeClassiche(){
        List<ListaPizza> tmp = new ArrayList<>();
        tmp.add(new ListaPizza(ListaPizza.Classica.Margherita));
        tmp.add(new ListaPizza(ListaPizza.Classica.Napoli));
        tmp.add(new ListaPizza(ListaPizza.Classica.QuattroStagioni));
        return tmp;
    }



    public static List<ListaIngrediente> resetIngredienti() {
        List <ListaIngrediente> listingredienti = new ArrayList<>();

        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.BASILICO, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.ACCIUGHE, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.FUNGHI, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.ORIGANO, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SALMONE, 0));
            return listingredienti;

    }

    /* return l-indice nella quale si trova ingrediente in cui ha valore magg di zero */
    public static boolean trovaIngredientiInseriti(List<ListaIngrediente> listaingredienti, String nomeString) {
        List <ListaIngrediente> listingredienti = new ArrayList<>();
        // Adding child data
        int i=0;
        for (ListaIngrediente ingrediente : listaingredienti){
            if ((ingrediente.getStringNome().equals(nomeString))&& (ingrediente.getCount()>0))
                    return true;

            i++;
        }

        return false;

    }

    public static int trovaIngrediente(List<ListaIngrediente> listaingredienti, String nomeString) {
        List <ListaIngrediente> listingredienti = new ArrayList<>();
        // Adding child data
        int i=0;
        for (ListaIngrediente ingrediente : listaingredienti){
            if (ingrediente.getStringNome().equals(nomeString))
                return i;

            i++;
        }

        return -1;

    }





    public static String totalePrezzo(List<ListaPizza> pizze){
        float prezzo =0.0f;
        for(ListaPizza nuovo: pizze){
            prezzo+=nuovo.getPrezzo();

        }

        return formatoPrezzo(prezzo);

    }

/* ritorna il formato standard */
    public static String formatoPrezzo(float prezzo) {
        DecimalFormat form = new DecimalFormat("0.00");
        return form.format(prezzo) + " €";
    }



    public static void printAll(List<ListaPizza> pizze){
        System.out.println("pizza");
        for(ListaPizza pizza: pizze) {

            System.out.print(pizza.getStringNome());

            for (ListaIngrediente ingrediente :pizza.getIngredienti()){
                System.out.print("nome " + ingrediente.getStringNome() + "nu > " + ingrediente.getCount());
            }

            System.out.println();


        }

    }

    public static int containPizza(List<ListaPizza> lista, String nome){
        int i=0;
        for(ListaPizza pizze: lista){
            if (pizze.getStringNome().equals(nome))
                return i;
            i++;
        }
        return -1;


    }

}
