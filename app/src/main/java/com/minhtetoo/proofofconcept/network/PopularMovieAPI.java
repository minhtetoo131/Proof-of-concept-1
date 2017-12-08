package com.minhtetoo.proofofconcept.network;

import com.minhtetoo.proofofconcept.network.response.GetPopularMovieResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by min on 12/7/2017.
 */

public interface PopularMovieAPI {


    @FormUrlEncoded
    @POST("v1/getPopularMovies.php")
    Call<GetPopularMovieResponse> loadPopularMovie(
            @Field("page") int pageIndex,
            @Field("access_token") String acessToken);

}
