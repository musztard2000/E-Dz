package pl.kot.app1.model.classes;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Damian on 25/05/2016.
 *
 * Model danych przechowywanych jako plik tekstowy w local storage aplikacji.
 */
public class ZapisaneDaneUzytkownika implements Serializable{
    private Uzytkownik uzytkownik;
    private List<Wydarzenie> wydarzenia;
    private long dataOstatniegoLogowania;

    public ZapisaneDaneUzytkownika() {}

    public ZapisaneDaneUzytkownika(Uzytkownik uzytkownik, List<Wydarzenie> wydarzenia, long dataOstatniegoLogowania) {
        this.uzytkownik = uzytkownik;
        this.wydarzenia = wydarzenia;
        this.dataOstatniegoLogowania = dataOstatniegoLogowania;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public List<Wydarzenie> getWydarzenia() {
        return wydarzenia;
    }

    public void setWydarzenia(List<Wydarzenie> wydarzenia) {
        this.wydarzenia = wydarzenia;
    }

    public long getDataOstatniegoLogowania() {
        return dataOstatniegoLogowania;
    }

    public void setDataOstatniegoLogowania(long dataOstatniegoLogowania) {
        this.dataOstatniegoLogowania = dataOstatniegoLogowania;
    }
}
