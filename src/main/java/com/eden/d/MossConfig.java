package com.eden.d;

import com.eden.d.imdb.ImdbIdsCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MossConfig {

    @Bean
    public MossController mossController(ImdbIdsCollector collector) {
        return new MossController(collector);
    }

}
