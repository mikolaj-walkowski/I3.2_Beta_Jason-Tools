package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Klasa wyświetlająca reprezentację zminifikowaną struktury w formacie JSON.
 */
public class JsonOneLine extends JsonInterpreter {

    /**
     * Konstruktor.
     * @param text String zawierający JSONa.
     */
    public JsonOneLine(String text) throws JsonProcessingException {
        super(text);
    }

    /**
     * Funkcja do wyświetalnia JSONa
     * @return String zawierający wynikowy JSON
     * @throws JsonProcessingException wyjątek zgłaszany przy błędnej obsłudze JSONa
     */
    @Override
    public String show() throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(getJsonNode());
        return json;
    }
}
