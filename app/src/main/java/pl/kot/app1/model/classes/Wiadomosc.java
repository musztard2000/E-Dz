package pl.kot.app1.model.classes;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Damian on 20/05/2016.
 */
public class Wiadomosc implements Serializable {
    private String nadawca;
    private String temat;
    private String tresc;
    private Date dataWydarzenia;

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

    public Date getDataWydarzenia() {
        return dataWydarzenia;
    }

    public void setDataWydarzenia(Date dataWydarzenia) {
        this.dataWydarzenia = dataWydarzenia;
    }
}
