package it.unica.ium.pizzalove;


import java.text.DecimalFormat;

/**
 * Created by manuf_000 on 22/02/2016.
 */
public enum Ingredienti{

    Sugo (0.25f , 1),
    Mozzarella (0.25f, 1),
    Basilico (0.0f, 1),
    Funghi (0.50f, 1),
    Acciughe (0.50f, 1),
    Capperi (0.25f, 1),
    Bacon (1.00f, 1),
    Broccoli (0.50f, 1),
    Cipolle (0.25f, 1),
    Grana (0.50f, 1),
    Gamberetti (1.00f, 1),
    Melanzane (0.50f, 1),
    Olive (0.25f, 1),
    Patatine (0.50f, 1),
    Peperoncino (0.25f, 1),
    Pepeperoni (0.50f, 1),
    Pomodoro (0.50f, 1),
    Salame (1.00f, 1),
    Uova (0.50f, 1),
    Wurstel (0.50f, 1),
    Zucchine (0.50f, 1),
    Cotto (0.50f, 1)
    ;

    private final Float price;
    private int number;

    Ingredienti(Float price, int number){
        this.price = price;
        this.number = number;
    }

    void setNumber(int n){
        this.number = n;
    }

    Float getPrice(){
        return this.price;
    }

    int getNumber() {
        return this.number;
    }


    /* ritorna il formato standard */
    public static String formatoPrezzo(float prezzo) {
        DecimalFormat form = new DecimalFormat("0.00");
        return form.format(prezzo) + " €";
    }
}
