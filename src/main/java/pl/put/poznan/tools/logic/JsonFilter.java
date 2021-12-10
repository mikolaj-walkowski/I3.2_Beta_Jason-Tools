package pl.put.poznan.tools.logic;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

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
     * @param currentNode JSON node
     */
    public void filter(JsonNode currentNode){
        if (currentNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) currentNode;
            Iterator<JsonNode> node = arrayNode.elements();
            while (node.hasNext()) {
                filter(node.next());
            }
        }
        else if (currentNode.isObject()) {
            Iterator<String> itr = currentNode.fieldNames();
            while(itr.hasNext()){
                String help = itr.next();
                for(String attribute : filters){
                    if(!help.equals(attribute)){
                        itr.remove();
                    }
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
