package pl.put.poznan.tools.logic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Klasa abstrakcyjna interpreterów JSON
 */
public abstract class JsonInterpreter {

    private JsonNode obj;
    public ObjectMapper mapper;


    public JsonInterpreter(){}

    /**
     * Konstruktor parsujący dane w formacie JSON ze Stringa na JsonNode'a
     * @param json String zawierający JSON
     */
    public JsonInterpreter(String json){
        try {
            this.mapper = new ObjectMapper();
            this.obj = mapper.readTree(json);
        }catch (JsonProcessingException e){
            System.out.println(e);
        }
    }

    /**
     * Funkcja zwracająca JsonNode'a
     * @return JsonNode
     */
    public JsonNode getJsonNode(){
        return obj;
    }

    /**
     * Funkcja ustawiająca JsonNode'a
     * @param obj JsonNode
     */
    public void setJsonNode(JsonNode obj){
        this.obj = obj;
    }

    /**
     * Funkcja zwracająca wyniki wykonań danych klas (nadpisywana przez dane klasy)
     * @return String zawierający wynikowy JSON
     * @throws JsonProcessingException wyjątek zgłaszany przy błędnej obsłudze JSONa
     */
    abstract public String show() throws JsonProcessingException;

}
