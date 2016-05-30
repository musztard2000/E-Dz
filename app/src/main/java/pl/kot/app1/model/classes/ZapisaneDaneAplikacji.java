package pl.kot.app1.model.classes;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Damian on 26/05/2016.
 */
public class ZapisaneDaneAplikacji implements Serializable{
    List<ZapisaneDaneUzytkownika> zapisaneDaneUzytkownikaList;

    public List<ZapisaneDaneUzytkownika> getZapisaneDaneUzytkownikaList() {
        return zapisaneDaneUzytkownikaList;
    }

    public void setZapisaneDaneUzytkownikaList(List<ZapisaneDaneUzytkownika> zapisaneDaneUzytkownikaList) {
        this.zapisaneDaneUzytkownikaList = zapisaneDaneUzytkownikaList;
    }
}
