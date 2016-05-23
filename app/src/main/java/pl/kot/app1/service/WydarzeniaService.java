package pl.kot.app1.service;

import java.util.ArrayList;
import java.util.List;

import pl.kot.app1.rest.model.classes.OdpowiedzNaLogowanie;
import pl.kot.app1.rest.model.classes.Wydarzenie;

/**
 * Created by Damian on 21/05/2016.
 */
public class WydarzeniaService {
    public List<Wydarzenie> generujWydarzenia(OdpowiedzNaLogowanie oceny) {

        List<Wydarzenie> wydarzenies = new ArrayList<>();
        Wydarzenie wydarzenie = new Wydarzenie("Ocena", "5", "za sprawdzian");
        Wydarzenie wydarzenie1 = new Wydarzenie("Wiadomosc", "Zaproszenia na...", "dyrektor");
        Wydarzenie wydarzenie2 = new Wydarzenie("Wiadomosc", "Zaproszenia na...", "dyrektor");
        wydarzenies.add(wydarzenie);
        wydarzenies.add(wydarzenie1);
        wydarzenies.add(wydarzenie2);
        wydarzenies.add(wydarzenie);
        wydarzenies.add(wydarzenie1);
        wydarzenies.add(wydarzenie2);
        wydarzenies.add(wydarzenie);
        wydarzenies.add(wydarzenie1);
        wydarzenies.add(wydarzenie2);
        wydarzenies.add(wydarzenie1);
        wydarzenies.add(wydarzenie2);
        wydarzenies.add(wydarzenie1);
        wydarzenies.add(wydarzenie2);

        return wydarzenies;
    }
}
