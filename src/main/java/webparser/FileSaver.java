package webparser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSaver {
    private static final Logger logger = LoggerFactory.getLogger(FileSaver.class);

    public static void saveResultsToFile(List<ParseResult> results, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            results.forEach(r -> writer.println(r.title + " - " + r.href));
            logger.info("Результаты сохранены в файл: {}", filename);
        } catch (IOException e) {
            logger.error("Ошибка при сохранении результатов: {}", e.getMessage());
        }
    }
} 