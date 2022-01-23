package pl.put.poznan.tools.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCompareTest {

    StringCompareTest(){
    }

    @Test
    void testShow1(){
        String s1 = "testing Equal Strings";
        StringCompare comparison = new StringCompare(s1,s1);
        assertEquals(comparison.show(),"");
    }

    @Test
    void testShow2(){
        String s1 = "testing two, one line, non equal strings";
        String s2 = "testing one line, yet not equal strings";
        StringCompare comparison = new StringCompare(s1,s2);

        assertEquals(comparison.show().replace("\t"," "),s1 + " != " + s2 + " line: 1\n");
    }

    @Test
    void testShow3(){
        String s1 = "testing\ntwo\nequal\nstrings\nwith\nmany\nlines";
        StringCompare comparison = new StringCompare(s1,s1);

        assertEquals(comparison.show(),"");
    }

    @Test
    void testShow4(){

        assertEquals(new StringCompare("test\n2\ndifferent\nstrings\npossessing\nmultiple\nlines\nof\nvarying\nnumber",
                "testing\ntwo\ndifferent\nstrings\nwith\nmany\nlines").show().replace("\t"," "),
                "test != testing line: 1\n2 != two line: 2\npossessing != with line: 5\nmultiple != many line: 6\nof != ###KONIEC### line: 8\nvarying != ###KONIEC### line: 9\nnumber != ###KONIEC### line: 10\n");
    }

    @Test
    void testShow5(){
        assertNotEquals(new StringCompare("sample", "String").show().replace("\t"," "), "");
    }
}
