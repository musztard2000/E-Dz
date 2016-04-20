package pl.kot.app1.model;

/**
 * Created by Damian on 20/04/2016.
 */
public class Przedmiot {
    private String nazwaPrzedmiotu;
    private String listaOcen;

    public Przedmiot(String nazwaPrzedmiotu, String listaOcen) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
        this.listaOcen = listaOcen;
    }
    public String getNazwaPrzedmiotu() {
        return nazwaPrzedmiotu;
    }

    public void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
    }

    public String getListaOcen() {
        return listaOcen;
    }

    public void setListaOcen(String listaOcen) {
        this.listaOcen = listaOcen;
    }
}
