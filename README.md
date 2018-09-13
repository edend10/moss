# Moss
IMDB id collector and producer (for now).

## Project structure:
- `broadcaster` package:
    - kafka producer
- `imdb` package:
    - Year search page scraper for imdb ids
    - broadcasts ids via kafka to topic
- `MossController` initialize collection/broadcast

## TODO:
- client library (separate project?)
    - kafka consumer
    - workflow:
        - scrape title page for metadata
        - scrape wayback for historic ratings
        - persist title+ratings(+page source?)
            - mysql?
            - elasticsearch?
            - both?
- generalize collector + broadcaster
    design options:
    - pass broadcaster as field of collector?
    - pass broadcaster callback into collector?
    - other
