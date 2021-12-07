package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonBeautiful extends JsonInterpreter{

    private JsonNode obj;
    private ObjectMapper mapper;

    public JsonBeautiful(String obj) {
        super();
    }

    @Override
    public String show() throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }
}
