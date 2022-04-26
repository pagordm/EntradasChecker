package com.pablogormi.entraditas.main;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.Desktop;
import com.pablogormi.entraditas.verifiers.JWVerifier;
import com.pablogormi.entraditas.verifiers.Verifier;

public class Main {
    //Update time in TIME_UNIT units
    public static final long DELAY = 5;
    public static final TimeUnit TIME_UNIT = TimeUnit.MINUTES;

    public static final boolean DEBUG = false;


    public static final ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) throws IOException {

        log("Starting program...");
        JWVerifier verifier = new JWVerifier();
        log("Enabling main timer...");
        service.scheduleAtFixedRate(() -> {
            if (verifier.isAgotado()) {
                log("Agotado, reintentado en " + DELAY + " " + TIME_UNIT.toString());
            } else {
                log("-+-+-+-+-+-+-+AVISO+-+-+-+-+-+-+-+-");
                log("ESTO NO ES UN SIMULACRO, NO AGOTADO");
                log("         Abriendo la página.       ");
                log("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(JWVerifier.URL));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1, DELAY, TIME_UNIT);
        log("Ready to roll!");
        //while(true);

    }
    public static String getDateAndTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("'['yyyy-MM-dd'T'HH:mm:ss']'");
        return format.format(date);
    }

    public static void log(String s) {
        System.out.println(getDateAndTime()+ " " + s);
    }

    public static void debug(String s) {
        if (DEBUG) log("DEBUG " + s);
    }
}
