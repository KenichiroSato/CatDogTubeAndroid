package com.capken.catdogtube.function.video.data.search.youtube;

import com.capken.catdogtubedomain.video.data.YouTubeVideo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2017/02/27..
 */

final class YouTubeDataParser {

    static List<YouTubeVideo> parse(String response) {
        JsonObject json = new Gson().fromJson(response, JsonObject.class);
        List<YouTubeVideo> list = new ArrayList<>();

        JsonArray items = json.get("items").getAsJsonArray();
        for (final JsonElement item : items) {
            JsonObject jsonItem = item.getAsJsonObject();
            String videoId = jsonItem.getAsJsonObject("id").get("videoId").getAsString();

            JsonObject snippet = jsonItem.getAsJsonObject("snippet");
            String title = snippet.get("title").getAsString();
            String url = snippet.getAsJsonObject("thumbnails")
                    .getAsJsonObject("medium")
                    .get("url").getAsString();

            list.add(new YouTubeVideo(videoId, title, url));
        }
        return list;
    }
}
