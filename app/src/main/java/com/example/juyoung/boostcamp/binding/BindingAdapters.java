package com.example.juyoung.boostcamp.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.juyoung.boostcamp.model.Movie;
import com.example.juyoung.boostcamp.model.RetrofitService;

import java.util.List;


public class BindingAdapters {

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(recyclerView.getContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView imageView, String url, Drawable drawable) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .override(imageView.getHeight() / 3 * 2, imageView.getHeight())
                .error(drawable);

        Glide.with(imageView.getContext()).load(url).apply(options).into(imageView);
    }

}
