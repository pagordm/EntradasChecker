package com.pablogormi.entraditas.verifiers;

import java.util.regex.Pattern;

public interface Verifier {

    Pattern[] genPatterns(String[] strings);
    //Match a specific pattern.
    boolean matchPattern(Pattern pattern, String match);
    //Match a specific pattern by index.
    boolean matchPattern(int index, String match);

    //Search for any match of this Verifier's patterns
    boolean anyMatch(String match);

    boolean matchAll(String match);



}
