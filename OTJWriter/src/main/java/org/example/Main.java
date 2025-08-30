package org.example;

import org.example.objects.Module;
import org.example.objects.OTJEntry;
import org.example.utils.CookieGrabber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static Set<Module> modules;
    public static void main(String[] args) {
        CookieGrabber cookieGrabber = new CookieGrabber();

        try {
            System.out.println("Press Enter to continue...");
            System.in.read();
        } catch (Exception e) {
            System.out.println("Error while waiting for input");
        }

        modules = ModuleFetcher.fetchModules(cookieGrabber.getCookie());
        if (modules == null) {
            System.out.println("No modules");
            System.exit(-1);
        }

        Set<OTJEntry> entries = readFile();
        OTJSender.sendOTJs(cookieGrabber.getCookie(), entries);

        System.out.println(modules);

        cookieGrabber.shutdown();
    }

    private static Set<OTJEntry> readFile() {
        Set<OTJEntry> set = new HashSet<>();
        String filePath = "../xls/calendar_events.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Module thisModule = modules.stream().filter(module -> module.getText().contains(values[1])).findFirst().orElse(null);

                OTJEntry entry = new OTJEntry(
                        values[3],
                        thisModule.getId(),
                        values[5],
                        values[4],
                        values[2],
                        values[6]
                );

                set.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return set;
    }
}