package com.example.juyoung.boostcamp.model;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    public static final String API_URL = "https://openapi.naver.com/";

    @Headers({"X-Naver-Client-Id: DpTPvpEoBeEwKpsEzbfF",
            "X-Naver-Client-Secret: cEyO35Yw6M"})
    @GET("v1/search/movie.json")
    Call<Message> getMoiveList(@Query("query") String keyword,@Query("display") int display,@Query("start") int start);

}
