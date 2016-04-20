package pl.kot.app1.service;

import java.util.ArrayList;
import java.util.List;

import pl.kot.app1.model.Przedmiot;

/**
 * Created by Damian on 20/04/2016.
 */
public class PrzedmiotyService {

    private final String OCENY= "3, 4, 4+, 5-, 3, 3";

    public List<Przedmiot> generujPrzedmioty() {
        List<Przedmiot> przedmioty = new ArrayList<>();
        przedmioty.add(new Przedmiot("Język polski", OCENY));
        przedmioty.add(new Przedmiot("Matematyka", OCENY));
        przedmioty.add(new Przedmiot("Biologia", OCENY));
        przedmioty.add(new Przedmiot("Chemia", OCENY));
        przedmioty.add(new Przedmiot("Plastyka", OCENY));
        przedmioty.add(new Przedmiot("Muzyka", OCENY));
        przedmioty.add(new Przedmiot("Wychowanie fizyczne", OCENY));
        przedmioty.add(new Przedmiot("Religia / Etyka", OCENY));
        przedmioty.add(new Przedmiot("Geografia", OCENY));
        przedmioty.add(new Przedmiot("Historia", OCENY));
        przedmioty.add(new Przedmiot("Informatyka", OCENY));
        przedmioty.add(new Przedmiot("Wiedza o społeczeństwie", OCENY));
        przedmioty.add(new Przedmiot("Przysposobienie obronne", OCENY));
        przedmioty.add(new Przedmiot("Podstawy przedsiębiorczości", OCENY));

        return przedmioty;
    }
}
