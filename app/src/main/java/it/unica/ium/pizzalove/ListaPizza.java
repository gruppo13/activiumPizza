package it.unica.ium.pizzalove;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perlo on 15/02/16.
 */


public class ListaPizza {

    public enum Classica {
        Margherita, Napoli, Funghi, Capricciosa, Cotto, Vegetariana, WurstelCipolle,
        ProsciuttoFunghi, AllAmerican, Carbonara, Parmigiana, Creata
    }

    private Classica nome;
    private float prezzo;
    private int count;
    private List<ListaIngrediente> ingrediente;

    private final float prezzoBase = 2.0f;

    public ListaPizza(Classica nome, float prezzo, List<ListaIngrediente> ingredienti, int count){
        this.nome = nome;
        this.ingrediente = ingredienti;
        if (prezzo>0.0)
            this.prezzo = prezzo;
        else
            this.prezzo =prezzoBase;
        if (count>0)
            this.count =count;
        else
            this.count=0;

    }

    public ListaPizza(Classica nome, List<ListaIngrediente> ingredienti, int count){
        this.nome = nome;
        this.ingrediente = ingredienti;
        this.prezzo =prezzoBase;
        if (count>0)
            this.count =count;
        else
            this.count=0;
    }

/* costruttore richiamato quando si crea la pizza */
    public ListaPizza(List<ListaIngrediente> ingredienti){
        this.nome = Classica.Creata;
        this.ingrediente = new ArrayList<>();
        for(ListaIngrediente ingrediente : ingredienti){
            if (ingrediente.getCount()>0) {
                this.ingrediente.add(ingrediente);
            }

        }
        this.prezzo =prezzoBase;
        this.setPrezzoCreata();
        this.count=1;
    }


    /* costruttore per creare la pizza classica */
    public ListaPizza(Classica nome){
                this.nome = nome;
                this.ingrediente = new ArrayList<>() ;
                this.setIngredienti();
                this.setPrezzo();
                this.count=0;

    }


    public ListaPizza(Classica nome, int count){
                this.nome = nome;
                this.ingrediente = new ArrayList<>() ;
                this.setIngredienti();
                this.setPrezzo();
                this.count=count;

    }




    public ListaPizza(Classica nome, float prezzo){
        this.nome = nome;
        this.ingrediente = new ArrayList<>() ;
        this.setIngredienti();
        if (prezzo>0.0)
            this.prezzo = prezzo;
        else
            this.prezzo =prezzoBase;
        this.count=1;
    }


    private void setIngredienti () {
        switch (this.nome) {
            case Margherita:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                break;
            case Napoli:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.ACCIUGHE));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.CAPPERI));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.BASILICO));
                break;
            case Funghi:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.FUNGHI));
                break;
            case Capricciosa:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.FUNGHI));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.COTTO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.UOVA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.WURSTEL));
                break;
            case WurstelCipolle:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.WURSTEL));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.CIPOLLE));
                break;
            case Vegetariana:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MELANZANE));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.ZUCCHINE));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.PEPERONI));
                break;
            case ProsciuttoFunghi:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.COTTO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.FUNGHI));
                break;
            case AllAmerican:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.WURSTEL));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.PATATINE));
                break;
            case Carbonara:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.UOVA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.BECON));
                break;
            case Parmigiana:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MELANZANE));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.FORMAGGIO));
                break;
            case Cotto:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.COTTO));
                break;
            default:
                break;
        }
    }

    private void setPrezzo () {
        switch (this.nome) {
            case Margherita:
                this.prezzo=3.0f;
                break;
            case Napoli:
                this.prezzo=3.5f;
                break;
            case WurstelCipolle:
                this.prezzo=3.8f;
                break;
            case Funghi:
                this.prezzo=4.5f;
                break;
            case Capricciosa:
                this.prezzo=4.8f;
                break;
            case Vegetariana:
                this.prezzo=4.5f;
                break;
            case ProsciuttoFunghi:
                this.prezzo=4.3f;
                break;
            case AllAmerican:
                this.prezzo=4.0f;
                break;
            case Carbonara:
                this.prezzo=4.5f;
                break;
            case Parmigiana:
                this.prezzo=4.9f;
                break;
            case Cotto:
                this.prezzo=4.0f;
                break;
            default:
                this.prezzo=prezzoBase;
                break;
        }
    }



    public void addIngrediente(ListaIngrediente.Ingrediente ingrediente){
        this.ingrediente.add(new ListaIngrediente(ingrediente));
    }

    public void addCount(){
        this.count++;

    }

    public int getCount(){
        return this.count;
    }


    public int getSizeIngredienti(){
        int size=0;
        for(ListaIngrediente ingrediente : this.ingrediente) {
            if (ingrediente.getCount()>0){
                size++;
            }
        }
        return size;
    }



    public static Classica getClassicaS(String classica) {
        switch (classica) {
            case "Margherita":
                return Classica.Margherita;
            case "Napoli":
                return Classica.Napoli;
            case "Wurstel e Cipolle":
                return Classica.WurstelCipolle;
            case "Funghi":
                return Classica.Funghi;
            case "Prosciutto e Funghi":
                return Classica.ProsciuttoFunghi;
            case "Capricciosa":
                return Classica.Capricciosa;
            case "Vegetariana":
                return Classica.Vegetariana;
            case "All American":
                return Classica.AllAmerican;
            case "Carbonara":
                return Classica.Carbonara;
            case "Parmigiana":
                return Classica.Parmigiana;
            case "Prosciutto Cotto":
                return Classica.Cotto;
            default:
                return Classica.Creata;
        }
    }

    public String getStringNome() {
        switch (this.nome) {
            case Margherita:
                return "Margherita";
            case Napoli:
                return "Napoli";
            case WurstelCipolle:
                return "Wurstel e Cipolle";
            case Funghi:
                return "Funghi";
            case Capricciosa:
                return "Capricciosa";
            case Vegetariana:
                return "Vegetariana";
            case ProsciuttoFunghi:
                return "Prosciutto e Funghi";
            case AllAmerican:
                return "All American";
            case Carbonara:
                return "Carbonara";
            case Parmigiana:
                return "Parmigiana";
            case Cotto:
                return "Prosciutto Cotto";
            default:
                return "not valid";
        }
    }

    public Classica getNome() {
        return this.nome;
    }

    public List<ListaIngrediente> getIngredienti (){
        return this.ingrediente;
    }

    private void setPrezzoCreata(){
        for(ListaIngrediente ingrediente : this.ingrediente) {
            if (ingrediente.getCount()>0){
                this.prezzo+= ingrediente.getPrezzoIngrediente() * ingrediente.getCount();
            }
        }
    }

    public float getPrezzo(){
        return this.prezzo;
    }

}
