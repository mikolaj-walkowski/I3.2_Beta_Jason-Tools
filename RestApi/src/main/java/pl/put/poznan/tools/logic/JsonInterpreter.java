package pl.put.poznan.tools.logic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Klasa abstrakcyjna interpreterów JSON
 */
public abstract class JsonInterpreter {

    /**
     * Odczytany JSON.
     */
    private JsonNode obj;
    /**
     * Mapper do odczytywania JSONów.
     */
    public ObjectMapper mapper;


    /**
     * Pusty konstruktor do nadpisania przez klasy JsonFilter i JsonFilterOut.
     */
    public JsonInterpreter(){}

    /**
     * Konstruktor parsujący dane w formacie JSON ze Stringa na JsonNode'a
     * @param json String zawierający JSON
     */
    public JsonInterpreter(String json) throws JsonProcessingException {
            this.mapper = new ObjectMapper();
            this.obj = mapper.readTree(json);
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
