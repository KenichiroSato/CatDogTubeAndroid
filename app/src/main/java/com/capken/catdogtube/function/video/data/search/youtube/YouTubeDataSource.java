package com.capken.catdogtube.function.video.data.search.youtube;

import android.util.Log;

import com.capken.catdogtubedomain.video.data.YouTubeVideo;
import com.capken.catdogtubedomain.video.domain.search.SearchVideoDataSourceProtocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import info.vividcode.time.iso8601.Iso8601ExtendedOffsetDateTimeFormat;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
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

    private final int OLDEST_YEAR = 2011;
    private final int OLDEST_MONTH = 1;
    private final int OLDEST_DAY = 1;


    private final int SEARCH_PERIOD_DAYS = 30;

    @Override
    public void searchVideos(@NotNull String searchWord,
                             @Nullable String token,
                             @NotNull final Function2<? super List<YouTubeVideo>,
                                     ? super String, Unit> completionHandler) {

        PublishedParamPair publishedParam = generatePublishedParams();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = new HttpUrl.Builder()
                .scheme("https")
                .host("www.googleapis.com")
                .addPathSegments("youtube/v3/search")
                .addQueryParameter("key", YouTubeInfo.INFO)
                .addQueryParameter("q", searchWord)
                .addQueryParameter("part", "snippet")
                .addQueryParameter("type", "video")
                .addQueryParameter("videoDuration", "short")
                .addQueryParameter("maxResults", "50")
                .addQueryParameter("order", "viewCount")
                .addQueryParameter("publishedBefore", publishedParam.before)
                .addQueryParameter("publishedAfter", publishedParam.after);
        if (token != null && !token.isEmpty()) {
            builder.addQueryParameter("pageToken", token);
        }
        HttpUrl url = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("tag", e.getMessage());
                completionHandler.invoke(null, null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // リクエスト成功時の処理
                // ステータスコードが200かどうか
                if (response.code() == 200) {
                    generatePublishedParams();
                    String res = response.body().string();
                    List<YouTubeVideo> videos = YouTubeDataParser.parse(res);
                    String token = YouTubeDataParser.getPageToken(res);
                    completionHandler.invoke(videos, token);
                } else {
                    Log.d("tag", "res=" + response.toString());
                    completionHandler.invoke(null, null);
                }
            }
        });
    }

    private PublishedParamPair generatePublishedParams() {

        Calendar oldestCal = new GregorianCalendar(OLDEST_YEAR, OLDEST_MONTH, OLDEST_DAY);
        long oldest = oldestCal.getTime().getTime();

        long now = System.currentTimeMillis();
        long publishedBefore = randomLongBetween(now, oldest);
        long publishedAfter = publishedBefore - (SEARCH_PERIOD_DAYS * TimeUnit.DAYS.toMillis(1L));

        String beforeStr = RFC3339FromTimeMillis(publishedBefore);
        String afterStr = RFC3339FromTimeMillis(publishedAfter);

        List<String> params = new ArrayList<>();
        params.add(beforeStr);
        params.add(afterStr);


        //cat
        //params["publishedBefore"] = "2015-03-13T15:00:26Z"//2014-02-18T17:30:05Z
        //params["publishedAfter"] =  "2015-02-11T15:00:26Z"//2014-01-19T17:30:05Z

        //dog
        //params["publishedBefore"] = "2013-08-12T18:01:59Z"//"2012-09-04T05:47:54Z"
        //params["publishedAfter"] =  "2013-07-13T18:01:59Z"//"2012-08-05T05:47:54Z"

        Log.d("gap", beforeStr);
        Log.d("gap", afterStr);

        return new PublishedParamPair(beforeStr, afterStr);

    }

    private long randomLongBetween(long max, long min) {
        long gap = max - min;
        Random rand = new Random();
        long randVal = Math.abs(rand.nextLong() % gap);
        return min + randVal;
    }

    private String RFC3339FromTimeMillis(long timeMillis) {
        DateFormat format = new Iso8601ExtendedOffsetDateTimeFormat();
        return format.format(timeMillis);
    }

    class PublishedParamPair {
        final String before;
        final String after;

        PublishedParamPair(String before, String after) {
            this.before = before;
            this.after = after;
        }
    }


}
