package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonFilterOutTest {

    JsonFilterOut json = null;
    JsonBeautiful mockBeautiful = mock(JsonBeautiful.class);
    String jsonString  = "{\"osoba1\":{\"imie\":\"Michał\",\"wiek\":420},\"osoba2\":{\"imie\":\"Nie Michał\",\"wiek\":420}}";
    JsonFilterOutTest(){
    }

    @BeforeEach
    void setup(){
        try {
            ObjectMapper map = new ObjectMapper();
            JsonNode node = map.readTree(jsonString);
            when(mockBeautiful.getJsonNode()).thenReturn(node);
            when(mockBeautiful.show()).thenAnswer(new Answer<String>() {
                @Override
                public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                    return map.writerWithDefaultPrettyPrinter().writeValueAsString(node);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    void testShow1() throws JsonProcessingException {
        String[] arr = {"Michał"};
        json = new JsonFilterOut(mockBeautiful,arr);
        assertEquals(json.show().replace("\r",""), "{\n" +
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
    void testShow2() throws JsonProcessingException {
        String[] arr = {"osoba1"};
        json = new JsonFilterOut(mockBeautiful,arr);
        assertEquals(json.show().replace("\r",""),
                "{\n" +
                        "  \"osoba2\" : {\n" +
                        "    \"imie\" : \"Nie Michał\",\n" +
                        "    \"wiek\" : 420\n" +
                        "  }\n" +
                        "}");
    }

    @Test
    void testShow3() throws JsonProcessingException{
        String[] arr = {};
        json = new JsonFilterOut(mockBeautiful,arr);
        assertEquals(json.show().replace("\r",""), "{\n" +
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
    void testShow4() throws JsonProcessingException {
        String[] arr = {"osoba1","imie"};
        json = new JsonFilterOut(mockBeautiful,arr);
        assertEquals(json.show().replace("\r",""),
                "{\n" +
                        "  \"osoba2\" : {\n" +
                        "    \"wiek\" : 420\n" +
                        "  }\n" +
                        "}");
    }

    @Test
    void testShow5() throws JsonProcessingException{
        String[] arr = {"osoba1"};
        json = new JsonFilterOut(mockBeautiful,arr);
        assertNotEquals(json.show().replace("\r",""),"{ }");
    }
}