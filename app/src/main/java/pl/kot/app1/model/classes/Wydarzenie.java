package pl.kot.app1.model.classes;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Damian on 21/05/2016.
 */
public class Wydarzenie implements Serializable{

    /**
     * Albo OCENA, albo WIADOMOSC.
     */
    String typWydarzenia;

    /**
     * Dla wydarzenia typu OCENA = wartosc oceny
     * Dla wydarzenia typu WIADOMOSC = temat wiadomosci
     */
    String nazwaWydarzenia;

    /**
     * Dla wydarzenia typu OCENA = za co ocena
     * Dla wydarzenia typu WIADOMOSC = treść wiadomosci
     */
    String zawartoscWydarzenia;

    /**
     * Data wydarzenia.
     */
    Date dataWydarzenia;

    /**
     * Jeśli wydarzenie to wiadomość, wypełniam je nadawcą.
     */
    String nadawcaWiadomosci;

    /**
     * Jeśli wydarzenie to Ocena, wtedy to jest przedmiot,
     * z którego jest ocena.
     */
    private String przedmiotOceny;

    /**
     * Gdy powiadomienie wchodzi do osi czasu po raz pierwszy
     * ustawione ma flagę na 'false', co skutkuje zmienionym
     * kolorem komórki, w której jest. Kliknięcie na to wydarzenie
     * odznacza flagę.
     */
    private boolean czyPrzeczytane;

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

    public boolean isCzyPrzeczytane() {
        return czyPrzeczytane;
    }

    public void setCzyPrzeczytane(boolean czyPrzeczytane) {
        this.czyPrzeczytane = czyPrzeczytane;
    }

    public String getNadawcaWiadomosci() {
        return nadawcaWiadomosci;
    }

    public void setNadawcaWiadomosci(String nadawcaWiadomosci) {
        this.nadawcaWiadomosci = nadawcaWiadomosci;
    }

    public String getPrzedmiotOceny() {
        return przedmiotOceny;
    }

    public void setPrzedmiotOceny(String przedmiotOceny) {
        this.przedmiotOceny = przedmiotOceny;
    }
}
