package pl.kot.app1.rest.model.classes;

import java.util.Date;

/**
 * Created by Damian on 21/05/2016.
 */
public class Wydarzenie {
    String typWydarzenia;
    String nazwaWydarzenia;
    String zawartoscWydarzenia;
    Date dataWydarzenia;

    public Wydarzenie(String typWydarzenia, String nazwaWydarzenia, String zawartoscWydarzenia) {
        this.typWydarzenia = typWydarzenia;
        this.nazwaWydarzenia = nazwaWydarzenia;
        this.zawartoscWydarzenia = zawartoscWydarzenia;
    }

    public Wydarzenie(String typWydarzenia, String nazwaWydarzenia, String zawartoscWydarzenia, Date dataWydarzenia) {
        this.typWydarzenia = typWydarzenia;
        this.nazwaWydarzenia = nazwaWydarzenia;
        this.zawartoscWydarzenia = zawartoscWydarzenia;
        this.dataWydarzenia = dataWydarzenia;
    }

    public Wydarzenie() {}

    public String getTypWydarzenia() {
        return typWydarzenia;
    }

    public void setTypWydarzenia(String typWydarzenia) {
        this.typWydarzenia = typWydarzenia;
    }

    public String getNazwaWydarzenia() {
        return nazwaWydarzenia;
    }

    public void setNazwaWydarzenia(String nazwaWydarzenia) {
        this.nazwaWydarzenia = nazwaWydarzenia;
    }

    public String getZawartoscWydarzenia() {
        return zawartoscWydarzenia;
    }

    public void setZawartoscWydarzenia(String zawartoscWydarzenia) {
        this.zawartoscWydarzenia = zawartoscWydarzenia;
    }

    public Date getDataWydarzenia() {
        return dataWydarzenia;
    }

    public void setDataWydarzenia(Date dataWydarzenia) {
        this.dataWydarzenia = dataWydarzenia;
    }
}
