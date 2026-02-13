package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.objects.OTJEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class OTJSender {
    public static void sendOTJs(String cookie, Set<OTJEntry> entries) {
        for (OTJEntry entry : entries) {
            try {
                HttpURLConnection conn = getHttpURLConnection(cookie, entry);

                // Check response code
                int responseCode = conn.getResponseCode();
                if (responseCode != 200) {
                    throw new Exception("Bad Request");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static HttpURLConnection getHttpURLConnection(String cookie, OTJEntry entry) throws URISyntaxException, IOException {
        HttpURLConnection conn = getUrlConnection(cookie);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(entry);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return conn;
    }

    private static HttpURLConnection getUrlConnection(String cookie) throws URISyntaxException, IOException {
        URI uri = new URI("https://education.oneadvanced.com/api/cloud-education/v1/learner/be605ce9-44ff-439e-8d55-47a8637a0313/activity-log");
        URL url = uri.toURL();

        // Open connection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Configure the connection for POST
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Cookie", cookie);
        return conn;
    }
}
