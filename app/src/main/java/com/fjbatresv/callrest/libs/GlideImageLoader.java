package com.fjbatresv.callrest.libs;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fjbatresv.callrest.libs.base.ImageLoader;

/**
 * Created by javie on 27/09/2016.
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager requestManager;

    public GlideImageLoader(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public GlideImageLoader(Context context) {
        this.requestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView imageView, String url) {
        this.requestManager
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .override(700,700)
                .into(imageView);
    }
}
