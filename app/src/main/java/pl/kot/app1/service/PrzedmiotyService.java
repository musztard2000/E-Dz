package pl.kot.app1.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.kot.app1.model.classes.Przedmiot;
import pl.kot.app1.model.classes.OcenaZPrzedmiotu;

/**
 * Created by Damian on 20/04/2016.
 *
 * Jest to serwis, który wykonuje transofmacje obiektów typu 'OcenaZPrzedmiotu'
 * do obiektów klasy 'Przedmiot'. Klasa 'Przedmiot' powstała na początku tworzenia
 * aplikacji i została ładnie podpięta z widokami i adapterami. Dlatego, nie
 * chciałem jej usuwać, tylko zmieniłem implementację metody generującej
 * serwis, by dalej korzystać z mądrze przemyślanych obiektów, dobrze wpiętych w widoki.
 */
public class PrzedmiotyService {

    public List<Przedmiot> generujPrzedmioty(List<OcenaZPrzedmiotu> ocenyZPrzedmiotow) {
        return transformOcenyZPrzedmiotowToPrzedmioty(ocenyZPrzedmiotow);
    }

    private List<Przedmiot> transformOcenyZPrzedmiotowToPrzedmioty(List<OcenaZPrzedmiotu> ocenyZPrzedmiotow) {
        List<Przedmiot> przedmiotyWynikowe = new ArrayList<>();

        final Set<String> nazwyPrzedmiotow = new HashSet<>();
        for (OcenaZPrzedmiotu o : ocenyZPrzedmiotow) {
            nazwyPrzedmiotow.add(o.getNazwaPrzedmiotu());
        }

        for (String nazwaPrzedmiotu : nazwyPrzedmiotow) {
            Przedmiot przedmiot = new Przedmiot();
            przedmiot.setNazwaPrzedmiotu(nazwaPrzedmiotu);

            String oceny = "";
            for (OcenaZPrzedmiotu ocenaZPrzedmiotu : ocenyZPrzedmiotow) {
                if (nazwaPrzedmiotu.equals(ocenaZPrzedmiotu.getNazwaPrzedmiotu())) {
                    final String ocenaZPrzecinkiem = ocenaZPrzedmiotu.getWartoscOceny() + ", ";
                    oceny += ocenaZPrzecinkiem;
                }
            }

            if (!oceny.isEmpty()) {
                oceny = oceny.substring(0, oceny.length() - 2);
            }

            przedmiot.setListaOcen(oceny);

            przedmiotyWynikowe.add(przedmiot);
        }

        return przedmiotyWynikowe;
    }
}
