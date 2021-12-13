package pl.put.poznan.tools.logic;

/**
 * Klasa zajmuje się znajdowamniem różnic w dwóch textach i przetwarzaniem na "human readable" output
 */
public class StringCompare {

    String[] s1Split;
    String[] s2Split;
    String result = "";

    /**
     * Przyjmuje dwa stringi i przetwarza je na tablice na podstawie newline
     * po czym tworzy dla nich wynik.
     * @param s1 pierwszy tekst
     * @param s2 drugi tekst
     */
    public StringCompare(String s1, String s2) {
        s1Split = s1.split("\\n");
        s2Split = s2.split("\\n");

        for(int i =0; i< s1Split.length || i< s2Split.length ; i++){
            if(i>=s1Split.length) {
                result += "###KONIEC###\t!=\t" + s2Split[i] + "\tline: " + (i+1) + "\n";
                continue;
            }
            if(i>=s2Split.length) {
                result += s1Split[i] + "\t!=\t###KONIEC###\tline: " + (i+1) + "\n";
                continue;
            }
            if(!s1Split[i].equals(s2Split[i])){
                result += s1Split[i] + "\t!=\t" + s2Split[i] + "\tline: " + (i+1) + "\n";
            }
        }
    }

    /**
     * Zwraca wynik
     * @return string z różniącymi się linami i ich numerami.
     */
    public String show(){
        return result;
    }
}
