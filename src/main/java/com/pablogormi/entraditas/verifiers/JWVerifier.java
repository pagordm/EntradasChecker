package com.pablogormi.entraditas.verifiers;

import com.pablogormi.entraditas.main.Main;
import com.pablogormi.entraditas.util.ServerGetter;
import com.pablogormi.entraditas.util.WebsiteUpdater;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JWVerifier implements Verifier {
    ServerGetter serverGetter = new ServerGetter();
    public static final String URL = "https://tienda.japanweekend.com/producto/japan-weekend-bilbao-entrada-anticrisis-26-febrero-2022/";
    //public String content;
    Pattern[] patterns;
    WebsiteUpdater updater;

    public JWVerifier() {
        Main.log("Started Japan Weekend Verifier");
        Main.log("Enabling tracker...");
        this.patterns = this.genPatterns(new String[]{"Agotado"});
        updater = new WebsiteUpdater();
        updater.startTracking(URL);
        Main.log("Finished enabling tracker");
    }

    public boolean isAgotado() {
        Main.debug("Call to isAgotado");
        return this.matchPatternToWebsite(0);
    }

    @Override
    public String getWebsite() {
        Main.debug("Website content asked");
        return updater.getWebsite_content();
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
    public boolean matchPatternToWebsite(Pattern p) {
        return this.matchPattern(p, this.getWebsite());
    }

    @Override
    public boolean matchPatternToWebsite(int index) {
        return this.matchPatternToWebsite(this.patterns[index]);
    }

    @Override
    public boolean anyMatch(String match) {
        for (Pattern p :
                patterns) {
            if (this.matchPattern(p, match)) return true;
        }
        return false;
    }

    //Match any pattern to the website
    public boolean anyMatch() {
        return this.anyMatch(this.getWebsite());
    }
}
