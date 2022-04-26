package com.pablogormi.entraditas.util;

import com.pablogormi.entraditas.main.Main;
import okhttp3.*;

import java.io.IOException;

public class ServerGetter {
    private final OkHttpClient client = new OkHttpClient();

    public String getURL(String url) {
        Request request = new Request.Builder().url(url).build();
        Main.debug("Requesting url " + url);
        try(Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code: " + response);
            Main.debug("Request completed successfully.");
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}
