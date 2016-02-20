package it.unica.ium.pizzalove;

/**
 * Created by perlo on 15/02/16.
 */
public class ListaIngrediente {

    public int getCount() {
        return this.count;
    }

    public enum Ingrediente {
        SUGO, MOZZARELLA, BASILICO, FUNGHI,
         ACCIUGHE, CAPPERI, BECON,BROCCOLI,CIPOLLE,
        FORMAGGIO,GAMBERETTI,MELANZANE,OLIVE,PATATINE,PEPERONCINI,PEPERONI,
        POMODORI, SALAME,UOVA,WURSTEL,ZUCCHINE, COTTO
    }


    private Ingrediente nome;
    private int count;
    private final int massCount = 10;
    private float prezzoIngrediente;




    public ListaIngrediente (Ingrediente nuovoingrediente, int n){
        this.nome = nuovoingrediente;
        if ((n>0) && n<massCount)
            this.count =n;
        else this.count=0;
        this.prezzoIngrediente = this.setPrezzoIngrediente();

    }


    public ListaIngrediente (Ingrediente nuovoingrediente){
        this.nome = nuovoingrediente;
        this.count =1;
        this.setPrezzoIngrediente();
    }



    public void addIngrediente(){
        if (this.count<this.massCount)
            this.count++;
    }

    public void setIngrediente(int aggiunta){
        if (aggiunta<this.massCount)
            this.count = aggiunta;
        else
            this.count = 0;
    }

    private float setPrezzoIngrediente() {
        switch (this.nome) {
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
            case CAPPERI:
                return 0.50f;
            case BECON:
                return 1.50f;
            case BROCCOLI:
                return 1.00f;
            case CIPOLLE:
                return 1.00f;
            case FORMAGGIO:
                return 1.00f;
            case GAMBERETTI:
                return 1.50f;
            case MELANZANE:
                return 0.50f;
            case OLIVE:
                return 0.5f;
            case PATATINE:
                return 0.50f;
            case PEPERONCINI:
                return 1.50f;
            case PEPERONI:
                return 0.50f;
            case POMODORI:
                return 1.50f;
            case SALAME:
                return 1.00f;
            case UOVA:
                return 1.00f;
            case WURSTEL:
                return 1.00f;
            case ZUCCHINE:
                return 1.00f;
            case COTTO:
                return 1.00f;
            default:
                return 0.0f;
        }
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
            case "acciughe":
                return Ingrediente.ACCIUGHE;
            case "capperi":
                return Ingrediente.CAPPERI;
            case "becon":
                return Ingrediente.BECON;
            case "broccoli":
                return Ingrediente.BROCCOLI;
            case "cipolle":
                return Ingrediente.CIPOLLE;
            case "formaggio":
                return Ingrediente.FORMAGGIO;
            case "gamberetti":
                return Ingrediente.GAMBERETTI;
            case "melanzane":
                return Ingrediente.MELANZANE;
            case "olive":
                return Ingrediente.OLIVE;
            case "patatine":
                return Ingrediente.PATATINE;
            case "peperoncini":
                return Ingrediente.PEPERONCINI;
            case "peperoni":
                return Ingrediente.PEPERONI;
            case "pomodori":
                return Ingrediente.POMODORI;
            case "salame":
                return Ingrediente.SALAME;
            case "uova":
                return Ingrediente.UOVA;
            case "wurstel":
                return Ingrediente.WURSTEL;
            case "zucchine":
                return Ingrediente.ZUCCHINE;
            case "cotto":
                return Ingrediente.COTTO;
            default:
                return null;
        }
    }

    public String getStringNome() {
        switch (this.nome) {
            case SUGO:
                return "sugo";
            case MOZZARELLA:
                return "mozzarella";
            case FUNGHI:
                return "funghi";
            case ACCIUGHE:
                return "acciughe";
            case BASILICO:
                return "basilico";
            case CAPPERI:
                return "capperi";
            case BECON:
                return "becon";
            case BROCCOLI:
                return "broccoli";
            case CIPOLLE:
                return "cipolle";
            case FORMAGGIO:
                return "formaggio";
            case GAMBERETTI:
                return "gamberetti";
            case MELANZANE:
                return "melanzane";
            case OLIVE:
                return "olive";
            case PATATINE:
                return "patatine";
            case PEPERONCINI:
                return "peperoncini";
            case PEPERONI:
                return "peperoni";
            case POMODORI:
                return "pomodori";
            case SALAME:
                return "salame";
            case UOVA:
                return "uova";
            case WURSTEL:
                return "wurstel";
            case ZUCCHINE:
                return "zucchine";
            case COTTO:
                return "cotto";

            default:
                return "not valid";
        }
    }

    public float getPrezzoIngrediente(){
        return this.prezzoIngrediente;
    }


    public Ingrediente getNome(){
        return this.nome;
    }
}
