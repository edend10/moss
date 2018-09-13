package com.eden.d.imdb;

import java.util.Optional;
import java.util.Set;

public class YearPageImdbIdsService {
    private final PageSourceService pageSourceService;
    private final YearPageParser yearPageParser;

    public YearPageImdbIdsService(PageSourceService pageSourceService, YearPageParser yearPageParser) {
        this.pageSourceService = pageSourceService;
        this.yearPageParser = yearPageParser;
    }

    public Optional<Set<String>> imdbIdsFromFromYearPage(int year, int pageNumber) {
        Optional<String> pageSourceOptional = pageSourceService.getYearPageSource(year, pageNumber);
        if(pageSourceOptional.isPresent()) {
            String pageSource = pageSourceOptional.get();
            Set<String> imdbIds = yearPageParser.titlesFromPage(pageSource);
            return Optional.of(imdbIds);
        } else {
            return Optional.empty();
        }
    }
}
