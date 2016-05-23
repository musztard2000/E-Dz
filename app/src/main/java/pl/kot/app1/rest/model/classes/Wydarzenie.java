package pl.kot.app1.rest.model.classes;

/**
 * Created by Damian on 21/05/2016.
 */
public class Wydarzenie {
    String typWydarzenia;
    String nazwaWydarzenia;
    String opisWydarzenia;

    public Wydarzenie(String typWydarzenia, String nazwaWydarzenia, String opisWydarzenia) {
        this.typWydarzenia = typWydarzenia;
        this.nazwaWydarzenia = nazwaWydarzenia;
        this.opisWydarzenia = opisWydarzenia;
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

    public String getOpisWydarzenia() {
        return opisWydarzenia;
    }

    public void setOpisWydarzenia(String opisWydarzenia) {
        this.opisWydarzenia = opisWydarzenia;
    }
}
