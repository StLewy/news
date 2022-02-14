package pl.api.news;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.api.news.model.NewsDTO;
import pl.api.news.service.NewsService;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

@SpringBootApplication
@AllArgsConstructor
public class NewsApplication {
    @Autowired
    NewsService newsService;

    public static void main(String[] args) {
        SpringApplication.run(NewsApplication.class, args);

    }
    @PostConstruct
    public void init() throws FileNotFoundException {
        newsService.getNewsDTOFromAPI("pl","business");
    }
}
