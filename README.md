# WebParser

Console web scraper in Java: collects news headlines and links from a website and saves them to a file. Built as a base for price, news and competitor monitoring tools.

**Stack:** Java 17 · Jsoup · SLF4J + Logback · JUnit 5 · Maven

## Features

Interactive menu after start:

1. **Parse first news item**: shows the headline and link of the latest news
2. **Parse all news**: collects every headline on the page and saves them to `news_results.txt`
3. **View results**: prints the saved file
4. **Exit**

The demo target is the news page of [pathofexile.com](https://www.pathofexile.com/).

## Running

Requirements: JDK 17+, Maven.

```bash
git clone https://github.com/AliaksandrMakaranka/Parser.git
cd Parser
mvn compile exec:java -Dexec.mainClass=webparser.App
```

Tests:

```bash
mvn test
```

## Structure

| Class | Responsibility |
|---|---|
| `App` | Interactive console menu |
| `Parser` | Downloads the page and extracts headlines and links (Jsoup) |
| `ParseResult` | Holds one result: title and URL |
| `FileSaver` | Writes results to a file |

Logs are written to `logs/webparser.log`; the level is set in `src/main/resources/logback.xml`.

---

### 🇵🇱 Opis

Parser stron WWW w Javie (Jsoup): pobiera nagłówki i linki do wiadomości i zapisuje je do pliku. Baza pod narzędzia do monitoringu cen, wiadomości i konkurencji.
