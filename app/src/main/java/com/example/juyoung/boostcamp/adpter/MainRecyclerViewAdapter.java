package com.example.juyoung.boostcamp.adpter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juyoung.boostcamp.R;
import com.example.juyoung.boostcamp.databinding.ItemMainBinding;
import com.example.juyoung.boostcamp.model.Movie;

import java.util.ArrayList;
import java.util.List;


public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Movie> movieList;

    public MainRecyclerViewAdapter(Context context) {
        this.context=context;
    }

    public MainRecyclerViewAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        final Movie movie = getItem(position);
        viewHolder.binding.setMovie(movie);
        viewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(context.getResources().getColor(R.color.colorPrimary));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(movie.getLink()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        }
        return movieList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMainBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

    }


    public Movie getItem(int position) {
        if (movieList == null) {
            return null;
        }
        return movieList.get(position);
    }

    public void updateItems(List<Movie> items) {
        if (this.movieList == null) {
            movieList = new ArrayList<>();
        }
        this.movieList.clear();
        this.movieList.addAll(items);
        notifyDataSetChanged();
    }

    public void addItems(List<Movie> items) {
        if (this.movieList == null) {
            this.movieList = items;
        } else {
            this.movieList.addAll(items);
        }
        notifyDataSetChanged();
    }


}
