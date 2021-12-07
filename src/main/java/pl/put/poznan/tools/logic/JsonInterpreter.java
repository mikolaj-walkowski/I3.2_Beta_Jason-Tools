package pl.put.poznan.tools.logic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public abstract class JsonInterpreter {

    private JsonNode obj;
    private ObjectMapper mapper;

    public JsonInterpreter(){}
    public JsonInterpreter(String json){
        try {
            this.mapper = new ObjectMapper();
            obj = mapper.readTree(json);
        }catch (JsonProcessingException e){
            System.out.println(e);
        }
    }

//    public JsonInterpreter(){}
    public JsonNode getJsonNode(){
        return obj;
    }
    public void setJsonNode(JsonNode obj){
        this.obj = obj;
    }

    abstract public String show() throws JsonProcessingException;

}
