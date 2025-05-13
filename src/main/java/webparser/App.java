package webparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final String DEFAULT_URL = "https://www.pathofexile.com/";
    private static final String RESULTS_FILE = "news_results.txt";

    public static void main(String[] args) {
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Парсер новостей Path of Exile ===");
            System.out.println("1. Парсить первую новость");
            System.out.println("2. Парсить все новости");
            System.out.println("3. Просмотреть результаты");
            System.out.println("4. Выход");
            System.out.print("Выберите действие (1-4): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    parseFirstNews(parser);
                    break;
                case "2":
                    parseAllNews(parser);
                    break;
                case "3":
                    viewResults();
                    break;
                case "4":
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void parseFirstNews(Parser parser) {
        Optional<ParseResult> result = parser.parseNewsTitleAndHref(DEFAULT_URL);
        if (result.isPresent()) {
            ParseResult news = result.get();
            System.out.println("\nНайдена новость:");
            System.out.println("Заголовок: " + news.title);
            System.out.println("Ссылка: " + news.href);
        } else {
            System.out.println("Новости не найдены.");
        }
    }

    private static void parseAllNews(Parser parser) {
        System.out.println("\nПарсинг всех новостей...");
        List<ParseResult> results = parser.parseAllNews(DEFAULT_URL);
        if (!results.isEmpty()) {
            System.out.println("Найдено новостей: " + results.size());
            FileSaver.saveResultsToFile(results, RESULTS_FILE);
            System.out.println("Результаты сохранены в файл: " + RESULTS_FILE);
        } else {
            System.out.println("Новости не найдены.");
        }
    }

    private static void viewResults() {
        try {
            java.io.File file = new java.io.File(RESULTS_FILE);
            if (!file.exists()) {
                System.out.println("Файл с результатами не найден. Сначала выполните парсинг.");
                return;
            }

            System.out.println("\nСодержимое файла " + RESULTS_FILE + ":");
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (java.io.IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
