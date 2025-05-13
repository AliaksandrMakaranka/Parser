package webparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parser {
    private static final Logger logger = LoggerFactory.getLogger(Parser.class);
    private static final String NEWS_SELECTOR = "//*[@class='newsListItem']/h2/a";

    public Optional<ParseResult> parseNewsTitleAndHref(String url) {
        try {
            Document page = Jsoup.connect(url).get();
            Elements titles = page.selectXpath(NEWS_SELECTOR);
            if (titles.isEmpty()) {
                logger.warn("Новости не найдены на странице: {}", url);
                return Optional.empty();
            }
            String title = titles.first().text();
            String href = titles.first().attr("href");
            logger.debug("Парсинг успешен: {} - {}", title, href);
            return Optional.of(new ParseResult(title, href));
        } catch (IOException e) {
            logger.error("Ошибка при парсинге: {}", e.getMessage());
            return Optional.empty();
        }
    }

    public List<ParseResult> parseAllNews(String url) {
        List<ParseResult> results = new ArrayList<>();
        try {
            Document page = Jsoup.connect(url).get();
            Elements titles = page.selectXpath(NEWS_SELECTOR);
            
            for (int i = 0; i < titles.size(); i++) {
                String titleText = titles.get(i).text();
                String href = titles.get(i).attr("href");
                results.add(new ParseResult(titleText, href));
                logger.debug("Добавлена новость {}: {} - {}", i + 1, titleText, href);
            }
            
            logger.info("Всего найдено новостей: {}", results.size());
        } catch (IOException e) {
            logger.error("Ошибка при парсинге всех новостей: {}", e.getMessage());
        }
        return results;
    }
} 