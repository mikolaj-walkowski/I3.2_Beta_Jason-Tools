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
    public JsonInterpreter(String json){
        try {
            this.mapper = new ObjectMapper();
            this.obj = mapper.readTree(json);
        }catch (JsonProcessingException e){
            System.out.println(e);
        }
    }

    public JsonNode getJsonNode(){
        return obj;
    }
    public void setJsonNode(JsonNode obj){
        this.obj = obj;
    }

    abstract public String show() throws JsonProcessingException;

}
