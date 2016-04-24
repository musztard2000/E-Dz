package pl.kot.app1.activities;

import android.content.Context;

/**
 * Created by Damian on 24/04/2016.
 */
public class ClientRestowyUstawPrzeczytana implements ClientRestowy {

    /**
     * Referencja do 'Activity', które akurat wywoła DziennikRestClient, które wykona
     * pracę RESTOWĄ, a które tutaj będzie miało manipulowane jakoś atrybuty
     * swoich komponentów.
     */
    public Context context;

    public ClientRestowyUstawPrzeczytana(Context context) {
        this.context = context;
    }

    @Override
    public String getRestResourceURL() {
        return "http://10.0.2.2:8080/webservices/webapi/uczniowie/2";
    }

    @Override
    public void proccessView(String inputText) {
        ((PodgladWiadomosciActivity) context).getTextViewTresc().setText(inputText);
    }
}
