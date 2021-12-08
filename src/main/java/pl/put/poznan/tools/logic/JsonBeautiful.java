package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Klasa wyświetlająca pełną strukturę w formacie JSON.
 */
public class JsonBeautiful extends JsonInterpreter {

    private JsonNode obj;
    private ObjectMapper mapper;

    /**
     * Konstruktor.
     * @param json String zawierający JSONa.
     */
    public JsonBeautiful(String json) {
        super(json);
    }

    /**
     * Funkcja do wyświetalnia JSONa
     * @return String zawierający wynikowy JSON
     * @throws JsonProcessingException wyjątek
     */
    @Override
    public String show() throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }
}
