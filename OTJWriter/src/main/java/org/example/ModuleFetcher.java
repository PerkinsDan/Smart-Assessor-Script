package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.objects.Module;
import org.openqa.selenium.Cookie;

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

public class ModuleFetcher {
    public static Set<Module> fetchModules(String cookie) {

        try {
            HttpURLConnection conn = getHttpURLConnection(cookie);

            // Check response code
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("Bad Request");
            }

            // Read the response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.toString(), new TypeReference<>() {});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static HttpURLConnection getHttpURLConnection(String cookie) throws URISyntaxException, IOException {
        HttpURLConnection conn = getUrlConnection(cookie);

        // Form data to send
        String urlEncodedForm = "course=ef974f73-5d9d-447e-8652-379ba9535229";

        // Write data to the request body
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = urlEncodedForm.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return conn;
    }

    private static HttpURLConnection getUrlConnection(String cookie) throws URISyntaxException, IOException {
        URI uri = new URI("https://www.smartassessor.co.uk/ETimeSheet/ModuleList");
        URL url = uri.toURL();

        // Open connection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Configure the connection for POST
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Cookie", cookie);
        return conn;
    }
}
