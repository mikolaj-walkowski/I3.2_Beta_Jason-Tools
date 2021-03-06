package pl.put.poznan.tools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonFilterTest {
    JsonFilter json = null;
    JsonBeautiful mockBeautiful = mock(JsonBeautiful.class);
    String jsonString  = "{\"osoba1\":{\"imie\":\"Michał\",\"wiek\":420},\"osoba2\":{\"imie\":\"Nie Michał\",\"wiek\":420}}";
    JsonFilterTest(){
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
        assertEquals(json.show().replace("\r",""), "{ }");
    }

    @Test
    void testShow4() throws JsonProcessingException {
        String[] arr = {"osoba1","imie"};
        json = new JsonFilter(mockBeautiful,arr);
        assertEquals(json.show().replace("\r",""),
                "{\n" +
                        "  \"osoba1\" : {\n" +
                        "    \"imie\" : \"Michał\"\n" +
                        "  }\n" +
                        "}");
    }

    @Test
    void testShow5() throws JsonProcessingException{
        String[] arr = {"osoba1"};
        json = new JsonFilter(mockBeautiful,arr);
        assertNotEquals(json.show().replace("\r",""),"{ }");
    }
}