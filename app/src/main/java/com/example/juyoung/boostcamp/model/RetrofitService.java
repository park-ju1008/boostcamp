package com.example.juyoung.boostcamp.model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private ApiService apiService;
    private static RetrofitService retrofitService;

    private RetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public synchronized static RetrofitService getInstance() {
        if (retrofitService == null) {
            retrofitService = new RetrofitService();
        }
        return retrofitService;
    }

    public List<Movie> getMoviewList(final String keyword, final int disply, final int page) {
        final ArrayList<Movie> movies = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<Message> response = apiService.getMoiveList(keyword, disply, page).execute();
                    if (response.isSuccessful() && response.body().getTotal() != 0) {
                        movies.addAll(response.body().getItems());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
