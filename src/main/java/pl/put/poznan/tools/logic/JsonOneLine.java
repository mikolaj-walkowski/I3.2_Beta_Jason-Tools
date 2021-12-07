package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonOneLine extends JsonInterpreter {

    public JsonOneLine(String text) throws JsonProcessingException {
        super(text);
    }

    @Override
    public String show() throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(getJsonNode());
        return json;
    }
}
