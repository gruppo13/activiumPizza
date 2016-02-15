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
        SALMONE, ACCIUGHE, ORIGANO,CAPPERI
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

    public String getStringNome() {
        switch (this.nome) {
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

    public float getPrezzoIngrediente(){
        return this.prezzoIngrediente;
    }


    public Ingrediente getNome(){
        return this.nome;
    }
}
