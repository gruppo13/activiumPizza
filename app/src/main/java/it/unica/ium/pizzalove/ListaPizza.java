package it.unica.ium.pizzalove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by perlo on 15/02/16.
 */


public class ListaPizza {

    public enum Classica {
        Margherita,QuattroStagioni, Napoli, Creata
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
                break;
            case QuattroStagioni:
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.SUGO));
                this.ingrediente.add(new ListaIngrediente(ListaIngrediente.Ingrediente.MOZZARELLA));
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
                this.prezzo=4.0f;
                break;
            case QuattroStagioni:
               this.prezzo=5.0f;
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
            case "Quattro Stagioni":
                return Classica.QuattroStagioni;
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
            case QuattroStagioni:
                return "Quattro Stagioni";
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
