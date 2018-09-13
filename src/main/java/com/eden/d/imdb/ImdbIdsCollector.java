package com.eden.d.imdb;

import com.eden.d.broadcaster.Broadcaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ImdbIdsCollector {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImdbIdsCollector.class);

    private final YearPageImdbIdsService yearPageImdbIdsService;
    private final Broadcaster broadcaster;
    private final int maxPagesToTry;

    public ImdbIdsCollector(YearPageImdbIdsService yearPageImdbIdsService,
                            Broadcaster broadcaster,
                            int maxPagesToTry) {
        this.yearPageImdbIdsService = yearPageImdbIdsService;
        this.broadcaster = broadcaster;
        this.maxPagesToTry = maxPagesToTry;
    }

    public void collect(List<Integer> years) {
        years.forEach(this::scrapeYearForImdbIds);
    }

    private void scrapeYearForImdbIds(int year) {
        for (int i = 0; i < maxPagesToTry; i++) {
            Optional<Set<String>> imdbIdsOptional = yearPageImdbIdsService
                    .imdbIdsFromFromYearPage(year, i);

            if (imdbIdsOptional.isPresent()) {
                Set<String> imdbIds = imdbIdsOptional.get();
                imdbIds.forEach(broadcaster::broadcast);

                LOGGER.debug("Broadcasted {} imdb ids for year {} on page {}", imdbIds.size(), year, i);
            } else {
                LOGGER.info("Stopped scraping year page for year {} on page {}", year, i);
                break;
            }
        }
    }
}
