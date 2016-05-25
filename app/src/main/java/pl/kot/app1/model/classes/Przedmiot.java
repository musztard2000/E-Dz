package pl.kot.app1.model.classes;

/**
 * Created by Damian on 20/04/2016.
 *
 * Jest to model, który jest bezpośrednio podpięty pod widok Listy ocen.
 * Przedmiot jest generowany z klasy <class>OcenaZPrzedmiotu</class> która
 * jest częścią incomingowej wiadomości klasy 'OdpowiedzNaLogowanie'
 * przy udanym logowaniu.
 */
public class Przedmiot {
    private String nazwaPrzedmiotu;
    private String listaOcen;

    public Przedmiot(String nazwaPrzedmiotu, String listaOcen) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
        this.listaOcen = listaOcen;
    }

    public Przedmiot() {

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
