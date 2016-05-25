package pl.kot.app1.model.classes;

import java.io.Serializable;

/**
 * Created by Damian on 20/05/2016.
 */
public class Uzytkownik implements Serializable{
    private String imie;
    private String nazwisko;
    private String login;
    private String password;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
