package it.unica.ium.pizzalove;

import java.util.Arrays;
import java.util.List;

/**
 * Created by manuf_000 on 22/02/16.
 */
public class Pizza {

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


    /*
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

    public static int containPizza(List<ListaPizza> lista, String nome){
        int i=0;
        for(ListaPizza pizze: lista){
            if (pizze.getStringNome().equals(nome))
                return i;
            i++;
        }
        return -1;
    }


    public static boolean getContainIngredienti(List<ListaIngrediente> lista){
        for(ListaIngrediente ingrediente :lista){
            if (ingrediente.getCount()>0)
                return true;

        }
        return false;
    }*/

}
