package com.pablogormi.entraditas.verifiers;

import com.pablogormi.entraditas.main.Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A basic matcher that uses text strings as patterns
 */
public abstract class BasicVerifier implements Verifier{

    private final Pattern[] patterns;

    protected BasicVerifier(String[] patterns) {
        this.patterns = this.genPatterns(patterns);
    }


    @Override
    public Pattern[] genPatterns(String[] strings) {
        Pattern[] p = new Pattern[strings.length];
        for (int i = 0; i < strings.length; i++) {
            p[i] = Pattern.compile(strings[i]);
        }
        Main.debug("Finished compiling patterns!");
        return p;
    }

    @Override
    public boolean matchPattern(Pattern pattern, String match) {
        Matcher m = pattern.matcher(match);
        return m.find();
    }

    @Override
    public boolean matchPattern(int index, String match) {
        return this.matchPattern(this.patterns[index], match);
    }

    @Override
    public boolean anyMatch(String match) {
        for (Pattern p :
                patterns) {
            if (this.matchPattern(p, match)) return true;
        }
        return false;
    }

    @Override
    public boolean matchAll(String match) {
        for(Pattern p : this.patterns) {
            if (!this.matchPattern(p, match)) return false;
        }
        return true;
    }

}
