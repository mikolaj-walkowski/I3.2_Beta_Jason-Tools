package pl.put.poznan.transformer.logic;

public class JsonFilterOut extends JsonInterpreter{
    private JsonInterpreter obj;
    private String[] attributes;

    public JsonFilterOut(JsonInterpreter obj, String[] attributes) {
        this.obj = obj;
        this.attributes = attributes;
    }

    @Override
    public String show() {
        return null;
    }
}
