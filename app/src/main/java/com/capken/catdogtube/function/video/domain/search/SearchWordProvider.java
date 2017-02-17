package com.capken.catdogtube.function.video.domain.search;

import android.content.Context;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.domain.model.ContentType;
import com.capken.catdogtubedomain.video.domain.search.SearchWordProviderProtocol;

import org.jetbrains.annotations.NotNull;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class SearchWordProvider implements SearchWordProviderProtocol {

    private Context context;

    public SearchWordProvider(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public String searchWord(@NotNull ContentType content) {
        int id = R.string.SEARCH_WORD_CAT;
        if (content == ContentType.dog) {
            id = R.string.SEARCH_WORD_DOG;
        }
        return context.getResources().getString(id);
    }
}
