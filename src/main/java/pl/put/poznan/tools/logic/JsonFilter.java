package pl.put.poznan.tools.logic;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonFilter extends JsonInterpreter {

    private JsonInterpreter obj;
    private String[] attributes;

    public JsonFilter(JsonInterpreter obj, String[] attributes) {
        this.obj = obj;
        this.attributes = attributes;
    }

    public JsonNode filter(JsonNode jsonNode){
        return jsonNode;
    }

    @Override
    public String show() {
        return null;
    }

}
