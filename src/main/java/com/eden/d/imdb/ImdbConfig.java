package com.eden.d.imdb;

import com.eden.d.broadcaster.Broadcaster;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImdbConfig {

    @Value("${imdb.year.max.pages}")
    private int maxYearPagesToTry;

    @Bean
    public ImdbIdsCollector imdbYearPageIdCollector(YearPageImdbIdsService yearPageImdbIdsService,
                                                           Broadcaster broadcaster) {
        return new ImdbIdsCollector(yearPageImdbIdsService, broadcaster, maxYearPagesToTry);
    }

    @Bean
    public YearPageImdbIdsService yearPageImdbIdsService(PageSourceService pageSourceService,
                                                         YearPageParser yearPageParser) {
        return new YearPageImdbIdsService(pageSourceService, yearPageParser);
    }

    @Bean
    public PageSourceService pageSourceService(PageSourceClient pageSourceClient) {
        return new PageSourceService(pageSourceClient);
    }

    @Bean
    public PageSourceClient pageSourceClient() {
        return new PageSourceClient();
    }

    @Bean
    public YearPageParser yearPageParser() {
        return new YearPageParser();
    }
}
