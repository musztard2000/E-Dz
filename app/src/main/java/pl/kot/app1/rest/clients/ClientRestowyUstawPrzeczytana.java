package pl.kot.app1.rest.clients;

import android.content.Context;

import pl.kot.app1.activities.PodgladWiadomosciActivity;
import pl.kot.app1.rest.ClientRestowy;

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
        return URL_UCZNIOWIE;
    }



    @Override
    public void proccessView(String inputText) {
        ((PodgladWiadomosciActivity) context).getTextViewTresc().setText(inputText);
    }
}
