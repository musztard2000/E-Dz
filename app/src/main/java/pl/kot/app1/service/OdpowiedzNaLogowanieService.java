package pl.kot.app1.service;

import java.util.ArrayList;
import java.util.List;

import pl.kot.app1.model.classes.OcenaZPrzedmiotu;
import pl.kot.app1.model.classes.OdpowiedzNaLogowanie;
import pl.kot.app1.model.classes.Wiadomosc;
import pl.kot.app1.model.classes.Wydarzenie;
import pl.kot.app1.model.classes.ZapisaneDaneUzytkownika;

/**
 * Created by Damian on 25/05/2016.
 */
public class OdpowiedzNaLogowanieService {

    private OdpowiedzNaLogowanie odpowiedzNaLogowanie;
    private ZapisaneDaneUzytkownika zapisaneDaneUzytkownika;

    public OdpowiedzNaLogowanieService(OdpowiedzNaLogowanie odpowiedzNaLogowanie, ZapisaneDaneUzytkownika zapisaneDaneUzytkownika) {
        this.odpowiedzNaLogowanie = odpowiedzNaLogowanie;
        this.zapisaneDaneUzytkownika = zapisaneDaneUzytkownika;
    }

    public OdpowiedzNaLogowanie generuj() {
        if (zapisaneDaneUzytkownika == null) {
            return odpowiedzNaLogowanie;
        } else {
            return transformujIScalDaneAplikacjiZOdpowiedziaNaLogowanie();
        }
    }

    private OdpowiedzNaLogowanie transformujIScalDaneAplikacjiZOdpowiedziaNaLogowanie() {
        List<OcenaZPrzedmiotu> oceny = new ArrayList<>();
        List<Wiadomosc> wiadomosci = new ArrayList<>();
        for(Wydarzenie wydarzenie : zapisaneDaneUzytkownika.getWydarzenia()) {
            if (wydarzenie.getTypWydarzenia().startsWith("O")) {
                oceny.add(transformujWydazrenieDoOceny(wydarzenie));
            } else {
                wiadomosci.add(transformujWydarzenieDoWiadomosci(wydarzenie));
            }
        }

        odpowiedzNaLogowanie.setUzytkownik(zapisaneDaneUzytkownika.getUzytkownik());
        odpowiedzNaLogowanie.getWiadomosci().addAll(wiadomosci);
        odpowiedzNaLogowanie.getOceny().addAll(oceny);

        return odpowiedzNaLogowanie;
    }

    private Wiadomosc transformujWydarzenieDoWiadomosci(Wydarzenie wydarzenie) {
        Wiadomosc wiadomosc = new Wiadomosc();
        wiadomosc.setNadawca(wydarzenie.getNadawcaWiadomosci());
        wiadomosc.setDataWydarzenia(wydarzenie.getDataWydarzenia());
        wiadomosc.setTemat(wydarzenie.getNazwaWydarzenia());
        wiadomosc.setTresc(wydarzenie.getZawartoscWydarzenia());
        wiadomosc.setCzyPrzeczytana(wydarzenie.isCzyPrzeczytane());

        return wiadomosc;
    }

    private OcenaZPrzedmiotu transformujWydazrenieDoOceny(Wydarzenie wydarzenie) {
        OcenaZPrzedmiotu ocenaZPrzedmiotu = new OcenaZPrzedmiotu();
        ocenaZPrzedmiotu.setDataWydarzenia(wydarzenie.getDataWydarzenia());
        ocenaZPrzedmiotu.setNazwaPrzedmiotu(wydarzenie.getPrzedmiotOceny());
        ocenaZPrzedmiotu.setWartoscOceny(wydarzenie.getNazwaWydarzenia());
        ocenaZPrzedmiotu.setZaCoOcena(wydarzenie.getZawartoscWydarzenia());
        ocenaZPrzedmiotu.setCzyPrzeczytana(wydarzenie.isCzyPrzeczytane());

        return ocenaZPrzedmiotu;
    }

}
