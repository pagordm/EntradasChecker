package com.pablogormi.entraditas.util;

import com.pablogormi.entraditas.main.Main;

public class WebsiteUpdater {

    private StringWrapper website_content = new StringWrapper();
    private volatile boolean stop = false;
    private static final long WAIT_TIME = 300;

    public WebsiteUpdater() {

    }

    public void startTracking(String url) {
        this.stop = false;
        ServerGetter s = new ServerGetter();
        Main.service.scheduleAtFixedRate(() -> {
            Main.debug("Updating website content.");
            String newcontent = s.getURL(url);
            Main.debug("Finished updating website content");
            if (newcontent.compareTo(this.website_content.getString()) != 0) {
                this.website_content.setString(newcontent);
            }
        }, 0, Main.DELAY, Main.TIME_UNIT);
    }

    public void stopTracking() {
        this.stop = true;
    }

    public String getWebsite_content() {
        return this.website_content.getString();
    }
}
