package com.eden.d;

import com.eden.d.broadcaster.Broadcaster;
import com.eden.d.imdb.ImdbIdsCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MossController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MossController.class);

    private final ImdbIdsCollector imdbIdsCollector;

    public MossController(ImdbIdsCollector imdbIdsCollector) {
        this.imdbIdsCollector = imdbIdsCollector;
    }

    @GetMapping("/start")
    public void start(@RequestParam("years")List<Integer> years) {
        LOGGER.info("Collecting for years: {}", years);
        imdbIdsCollector.collect(years);
    }

}
