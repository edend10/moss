package com.eden.d.imdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

public class PageSourceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageSourceClient.class);

    public Optional<String> getWebPageSource(String sUrl) {
        try {
            URL url = new URL(sUrl);
            URLConnection urlCon = url.openConnection();
            BufferedReader in =  new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

            String inputLine;
            StringBuilder sb = new StringBuilder();

            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            in.close();

            return Optional.of(sb.toString());

        } catch (Exception e) {
            LOGGER.error("problem getting page source", e);
        }
        return Optional.empty();
    }
}
