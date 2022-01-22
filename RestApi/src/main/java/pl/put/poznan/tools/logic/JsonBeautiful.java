package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Klasa wyświetlająca pełną strukturę w formacie JSON.
 */
public class JsonBeautiful extends JsonInterpreter {

    /**
     * Konstruktor.
     * @param json String zawierający JSONa.
     */
    public JsonBeautiful(String json) throws JsonProcessingException {
        super(json);
    }

    /**
     * Funkcja do wyświetalnia JSONa
     * @return String zawierający wynikowy JSON
     * @throws JsonProcessingException wyjątek zgłaszany przy błędnej obsłudze JSONa
     */
    @Override
    public String show() throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getJsonNode());
    }
}
