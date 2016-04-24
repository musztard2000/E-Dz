package pl.kot.app1.activities;

/**
 * Created by Damian on 24/04/2016.
 */
public interface ClientRestowy {
    String URL_UCZNIOWIE = "http://10.0.2.2:8080/webservices/webapi/uczniowie";

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
