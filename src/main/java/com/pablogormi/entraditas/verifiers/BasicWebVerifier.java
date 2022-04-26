package com.pablogormi.entraditas.verifiers;

import com.pablogormi.entraditas.main.Main;
import com.pablogormi.entraditas.util.ValidateType;
import com.pablogormi.entraditas.util.WebsiteUpdater;

public class BasicWebVerifier extends BasicVerifier {
    public final String URL;
    //public String content;
    WebsiteUpdater updater;
    ValidateType validateType = ValidateType.MATCH_ANY;

    /**
     * Starts a new BasicWebVerifier
     * @param url the url to check
     * @param patterns a list of patterns to check for matches
     */
    public BasicWebVerifier(String url, String[] patterns) {
        super(patterns);
        Main.log("Started Web Verifier");
        Main.log("Enabling tracker...");
        this.URL = url;
        updater = new WebsiteUpdater();
        updater.startTracking(URL);
        Main.log("Finished enabling tracker");
    }

    /**
     * Sets the method of validation used to match the patterns.
     * @param type one of ValidateType's options.
     */
    public void setValidateType(ValidateType type) {
        this.validateType=type;
    }

    public boolean isAgotado() {
        Main.debug("Call to isAgotado");
        switch (this.validateType) {
            case MATCH_ALL -> {
                return !this.matchAll(this.getWebsite());
            }
            case MATCH_ANY -> {
                return !this.anyMatch();
            }
        }
        Main.log("CRITICAL ERROR - THE VALIDATETYPE IS NOT VALID. ");
        return true; //unreachable in normal circumstances.
    }

    public String getWebsite() {
        Main.debug("Website content asked");
        return updater.getWebsite_content();
    }

    public String getURL() {
        return this.URL;
    }

    //Match any pattern to the website
    public boolean anyMatch() {
        return this.anyMatch(this.getWebsite());
    }

    public boolean isError() {
        Main.debug("isError invoked");
        return this.getWebsite().equals("ERROR");
    }
}
