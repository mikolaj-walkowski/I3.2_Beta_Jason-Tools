package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonFilterTest {
    JsonFilter json = null;
    JsonBeautiful obj = new JsonBeautiful("{\"osoba1\":{\"imie\":\"Michał\",\"wiek\":420},\"osoba2\":{\"imie\":\"Nie Michał\",\"wiek\":420}}");

    JsonFilterTest() throws JsonProcessingException {
    }

    @Test
    void testShow1() throws JsonProcessingException {
        String[] arr = {"Michał"};
        json = new JsonFilter(obj,arr);
        assertEquals(json.show().replace("\r",""), "{ }");
    }

    @Test
    void testShow2() throws JsonProcessingException{
        String[] arr = {"osoba1"};
        json = new JsonFilter(obj,arr);
        assertEquals(json.show().replace("\r",""),
                "{\n" +
                "  \"osoba1\" : { }\n" +
                "}");
    }

    @Test
    void testShow3() throws JsonProcessingException{
        String[] arr = {};
        json = new JsonFilter(obj,arr);
        assertEquals(json.show().replace("\r",""),
                "{\n" +
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
    void testShow4() {
        String[] arr = {"osoba1","imie"};
        json = new JsonFilter(obj,arr);
        assertThrows(IllegalStateException.class,()->{json.show();});
    }

    @Test
    void testShow5() throws JsonProcessingException{
        String[] arr = {"osoba1"};
        json = new JsonFilter(obj,arr);
        assertNotEquals(json.show().replace("\r",""),"{ }");
    }
}