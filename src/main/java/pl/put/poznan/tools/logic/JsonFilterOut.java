package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;

public class JsonFilterOut extends JsonInterpreter{
    private JsonInterpreter obj;
    private String[] attributes;

    public JsonFilterOut(JsonInterpreter obj, String[] attributes) {
        this.obj = obj;
        this.attributes = attributes;
    }

    public void filterOut(JsonNode node){
        Iterator<JsonNode> nodes = node.elements();
                while(nodes.hasNext()){
                    for (String attribute : attributes) {
                        if (nodes.next().textValue().equals(attribute)) {
                            nodes.remove();
                        }
                    }
                }
    }

    @Override
    public String show() throws JsonProcessingException {
        filterOut(obj.getJsonNode());
        return obj.show();
    }
}
