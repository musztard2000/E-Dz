package pl.kot.app1.model.classes;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Damian on 20/05/2016.
 */
public class OdpowiedzNaLogowanie implements Serializable{
    private Uzytkownik uzytkownik;
    private List<Wiadomosc> wiadomosci;
    private List<OcenaZPrzedmiotu> oceny;

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public List<Wiadomosc> getWiadomosci() {
        return wiadomosci;
    }

    public void setWiadomosci(List<Wiadomosc> wiadomosci) {
        this.wiadomosci = wiadomosci;
    }

    public List<OcenaZPrzedmiotu> getOceny() {
        return oceny;
    }

    public void setOceny(List<OcenaZPrzedmiotu> oceny) {
        this.oceny = oceny;
    }
}
