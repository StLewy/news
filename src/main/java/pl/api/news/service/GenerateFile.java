package pl.api.news.service;

import org.springframework.stereotype.Service;
import pl.api.news.model.NewsDTO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
@Service
public class GenerateFile {

    public void generetTxtFile(List<NewsDTO> newsList) throws FileNotFoundException {
        PrintWriter saveFile = new PrintWriter("news.txt");
        for (NewsDTO news: newsList){
            saveFile.println("title: " + news.getTitle() + " description: " + news.getDescription() + " author: " + news.getAuthor());
        }
        saveFile.close();
    }
}
