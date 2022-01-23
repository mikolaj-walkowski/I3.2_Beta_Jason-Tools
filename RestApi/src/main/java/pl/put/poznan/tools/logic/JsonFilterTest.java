package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonFilterTest {
    JsonFilter json = null;
    JsonBeautiful mockBeautiful = mock(JsonBeautiful.class);
    String jsonString  = "{\"osoba1\":{\"imie\":\"Michał\",\"wiek\":420},\"osoba2\":{\"imie\":\"Nie Michał\",\"wiek\":420}}";
    JsonFilterTest() throws JsonProcessingException {
    }

    @BeforeEach
    void setup(){
        try {
            when(mockBeautiful.getJsonNode()).thenReturn(new ObjectMapper().readTree(jsonString));
            when(mockBeautiful.show()).thenReturn("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    void testShow1() throws JsonProcessingException {
        String[] arr = {"Michał"};

        json = new JsonFilter(mockBeautiful,arr);
        assertEquals(json.show().replace("\r",""), "{ }");
    }

    @Test
    void testShow2() throws JsonProcessingException{
        String[] arr = {"osoba1"};
        json = new JsonFilter(mockBeautiful,arr);
        assertEquals(json.show().replace("\r",""),
                "{\n" +
                "  \"osoba1\" : { }\n" +
                "}");
    }

    @Test
    void testShow3() throws JsonProcessingException{
        String[] arr = {};
        json = new JsonFilter(mockBeautiful,arr);
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
        json = new JsonFilter(mockBeautiful,arr);
        assertThrows(IllegalStateException.class,()->{json.show();});
    }

    @Test
    void testShow5() throws JsonProcessingException{
        String[] arr = {"osoba1"};
        json = new JsonFilter(mockBeautiful,arr);
        assertNotEquals(json.show().replace("\r",""),"{ }");
    }
}