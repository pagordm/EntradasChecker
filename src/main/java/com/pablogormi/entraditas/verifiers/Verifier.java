package com.pablogormi.entraditas.verifiers;

import java.util.regex.Pattern;

public interface Verifier {

    public String getWebsite();

    public Pattern[] genPatterns(String[] strings);
    //Match a specific pattern.
    public boolean matchPattern(Pattern pattern, String match);
    //Match a specific pattern by index.
    public boolean matchPattern(int index, String match);
    public boolean matchPatternToWebsite(Pattern p);
    public boolean matchPatternToWebsite(int index);

    //Search for any match of this Verifier's patterns
    public boolean anyMatch(String match);

    public boolean anyMatch();



}
