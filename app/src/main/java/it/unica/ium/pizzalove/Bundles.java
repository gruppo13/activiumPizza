package it.unica.ium.pizzalove;

/**
 * Created by manuf_000 on 04/05/2016.
 */
public enum Bundles {

    MODIFICA_PIZZA  ("it.unica.ium.pizzalove.MODIFICA_PIZZA"),
    ELENCO_PIZZE  ("it.unica.ium.pizzalove.ELENCO_PIZZE")
    ;

    private final String bundle;

    Bundles(String bundle){
        this.bundle = bundle;
    }

    String getBundle(){ return this.bundle; }
}
