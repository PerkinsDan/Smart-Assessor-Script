package org.example;

import org.example.objects.OTJEntry;
import org.example.utils.CookieGrabber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        CookieGrabber cookieGrabber = new CookieGrabber();

        try {
            System.out.println("Press Enter to continue...");
            System.in.read();
        } catch (Exception e) {
            System.out.println("Error while waiting for input");
        }

        Set<OTJEntry> entries = readFile();
        OTJSender.sendOTJs(cookieGrabber.getCookie(), entries);

        cookieGrabber.shutdown();
    }

    private static Set<OTJEntry> readFile() {
        Set<OTJEntry> set = new HashSet<>();
        String filePath = "/Users/danperkins/Desktop/utils/xls/calendar_events.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String activityDate = outputFormat.format(inputFormat.parse(values[3]));
                String activityImpact = values[6];
                String activityTime = "T" + values[4] + ":00";
                int hours = Integer.parseInt(values[5].split(":")[0]);
                int minutes =  Integer.parseInt(values[5].split(":")[1]);

                OTJEntry entry = new OTJEntry(
                        activityDate, activityImpact, activityTime, hours, minutes
                );

                set.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return set;
    }
}