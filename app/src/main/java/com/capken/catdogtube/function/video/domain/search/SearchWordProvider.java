package com.capken.catdogtube.function.video.domain.search;

import android.content.Context;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.domain.model.ContentType;
import com.capken.catdogtubedomain.video.domain.search.SearchWordProviderProtocol;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class SearchWordProvider implements SearchWordProviderProtocol {

    private final Context mContext;

    @Inject
    public SearchWordProvider(Context context) {
        mContext = context;
    }

    @NotNull
    @Override
    public String searchWord(@NotNull ContentType content) {
        int id;
        switch (content) {
            case dog:
                id = R.string.SEARCH_WORD_DOG;
                break;
            case cat:
                id = R.string.SEARCH_WORD_CAT;
                break;
            default:
                id = R.string.SEARCH_WORD_CAT;
        }
        return mContext.getResources().getString(id);
    }
}
