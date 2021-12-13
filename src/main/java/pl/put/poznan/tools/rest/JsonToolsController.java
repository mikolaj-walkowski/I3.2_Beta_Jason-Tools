package pl.put.poznan.tools.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.tools.logic.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * Klasa kontroler REST
 */
@RestController
public class JsonToolsController {

    /**
     * Funkcja obsługuje żądanie filterOut
     *
     * @param text    String Zawierający Json-a
     * @param filters Tablica string-ów które zawierają nazwy pól odfiltrowanych domyślnie pusta
     * @param format  Format w którym ma być zapisany Odfiltrowany json domyślnie "beautiful"
     * @return String zawierający wynikowy Json
     */
    @GetMapping("/filterOut/{text}")
    public String filterOut(@PathVariable String text, @RequestParam(value = "filters", defaultValue = "") String[] filters, @RequestParam(value = "format", defaultValue = "beautiful") String format) throws JsonProcessingException {
        // log the parameters
        //logger.debug(text);
        //logger.debug(Arrays.toString(transforms));
        JsonInterpreter out;
        try {
            if (Objects.equals(format, "beautiful")) {
                out = new JsonBeautiful(text);
            } else if (Objects.equals(format, "oneLine")) {
                out = new JsonOneLine(text);
            } else throw new Exception();
        } catch (Exception e) {
            return "Nieobsługiwany format";
        }
        out = new JsonFilterOut(out, filters);
        return out.show();
    }

    /**
     * Funkcja obsługuje żądanie przeformatowania string-a z Json-em na Json- bez nie potrzebnych znaków
     * @param text String Zawierający Json-a
     * @return String zawierający wynikowy Json
     */
    @GetMapping("/oneLine/{text}")
    public String filterOut(@PathVariable String text) throws JsonProcessingException {
        // log the parameters
        //logger.debug(text);
        //logger.debug(Arrays.toString(transforms));
        JsonInterpreter out = new JsonOneLine(text);
        return out.show();
    }

    /**
     * Funkcja do obsługi żądania filter
     *
     * @param text      String stanowiący zawartość wejściowego pliku Json
     * @param filters   Tablica string-ów zawieraja filtry, domyślnie pusta
     * @param format    Format w którym ma być wyświetlony Json domyślnie "beautiful"
     * @return          String z zawartością wynikowego pliku Json
     */
    @GetMapping("/filter/{text}")
    public String filter(@PathVariable String text, @RequestParam(value = "filters", defaultValue = "") String[] filters, @RequestParam(value = "format", defaultValue = "beautiful") String format) {
        // log the parameters
        //logger.debug(text);
        //logger.debug(Arrays.toString(transforms));
        JsonInterpreter out;
        try {
            if (Objects.equals(format, "beautiful")) {
                out = new JsonBeautiful(text);
            } else if (Objects.equals(format, "oneLine")) {
                out = new JsonOneLine(text);
            } else throw new Exception();
        } catch (Exception e) {
            return "Nieobsługiwany format";
        }

        try {
            out = new JsonFilter(out, filters);
            return out.show();
        } catch (Exception e){
            return "Błąd wykonania funkcji show()";
        }
    }

    /**
     * Funkcja do obsługi żądania przeformatowania Jsona na Json w wersji niezminifikowanej
     * @param text      String stanowiący zawartość wejściowego pliku Json
     * @return          String z zawartością wynikowego pliku Json
     */
    @GetMapping("/beautiful/{text}")
    public String beautiful(@PathVariable String text) {
        // log the parameters
        //logger.debug(text);
        //logger.debug(Arrays.toString(transforms));
        JsonInterpreter out = new JsonBeautiful(text);
        try {
            return out.show();
        } catch (Exception e){
            return "Błąd wykonania funkcji show()";
        }
    }

    /**
     * Funkcja do obsługi żądania porównywania 2 tekstów.
     * @param s1 Pierwszy tekst
     * @param s2 Drugi tekst
     * @return Niezgodne line z obu tekstów i ich numer
     */
    @GetMapping("/stringCompare")
    public String stringCompare( @RequestParam(value = "s1", defaultValue = "") String s1, @RequestParam(value = "s2", defaultValue = "") String s2) {
        // log the parameters
        //logger.debug(text);
        //logger.debug(Arrays.toString(transforms));
        StringCompare stringCompare = new StringCompare(s1, s2);
        return stringCompare.show();
    }
}