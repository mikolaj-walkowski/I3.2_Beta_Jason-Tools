package pl.put.poznan.tools.logic;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;

/**
 * Klasa wyświetla  strukturę w formacie
 * JSON zawierającą tylko określone własności
 */
public class JsonFilter extends JsonInterpreter {

    private JsonInterpreter obj;
    private String[] filters;

    /**
     * Konstruktor
     * @param obj Filtrowany objekt.
     * @param filters Tablica string-ów zawierająca filtry
     */
    public JsonFilter(JsonInterpreter obj, String[] filters) {
        this.obj = obj;
        this.filters = filters;
    }

    /**
     * Funkcja filtruje własności
     * @param node JSON node
     */
    public void filter(JsonNode node){
        Iterator<JsonNode> nodes = node.elements();
        while(nodes.hasNext()){
            for (String attribute : filters) {
                if (nodes.next().textValue().equals(attribute)) {
                    continue;
                }
                else {
                    nodes.remove();
                }
            }
        }
    }

    /**
     * Funkcja do wyświetalnia JSONa
     * @return String zawierający wynikowy JSON
     * @throws JsonProcessingException wyjątek zgłaszany przy błędnej obsłudze JSONa
     */
    @Override
    public String show() throws JsonProcessingException {
        filter(obj.getJsonNode());
        return obj.show();
    }

}
