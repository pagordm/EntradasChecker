package com.pablogormi.entraditas.main;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.Desktop;

import com.pablogormi.entraditas.util.ValidateType;
import com.pablogormi.entraditas.verifiers.BasicWebVerifier;

public class Main {
    //Update time in TIME_UNIT units
    public static final long DELAY = 5;
    public static final TimeUnit TIME_UNIT = TimeUnit.MINUTES;

    public static final boolean DEBUG = false;


    public static final ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {

        log("Starting program...");
        BasicWebVerifier verifier = new BasicWebVerifier("https://www.entradascajarural.com/", new String[]{"barras", "plaza de toros", "bono"});
        verifier.setValidateType(ValidateType.MATCH_ANY);
        log("Enabling main timer...");
        service.scheduleAtFixedRate(() -> {
            if (verifier.isAgotado()) {
                if (verifier.isError())
                    log("Error al acceder a la URL. Reintentando en " + DELAY + " " + TIME_UNIT.toString().toLowerCase());
                else
                    log("Agotado, reintentando en " + DELAY + " " + TIME_UNIT);
            } else {
                log("-+-+-+-+-+-+-+AVISO+-+-+-+-+-+-+-+-");
                log("ESTO NO ES UN SIMULACRO, NO AGOTADO");
                log("         Abriendo la página.       ");
                log("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(verifier.getURL()));
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
