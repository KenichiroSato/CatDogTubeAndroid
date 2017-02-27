package com.capken.catdogtube.function.video.data.search.youtube;

import android.util.Log;

import com.capken.catdogtubedomain.video.data.YouTubeVideo;
import com.capken.catdogtubedomain.video.domain.search.SearchVideoDataSourceProtocol;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class YouTubeDataSource implements SearchVideoDataSourceProtocol {

    @Override
    public void searchVideos(@NotNull String searchWord,
                             @NotNull final Function1<? super List<YouTubeVideo>, Unit> completionHandler) {

        OkHttpClient client = new OkHttpClient();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("www.googleapis.com")
                .addPathSegments("youtube/v3/search")
                .addQueryParameter("key", YouTubeInfo.INFO)
                .addQueryParameter("q", searchWord)
                .addQueryParameter("part", "snippet")
                .addQueryParameter("type", "video")
                .addQueryParameter("videoDuration", "short")
                .addQueryParameter("maxResults", "30")
                .addQueryParameter("order", "viewCount")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("tag", e.getMessage());
                // リクエスト失敗時の処理
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // リクエスト成功時の処理
                // ステータスコードが200かどうか
                if (response.code() == 200) {
                    List<YouTubeVideo> videos = YouTubeDataParser.parse(response.body().string());
                    completionHandler.invoke(videos);
                } else {
                    Log.d("tag", "res=" + response.toString());
                    // レスポンスが200(OK)以外だった時の処理
                }
            }
        });
    }


}
