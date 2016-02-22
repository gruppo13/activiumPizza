package it.unica.ium.pizzalove;

/**
 * Created by manuf_000 on 22/02/2016.
 */
public enum Ingredienti {

    Sugo (0.25f),
    Mozzarella (0.25f),
    Basilico (0.0f),
    Funghi (0.50f),
    Acciughe (0.50f),
    Capperi (0.25f),
    Bacon (1.00f),
    Broccoli (0.50f),
    Cipolle (0.25f),
    Grana (0.50f),
    Gamberetti (1.00f),
    Melanzane (0.50f),
    Olive (0.25f),
    Patatine (0.50f),
    Peperoncino (0.25f),
    Pepeperoni (0.50f),
    Pomodoro (0.50f),
    Salame (1.00f),
    Uova (0.50f),
    Wurstel (0.50f),
    Zucchine (0.50f),
    Cotto (0.50f)
    ;

    private final Float price;

    Ingredienti(Float price){
        this.price = price;
    }

    Float getPrice(){
        return this.price;
    }
}
