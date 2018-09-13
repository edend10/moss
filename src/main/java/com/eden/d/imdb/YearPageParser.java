package com.eden.d.imdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YearPageParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(YearPageParser.class);

    private static final String TITLE_ENDPOINT_START_STR = "data-tconst=\"tt";
    private static final String TITLE_ENDPOINT_END_STR = "\"";
    private static final String REGEX_STRING = Pattern.quote(TITLE_ENDPOINT_START_STR) + "(.*?)" + Pattern.quote(TITLE_ENDPOINT_END_STR);
    private static final Pattern PATTERN = Pattern.compile(REGEX_STRING);

    public Set<String> titlesFromPage(String yearPageSource) {

        Set<String> imdbIds = new HashSet<>();
        Matcher matcher = PATTERN.matcher(yearPageSource);

        String imdbId;

        while (matcher.find()) {
            imdbId = matcher.group(1); // Since (.*?) is capturing group 1
            try {
                imdbIds.add(imdbId);
            } catch (Exception e) {
                LOGGER.error("problem parsing page source", e);
            }
        }

        return imdbIds;
    }
}
