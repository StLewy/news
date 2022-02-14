package pl.api.news.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.api.news.model.NewsDTO;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {
    private final ApiConnection apiConnection;
    private final GenerateFile generateFile;

    public void getNewsDTOFromAPI(String country, String category) throws FileNotFoundException {
        List<NewsDTO> newsDTOList = new ArrayList<>();
        String responseBody = apiConnection.getConnection(country, category);

        if (responseBody != null) {
            JsonElement element = new JsonParser().parse(responseBody);
            JsonObject object = element.getAsJsonObject();
            JsonArray list = object.get("articles").getAsJsonArray();
            for (JsonElement e : list) {
                NewsDTO newsDTO = new NewsDTO();
                if (!e.getAsJsonObject().get("title").toString().equals("null")) {
                    newsDTO.setTitle(e.getAsJsonObject().get("title").toString());
                } else {
                    newsDTO.setTitle("unknow");
                }
                if (!e.getAsJsonObject().get("description").toString().equals("null")) {
                    newsDTO.setDescription(e.getAsJsonObject().get("description").toString());
                } else {
                    newsDTO.setDescription("unknow");
                }
                if (!e.getAsJsonObject().get("author").toString().equals("null")) {
                    newsDTO.setAuthor(e.getAsJsonObject().get("author").toString());
                } else {
                    newsDTO.setAuthor("unknow");
                }
                newsDTOList.add(newsDTO);
            }
            generateFile.generetTxtFile(newsDTOList);
        }
    }

}
