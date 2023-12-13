package webparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import org.jsoup.select.Elements;

public class App {
  public static void main(String[] args) throws IOException {
    Document page = Jsoup.connect("https://www.pathofexile.com/").get();

    Elements titles  = page.selectXpath("//*[@class='newsListItem alt']/h2/a");

    String href = titles.attr("href");

    System.out.println(titles.text() +"\n"+ href);
  }
}
