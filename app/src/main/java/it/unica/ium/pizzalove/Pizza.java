package it.unica.ium.pizzalove;

import java.util.Arrays;
import java.util.List;

/**
 * Created by manuf_000 on 22/02/16.
 */
public class Pizza{

    private static int COUNT = 0;
    private String nomePizza;
    private List<Ingredienti> listaIngredienti;


    public Pizza(String nomePizza){
        this.nomePizza = nomePizza;
        switch(nomePizza){
            case "margherita":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella);
                break;
            case "napoli":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Capperi,
                        Ingredienti.Acciughe, Ingredienti.Basilico);
                break;
            case "wurstelCipolle":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Wurstel,
                        Ingredienti.Cipolle);
                break;
            case "funghi":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Funghi);
                break;
            case "capricciosa":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Funghi,
                        Ingredienti.Cotto, Ingredienti.Uova, Ingredienti.Wurstel);
                break;
            case "prosciuttoFunghi":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Funghi,
                        Ingredienti.Cotto
                );
                break;
            case "allAmerican":
                this.listaIngredienti =  Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella,Ingredienti.Patatine,
                        Ingredienti.Wurstel);
                break;
            case "carbonara":
                this.listaIngredienti = Arrays.asList(Ingredienti.Mozzarella, Ingredienti.Uova, Ingredienti.Bacon);
                break;
            case "parmigiana":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Melanzane,
                        Ingredienti.Grana);
                break;
            case "cotto":
                this.listaIngredienti = Arrays.asList(Ingredienti.Sugo, Ingredienti.Mozzarella, Ingredienti.Cotto);
                break;
            default:
                this.nomePizza = "creata";
                break;
        }
    }

    public String getNomePizza(){
        return this.nomePizza;
    }


    public List<Ingredienti> getIngredienti(){
        return this.listaIngredienti;
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

    public boolean addIngrediente(String i){
        try{
            listaIngredienti.add(Ingredienti.valueOf(i));
        }
        catch (Exception r){
            r.printStackTrace();
            return false;
        }
        return true;
    }

    public void addCount(){
        this.COUNT++;
    }

    public int getCount(){
        return this.COUNT;
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
        List <ListaIngrediente> listingredienti = new ArrayList<>();

        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.BASILICO, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.ACCIUGHE, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.FUNGHI, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.BECON, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.BROCCOLI, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.CIPOLLE, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.FORMAGGIO, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.GAMBERETTI, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MELANZANE, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.OLIVE, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.PATATINE, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.PEPERONCINI, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.PEPERONI, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.POMODORI, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SALAME, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.UOVA, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.WURSTEL, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.ZUCCHINE, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.COTTO, 0));
        listingredienti.add(new ListaIngrediente(ListaIngrediente.Ingrediente.CAPPERI, 0));
        return listingredienti;
    }

    /* return l-indice nella quale si trova ingrediente in cui ha valore magg di zero
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
