package pl.kot.app1.activities;

/**
 * Created by Damian on 24/04/2016.
 */
public interface ClientRestowy {

    String getRestResourceURL();

    /**
     *
     * @param inputText - Odpowiedź na request.
     *                  Implementacje tej metody będą przede wszystkim
     *                  (w zwiazku z tym, że klasy implementujące ten
     *                  interfejs będą posiadały obiekt 'Context')
     *                  posiadały odświeżanie widoku aplikacji.
     */
    void proccessView(String inputText);

}
