package it.unica.ium.pizzalove;


import java.text.DecimalFormat;

/**
 * Created by manuf_000 on 22/02/2016.
 */
public enum Ingredienti{

    Sugo (0.25f , 1),
    Mozzarella (0.25f, 2),
    Basilico (0.0f, 3),
    Funghi (0.50f, 4),
    Acciughe (0.50f, 5),
    Capperi (0.25f, 6),
    Bacon (1.00f, 7),
    Broccoli (0.50f, 8),
    Cipolle (0.25f, 9),
    Grana (0.50f, 10),
    Gamberetti (1.00f, 11),
    Melanzane (0.50f, 12),
    Olive (0.25f, 13),
    Patatine (0.50f, 14),
    Peperoncino (0.25f, 15),
    Pepeperoni (0.50f, 16),
    Pomodoro (0.50f, 17),
    Salame (1.00f, 18),
    Uova (0.50f, 19),
    Wurstel (0.50f, 20),
    Zucchine (0.50f, 21),
    Cotto (0.50f, 22)
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
}
