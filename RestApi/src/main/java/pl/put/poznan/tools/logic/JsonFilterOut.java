package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;

/**
 * Klasa wyświetla  strukturę w formacie
 * JSON nie zawierającą określonych własności
 */
public class JsonFilterOut extends JsonInterpreter{
    private JsonInterpreter obj;
    private String[] attributes;

    /**
     * Konstruktor
     * @param obj Filtrowany objekt.
     * @param attributes Tablica string-ów zawierająca filtry
     */
    public JsonFilterOut(JsonInterpreter obj, String[] attributes) {
        this.obj = obj;
        this.attributes = attributes;
    }
    
    /**
     * Funkcja filtrująca własności na pierwszym poziomie
     * @param node JSON node
     */
    public void filterOutInitial(JsonNode node){
        Iterator<String> itr = node.fieldNames();
        while(itr.hasNext()){
            String help = itr.next();
            for(String attribute : attributes){
                if(help.equals(attribute)){
                    itr.remove();
                }
            }
        }
    }

    /**
     * Właściwa funkcja filtrująca własności
     * @param node JSON node
     */
    public void filterOut(JsonNode node){
        for(JsonNode root : node){
            Iterator<String> itr = root.fieldNames();
            while(itr.hasNext()){
                String help = itr.next();
                for(String attribute : attributes){
                    if(help.equals(attribute)){
                        itr.remove();
                    }
                }
                filterOut(root);
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
        filterOutInitial(obj.getJsonNode());
        filterOut(obj.getJsonNode());
        return obj.show();
    }
}
