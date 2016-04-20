package pl.kot.app1.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.kot.app1.model.Wiadomosc;

/**
 * Created by Damian on 20/04/2016.
 */
public class WiadomosciService {

    public List<Wiadomosc> generujWiadmosci() {
        List<Wiadomosc> wiadomosciList = new ArrayList<>();
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 1", "Jakiś temat szkolny", "Anna Kurlanda"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 2", "Jakiś temat ważny", "Miron Białoszewski"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 3", "Jakiś temat", "Mariusz Gałka"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 4", "Jakiś temat 2", "Wisława Szymborska"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 5", "Jakiś temat klasówki", "Krysyna Pawłowicz"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 1", "Jakiś temat szkolny", "Anna Kurlanda"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 2", "Jakiś temat ważny", "Miron Białoszewski"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 3", "Jakiś temat", "Mariusz Gałka"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 4", "Jakiś temat 2", "Wisława Szymborska"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 5", "Jakiś temat klasówki", "Krysyna Pawłowicz"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 1", "Jakiś temat szkolny", "Anna Kurlanda"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 2", "Jakiś temat ważny", "Miron Białoszewski"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 3", "Jakiś temat", "Mariusz Gałka"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 4", "Jakiś temat 2", "Wisława Szymborska"));
        wiadomosciList.add(new Wiadomosc(new Date(), "Przykładowa treść 5", "Jakiś temat klasówki", "Krysyna Pawłowicz"));

        return wiadomosciList;

    }
}
