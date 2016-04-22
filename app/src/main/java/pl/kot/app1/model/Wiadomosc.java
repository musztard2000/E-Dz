package pl.kot.app1.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Damian on 20/04/2016.
 */
public class Wiadomosc implements Serializable{
    private String nadawca;
    private String temat;
    private String tresc;
    private Date dataWyslania;

    public Wiadomosc(Date dataWyslania, String tresc, String temat, String nadawca) {
        this.dataWyslania = dataWyslania;
        this.tresc = tresc;
        this.temat = temat;
        this.nadawca = nadawca;
    }

    public String getNadawca() {
        return nadawca;
    }

    public void setNadawca(String nadawca) {
        this.nadawca = nadawca;
    }

    public String getTemat() {
        return temat;
    }

    public void setTemat(String temat) {
        this.temat = temat;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Date getDataWyslania() {
        return dataWyslania;
    }

    public void setDataWyslania(Date dataWyslania) {
        this.dataWyslania = dataWyslania;
    }
}
