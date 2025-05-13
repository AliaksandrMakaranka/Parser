package webparser;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    @Test
    void testParseNewsTitleAndHrefSuccess() {
        Parser parser = new Parser();
        Optional<ParseResult> result = parser.parseNewsTitleAndHref("https://www.pathofexile.com/");
        assertTrue(result.isPresent(), "Должен быть результат для главной страницы");
        assertNotNull(result.get().title);
        assertFalse(result.get().title.isEmpty(), "Заголовок не должен быть пустым");
        assertNotNull(result.get().href);
        assertFalse(result.get().href.isEmpty(), "Ссылка не должна быть пустой");
    }

    @Test
    void testParseNewsTitleAndHrefInvalidUrl() {
        Parser parser = new Parser();
        Optional<ParseResult> result = parser.parseNewsTitleAndHref("https://nonexistent.example.com/");
        assertFalse(result.isPresent(), "Для несуществующего URL результат должен быть пустым");
    }

    @Test
    void testParseAllNews() {
        Parser parser = new Parser();
        List<ParseResult> results = parser.parseAllNews("https://www.pathofexile.com/");
        assertFalse(results.isEmpty(), "Список новостей не должен быть пустым");
        results.forEach(r -> {
            assertNotNull(r.title);
            assertFalse(r.title.isEmpty(), "Заголовок не должен быть пустым");
            assertNotNull(r.href);
            assertFalse(r.href.isEmpty(), "Ссылка не должна быть пустой");
        });
    }

    @Test
    void testSaveResultsToFile() {
        Parser parser = new Parser();
        List<ParseResult> results = parser.parseAllNews("https://www.pathofexile.com/");
        String filename = "test_news_results.txt";
        FileSaver.saveResultsToFile(results, filename);
        // Проверка, что файл создан и не пустой
        assertTrue(new java.io.File(filename).exists(), "Файл должен быть создан");
        assertTrue(new java.io.File(filename).length() > 0, "Файл не должен быть пустым");
        // Удаление тестового файла
        new java.io.File(filename).delete();
    }
} 