package pl.kot.app1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.kot.app1.rest.model.classes.OcenaZPrzedmiotu;
import pl.kot.app1.rest.model.classes.OdpowiedzNaLogowanie;
import pl.kot.app1.rest.model.classes.Wiadomosc;
import pl.kot.app1.rest.model.classes.Wydarzenie;

/**
 * Created by Damian on 21/05/2016.
 */
public class WydarzeniaService {
    public List<Wydarzenie> generujWydarzenia(OdpowiedzNaLogowanie oceny) {


        List<Wydarzenie> wydarzenies = new ArrayList<>();
        wydarzenies.addAll(generujWydarzeniaZOcen(oceny.getOceny()));
        wydarzenies.addAll(generujWydarzeniaZWiadomosci(oceny.getWiadomosci()));

        //w odwrotnej kolejności !!!
        Collections.sort(wydarzenies, new Comparator<Wydarzenie>() {
            @Override
            public int compare(Wydarzenie wydarzenie, Wydarzenie t1) {
                return t1.getDataWydarzenia().compareTo(wydarzenie.getDataWydarzenia());
            }
        });
        return wydarzenies;
    }

    private List<Wydarzenie> generujWydarzeniaZOcen(List<OcenaZPrzedmiotu> ocenyZPrzedmiotu) {
        List<Wydarzenie> wydarzeniaZOcen = new ArrayList<>();
        for (OcenaZPrzedmiotu ocenaZPrzedmiotu : ocenyZPrzedmiotu) {
            Wydarzenie wydarzenie = new Wydarzenie();
            wydarzenie.setDataWydarzenia(ocenaZPrzedmiotu.getDataWydarzenia());
            wydarzenie.setNazwaWydarzenia(ocenaZPrzedmiotu.getWartoscOceny());
            wydarzenie.setZawartoscWydarzenia(ocenaZPrzedmiotu.getZaCoOcena());
            wydarzenie.setTypWydarzenia("Ocena");
            wydarzeniaZOcen.add(wydarzenie);
        }

        return wydarzeniaZOcen;
    }

    private List<Wydarzenie> generujWydarzeniaZWiadomosci(List<Wiadomosc> wiadomosci) {
        List<Wydarzenie> wydarzeniaZOcen = new ArrayList<>();
        for (Wiadomosc wiadomosc : wiadomosci) {
            Wydarzenie wydarzenie = new Wydarzenie();
            wydarzenie.setDataWydarzenia(wiadomosc.getDataWydarzenia());
            wydarzenie.setNazwaWydarzenia(wiadomosc.getTemat());
            wydarzenie.setZawartoscWydarzenia(wiadomosc.getTresc());
            wydarzenie.setTypWydarzenia("Wiadomość");
            wydarzeniaZOcen.add(wydarzenie);
        }

        return wydarzeniaZOcen;
    }

}
