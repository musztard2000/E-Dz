package pl.kot.app1.model.classes;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Damian on 20/05/2016.
 */
public class OcenaZPrzedmiotu implements Serializable {
    private String wartoscOceny;
    private String zaCoOcena;
    private String nazwaPrzedmiotu;
    private Date dataWydarzenia;
    private boolean czyPrzeczytana;

    public String getWartoscOceny() {
        return wartoscOceny;
    }

    public void setWartoscOceny(String wartoscOceny) {
        this.wartoscOceny = wartoscOceny;
    }

    public String getZaCoOcena() {
        return zaCoOcena;
    }

    public void setZaCoOcena(String zaCoOcena) {
        this.zaCoOcena = zaCoOcena;
    }

    public String getNazwaPrzedmiotu() {
        return nazwaPrzedmiotu;
    }

    public void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
    }

    public Date getDataWydarzenia() {
        return dataWydarzenia;
    }

    public void setDataWydarzenia(Date dataWydarzenia) {
        this.dataWydarzenia = dataWydarzenia;
    }

    public boolean isCzyPrzeczytana() {
        return czyPrzeczytana;
    }

    public void setCzyPrzeczytana(boolean czyPrzeczytana) {
        this.czyPrzeczytana = czyPrzeczytana;
    }
}
