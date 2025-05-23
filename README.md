# WebParser

Этот проект — простой парсер новостей с сайта https://www.pathofexile.com/ на Java с использованием библиотеки Jsoup.

## Как использовать

1. Убедитесь, что у вас установлен JDK 11+ и Maven.
2. Соберите и запустите проект:

```
mvn compile exec:java -Dexec.mainClass=webparser.App
```

## Функционал

После запуска вы увидите интерактивное меню с опциями:
1. Парсить первую новость - показывает заголовок и ссылку на первую новость
2. Парсить все новости - парсит все новости и сохраняет их в файл
3. Просмотреть результаты - показывает содержимое файла с результатами
4. Выход - завершает работу программы

## Результаты

Результаты парсинга сохраняются в файл `news_results.txt` в корневой директории проекта. Каждая строка содержит заголовок новости и ссылку на неё.

## Тесты

Для запуска тестов:

```
mvn test
```

## Описание

- Основная логика парсинга вынесена в класс `Parser`.
- Метод `parseNewsTitleAndHref` возвращает заголовок и ссылку на первую новость.
- Метод `parseAllNews` парсит все новости на странице.
- Метод `saveResultsToFile` сохраняет результаты в файл.
- Логирование реализовано с помощью SLF4J и Logback.

## Структура

- `src/main/java/webparser/App.java` — основной класс с интерактивным меню.
- `src/main/java/webparser/Parser.java` — класс для парсинга новостей.
- `src/main/java/webparser/ParseResult.java` — класс для хранения результатов парсинга.
- `src/main/java/webparser/FileSaver.java` — класс для сохранения результатов в файл.
- `src/test/java/webparser/AppTest.java` — тесты для парсера.

## Логи

Логи сохраняются в файл `logs/webparser.log`. Уровень логирования можно настроить в `src/main/resources/logback.xml`.
