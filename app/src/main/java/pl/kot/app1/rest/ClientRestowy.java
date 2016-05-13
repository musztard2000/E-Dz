package pl.kot.app1.rest;

/**
 * Interfejs, dostarczający dwóch zachowań na potrzeby
 * klientów restowych, które będą przekazywane jako argument
 * klasy <class>RestProccessor</class>.
 *
 * Te zachowania to dostarczanie URL'a do resoruce'a restowego oraz
 * procesowanie widoku, po zakończonej operacji.
 *
 * Created by Damian on 24/04/2016.
 */
public interface ClientRestowy {
    String URL_UCZNIOWIE = "http://10.0.2.2:8080/webservices/webapi/uczniowie";
    String URL_OPIEKUN = "http://10.0.2.2:8080/webservices/webapi/uczniowie/opiekun";

    String getRestResourceURL();

    /**
     *
     * @param inputText - Odpowiedź na request w formacie JSON.
     *                  Implementacje tej metody będą przede wszystkim
     *                  (w zwiazku z tym, że klasy implementujące ten
     *                  interfejs będą posiadały obiekt 'Context')
     *                  posiadały odświeżanie widoku aplikacji. Zapewne metoda ta
     *                  będzie wywoływania w klasie rozszerzającej AsyncTask: na ten momen
     *                  jest to 'RestProccessor' w metodzie 'onPostExecute', która służy
     *                  z kolei do odświeżania widoku aplikacji po zakończonych operacjach
     *                  w wątku.
     */
    void proccessView(String inputText);

}
