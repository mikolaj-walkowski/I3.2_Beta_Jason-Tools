package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonOneLineTest {
    private JsonOneLine json = null;
    private JsonOneLine json2 = new JsonOneLine("{\n" +
            "  \"id\" : \"957c43f2-fa2e-42f9-bf75-6e3d5bb6960a\",\n" +
            "  \"name\" : \"The Best Product\",\n" +
            "  \"brand\" : {\n" +
            "    \"id\" : \"9bcd817d-0141-42e6-8f04-e5aaab0980b6\",\n" +
            "    \"name\" : \"ACME Products\",\n" +
            "    \"owner\" : {\n" +
            "      \"id\" : \"b21a80b1-0c09-4be3-9ebd-ea3653511c13\",\n" +
            "      \"name\" : \"Ultimate Corp, Inc.\"\n" +
            "    }\n" +
            "  }\n" +
            "}");

    JsonOneLineTest() throws JsonProcessingException {
    }

    @BeforeEach
    void setup() throws JsonProcessingException {
        json = new JsonOneLine("{\n" +
                "  \"osoba1\" : {\n" +
                "    \"imie\" : \"Michał\",\n" +
                "    \"wiek\" : 420\n" +
                "  },\n" +
                "  \"osoba2\" : {\n" +
                "    \"imie\" : \"Nie Michał\",\n" +
                "    \"wiek\" : 420\n" +
                "  }\n" +
                "}");
    }

    @Test
    void testShow1() throws JsonProcessingException {
        assertEquals(json.show().replace("\r",""),"{\"osoba1\":{\"imie\":\"Michał\",\"wiek\":420},\"osoba2\":{\"imie\":\"Nie Michał\",\"wiek\":420}}");

    }

    @Test
    void testShow2() throws JsonProcessingException{
        assertEquals(json2.show().replace("\r",""),"{\"id\":\"957c43f2-fa2e-42f9-bf75-6e3d5bb6960a\",\"name\":\"The Best Product\",\"brand\":{\"id\":\"9bcd817d-0141-42e6-8f04-e5aaab0980b6\",\"name\":\"ACME Products\",\"owner\":{\"id\":\"b21a80b1-0c09-4be3-9ebd-ea3653511c13\",\"name\":\"Ultimate Corp, Inc.\"}}}");

    }

    @Test
    void testShow3() throws JsonProcessingException {
        assertNotEquals(json.show().replace("\r",""),"{\"id\":\"957c43f2-fa2e-42f9-bf75-6e3d5bb6960a\",\"name\":\"The Best Product\",\"brand\":{\"id\":\"9bcd817d-0141-42e6-8f04-e5aaab0980b6\",\"name\":\"ACME Products\",\"owner\":{\"id\":\"b21a80b1-0c09-4be3-9ebd-ea3653511c13\",\"name\":\"Ultimate Corp, Inc.\"}}}");
    }

    @Test
    void testShow4() throws JsonProcessingException{
        assertNotEquals(json.show().replace("\r",""),"{\n" +
                "  \"id\" : \"957c43f2-fa2e-42f9-bf75-6e3d5bb6960a\",\n" +
                "  \"name\" : \"The Best Product\",\n" +
                "  \"brand\" : {\n" +
                "    \"id\" : \"9bcd817d-0141-42e6-8f04-e5aaab0980b6\",\n" +
                "    \"name\" : \"ACME Products\",\n" +
                "    \"owner\" : {\n" +
                "      \"id\" : \"b21a80b1-0c09-4be3-9ebd-ea3653511c13\",\n" +
                "      \"name\" : \"Ultimate Corp, Inc.\"\n" +
                "    }\n" +
                "  }\n" +
                "}");
    }

    @Test
    void testShow5() throws JsonProcessingException {
        assertThrows(JsonParseException.class,()->{new JsonBeautiful("niejson");});
    }
}