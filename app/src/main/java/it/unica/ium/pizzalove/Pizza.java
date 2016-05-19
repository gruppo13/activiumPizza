package it.unica.ium.pizzalove;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by manuf_000 on 22/02/16.
 */
public class Pizza implements Serializable{

    private int COUNT = 1;
    private String nomePizza;
    private List<Ingredienti> listaIngredienti = new ArrayList<>();


    public Pizza(String nomePizza){
        this.nomePizza = nomePizza;
        switch(nomePizza){
            case "Margherita":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella);
                break;
            case "Napoli":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Capperi,
                        Ingredienti.Acciughe, Ingredienti.Basilico);
                break;
            case "Wurstel e Cipolle":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Wurstel,
                        Ingredienti.Cipolle);
                break;
            case "Funghi":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Funghi);
                break;
            case "Capricciosa":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Funghi,
                        Ingredienti.Cotto, Ingredienti.Uova, Ingredienti.Wurstel);
                break;
            case "Prosciutto e Funghi":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Funghi,
                        Ingredienti.Cotto);
                break;
            case "All American":
                this.listaIngredienti =  Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Patatine,
                        Ingredienti.Wurstel);
                break;
            case "Vegetariana":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Zucchine,
                        Ingredienti.Melanzane, Ingredienti.Peperoni);
                break;
            case "Carbonara":
                this.listaIngredienti = Arrays.asList(Ingredienti.Mozzarella, Ingredienti.Uova, Ingredienti.Bacon);
                break;
            case "Parmigiana":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Melanzane,
                        Ingredienti.Grana);
                break;
            case "Cotto":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Cotto);
                break;
            default:
                break;
        }
    }

    public Pizza(List<Ingredienti> list){
        this.nomePizza = "creata";
        for(Ingredienti i : list){
            this.listaIngredienti.add(i);
        }
    }

    public String getNomePizza(){
        return this.nomePizza;
    }

    public Ingredienti getIngrediente(int i){
        return listaIngredienti.get(i);
    }


    public List<Ingredienti> getIngredienti(){
        sort();
        return this.listaIngredienti;
    }

    public void sort() {
        Collections.sort(listaIngredienti);
    }

    public int countIngredienti(){
        return listaIngredienti.size();
    }


    public boolean removeIngrediente(Ingredienti i){
       try {
           listaIngredienti.remove(i);
       }
        catch (Exception r){
            r.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeIngredienti(){
        try {
            listaIngredienti.clear();
        }
        catch(Exception r){
            r.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addIngrediente(Ingredienti i){
        try{
            listaIngredienti.add(i);
        }
        catch (Exception r){
            r.printStackTrace();
            return false;
        }
        return true;
    }

    public void addCount(){
        ++COUNT;
    }

    public int getCount(){
        return COUNT;
    }

    public Float getPrezzo(){
        Float totale = 3.0f;
        for (Ingredienti i : listaIngredienti){
            totale += i.getPrice();
        }
        return totale;
    }

    public static int containPizza(List<Pizza> lista, String nome){
        int i=0;
        for(Pizza pizze: lista){
            if (pizze.getNomePizza().equals(nome))
                return i;
            i++;
        }
        return -1;
    }

    /* ritorna il formato standard */
    public static String formatoPrezzo(float prezzo) {
        DecimalFormat form = new DecimalFormat("0.00");
        return form.format(prezzo) + " €";
    }


/*
    public static final Parcelable.Creator<Pizza> CREATOR = new
            Parcelable.Creator<Pizza>() {
                public Pizza createFromParcel(Parcel in) {
                    return new Pizza(in);
                }

                public Pizza[] newArray(int size) {
                    return new Pizza[size];
                }
            };


    private Pizza(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        this.COUNT = in.readInt();
        this.nomePizza = in.readString();
        in.readArrayList((ClassLoader) listaIngredienti);
        /*for(Ingredienti i : in.readArrayList()) {
            this.listaIngredienti.add(i);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(COUNT);
        out.writeString(nomePizza);
        out.writeList(listaIngredienti);
    }



    public static List<ListaPizza> getPizzeClassiche(){
        List<ListaPizza> tmp = new ArrayList<>();
        tmp.add(new ListaPizza(ListaPizza.Classica.Margherita));
        tmp.add(new ListaPizza(ListaPizza.Classica.Napoli));
        tmp.add(new ListaPizza(ListaPizza.Classica.WurstelCipolle));
        tmp.add(new ListaPizza(ListaPizza.Classica.Funghi));
        tmp.add(new ListaPizza(ListaPizza.Classica.Cotto));
        tmp.add(new ListaPizza(ListaPizza.Classica.Capricciosa));
        tmp.add(new ListaPizza(ListaPizza.Classica.Vegetariana));
        tmp.add(new ListaPizza(ListaPizza.Classica.ProsciuttoFunghi));
        tmp.add(new ListaPizza(ListaPizza.Classica.AllAmerican));
        tmp.add(new ListaPizza(ListaPizza.Classica.Carbonara));
        tmp.add(new ListaPizza(ListaPizza.Classica.Parmigiana));
        return tmp;
    }



    public static List<ListaIngrediente> resetIngredienti() {
        List <ListaIngrediente> listIngredienti = new ArrayList<>();

        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.BASILICO, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.ACCIUGHE, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.FUNGHI, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.BECON, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.BROCCOLI, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.CIPOLLE, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.FORMAGGIO, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.GAMBERETTI, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MELANZANE, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.OLIVE, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.PATATINE, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.PEPERONCINI, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.PEPERONI, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.POMODORI, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SALAME, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.UOVA, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.WURSTEL, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.ZUCCHINE, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.COTTO, 0));
        listIngredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.CAPPERI, 0));
        return listIngredienti;
    }

    /* return l-indice nella quale si trova ingrediente in cui ha valore magg di zero
    public static boolean trovaIngredientiInseriti(List<ListaIngrediente> listaingredienti, String nomeString) {
        List <ListaIngrediente> listIngredienti = new ArrayList<>();
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
        List <ListaIngrediente> listIngredienti = new ArrayList<>();
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
            prezzo+=nuovo.getPrezzo()*nuovo.getCount();

        }
        return formatoPrezzo(prezzo);
    }

/* ritorna il formato standard
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


    public static boolean getContainIngredienti(List<ListaIngrediente> lista){
        for(ListaIngrediente ingrediente :lista){
            if (ingrediente.getCount()>0)
                return true;

        }
        return false;
    }*/

}
