package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.JsonInterpreter;

import java.util.Arrays;
import java.util.Objects;


@RestController
public class JsonToolsController {

    //private static final Logger logger = LoggerFactory.getLogger(JsonToolsController.class);
    @GetMapping("/filterOut/{text}")
    public String filterOut(@PathVariable String text,@RequestParam(value="filters", defaultValue="") String[] filters, @RequestParam(value="format", defaultValue="beautiful") String format) {
        // log the parameters
        //logger.debug(text);
        //logger.debug(Arrays.toString(transforms));
        JsonInterpreter out;
        if(Objects.equals(format, "beautiful")){
            out = new JsonBeautiful(text);
        }else if(Objects.equals(format, "oneLine")){
            out = new JsonOneLine(text);
        }
        out = new JsonFilterOut(out,filters);
        return out.show();
    }
    @GetMapping("/oneLine/{text}")
    public String filterOut(@PathVariable String text) {
        // log the parameters
        //logger.debug(text);
        //logger.debug(Arrays.toString(transforms));
        JsonInterpreter out = new JsonOneLine(text);
        return out.show();
    }

}


