package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.JsonInterpreter;

import java.util.Arrays;
import java.util.Objects;

/**
 * Klsa kontroler REST
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
    public String filterOut(@PathVariable String text, @RequestParam(value = "filters", defaultValue = "") String[] filters, @RequestParam(value = "format", defaultValue = "beautiful") String format) {
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
    public String filterOut(@PathVariable String text) {
        // log the parameters
        //logger.debug(text);
        //logger.debug(Arrays.toString(transforms));
        JsonInterpreter out = new JsonOneLine(text);
        return out.show();
    }

    @GetMapping("/filter/{text}")
    public String filter(@PathVariable String text, @RequestParam(value = "filters", defaultValue = "") String[] filters, @RequestParam(value = "format", defaultValue = "beautiful") String format) {
        // log the parameters
        //logger.debug(text);
        //logger.debug(Arrays.toString(transforms));
        JsonInterpreter out;
        if (Objects.equals(format, "beautiful")) {
            out = new JsonBeautiful(text);
        } else if (Objects.equals(format, "oneLine")) {
            out = new JsonOneLine(text);
        }

        out = new JsonFilter(out, filters);
        return out.show();
    }

    @GetMapping("/beautiful/{text}")
    public String beautiful(@PathVariable String text) {
        // log the parameters
        //logger.debug(text);
        //logger.debug(Arrays.toString(transforms));
        JsonInterpreter out = new JsonBeautiful(text);
        return out.show();
    }
}


