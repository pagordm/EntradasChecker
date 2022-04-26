package com.pablogormi.entraditas.verifiers;

import com.pablogormi.entraditas.main.Main;
import com.pablogormi.entraditas.util.WebsiteUpdater;

public class BasicWebVerifier extends BasicVerifier {
    public final String URL;
    //public String content;
    WebsiteUpdater updater;

    public BasicWebVerifier(String url, String[] patterns) {
        super(patterns);
        Main.log("Started Web Verifier");
        Main.log("Enabling tracker...");
        this.URL = url;
        updater = new WebsiteUpdater();
        updater.startTracking(URL);
        Main.log("Finished enabling tracker");
    }

    public boolean isAgotado() {
        Main.debug("Call to isAgotado");
        return !this.matchAll(this.getWebsite());
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


}
