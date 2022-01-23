package pl.put.poznan.tools.logic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Klasa wyświetla  strukturę w formacie
 * JSON zawierającą tylko określone własności
 */
public class JsonFilter extends JsonInterpreter {

    /**
     * Filtrowany objekt.
     */
    private JsonInterpreter obj;
    /**
     * Tablica string-ów zawierająca filtry.
     */
    private String[] filters;

    /**
     * Konstruktor
     * @param obj Filtrowany objekt.
     * @param filters Tablica string-ów zawierająca filtry.
     */
    public JsonFilter(JsonInterpreter obj, String[] filters) {
        this.obj = obj;
        this.filters = filters;
    }

    /**
     * Funkcja filtruje własności na pierwszym poziomie
     * @param node JSON node
     */
    public void filterInitial(JsonNode node){
        Iterator<String> itr = node.fieldNames();
        while(itr.hasNext()){
            String help = itr.next();
            if(!Arrays.asList(filters).contains(help)){
                itr.remove();
            }
        }
    }

    /**
     * Funkcja filtruje własności
     * @param node JSON node
     */
    public void filter(JsonNode node){
        for(JsonNode root : node){
            Iterator<String> itr2 = root.fieldNames();
            while(itr2.hasNext()){
                String help = itr2.next();
                if(!Arrays.asList(filters).contains(help)){
                    itr2.remove();
                }
                filter(root);
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
        filterInitial(obj.getJsonNode());
        filter(obj.getJsonNode());
        return obj.show();
    }

}
