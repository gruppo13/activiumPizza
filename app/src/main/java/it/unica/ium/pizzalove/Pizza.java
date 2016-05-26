package it.unica.ium.pizzalove;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pizza implements Parcelable{

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

    public void lessCount(){
        --COUNT;
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(obj.getClass() != this.getClass()) return false;

        Pizza p = (Pizza)obj;
        if(p.getNomePizza() == null)
            return false;
        else if(!nomePizza.equals(p.getNomePizza()))
            return false;
        return true;
    }

    /**
     * --------------------------------------------------PARCELABLE---------------------------------
     */

    public Pizza(Parcel in){
        this.COUNT = in.readInt();
        this.nomePizza = in.readString();
        in.readTypedList(listaIngredienti, Ingredienti.CREATOR);
    }


    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(COUNT);
        out.writeString(nomePizza);
        out.writeTypedList(listaIngredienti);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public static final Parcelable.Creator<Pizza> CREATOR = new
            Parcelable.Creator<Pizza>() {
                @Override
                public Pizza createFromParcel(Parcel in) {
                    return new Pizza(in);
                }
                @Override
                public Pizza[] newArray(int size) {
                    return new Pizza[size];
                }
            };
}
