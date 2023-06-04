package com.epam.mjc;

import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        for(String delim : delimiters){
            StringBuilder some = new StringBuilder();
            StringTokenizer str = new StringTokenizer(source,delim);
            while(str.hasMoreTokens()){
                some.append(str.nextToken()).append(" ");

            }
            source = some.toString().replaceAll("\\s+", " ");

        }
        return List.of(source.split(" "));
    }
}
