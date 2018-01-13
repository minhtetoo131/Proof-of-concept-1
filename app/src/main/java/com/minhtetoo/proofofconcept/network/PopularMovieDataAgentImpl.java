package com.minhtetoo.proofofconcept.network;

import android.content.Context;

import com.minhtetoo.proofofconcept.events.RestApiEvents;
import com.minhtetoo.proofofconcept.network.response.GetPopularMovieResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by min on 12/7/2017.
 */

public class PopularMovieDataAgentImpl implements PopularMovieDataAgent {



    PopularMovieAPI theAPI;

    public PopularMovieDataAgentImpl() {

        OkHttpClient okHttpClient= new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/popular-movies/apis/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(PopularMovieAPI.class);
    }




    @Override
    public void loadPopularMovies(String acessToken, int pageNo, final Context context) {

        Call<GetPopularMovieResponse> loadMMNewsCall= theAPI.loadPopularMovie(pageNo,acessToken);

        loadMMNewsCall.enqueue(new Callback<GetPopularMovieResponse>() {
            @Override
            public void onResponse(Call<GetPopularMovieResponse> call,
                                   Response<GetPopularMovieResponse> response) {

                GetPopularMovieResponse GetPopularMovieResponse = response.body();

                if(GetPopularMovieResponse !=null && GetPopularMovieResponse.getPopularMovies().size()> 0){
                    RestApiEvents.PopularMovieLoadedEvent PopularMovieLoadedEvent = new
                            RestApiEvents.PopularMovieLoadedEvent(GetPopularMovieResponse.getPage(),
                            GetPopularMovieResponse.getPopularMovies(),context);

                    EventBus.getDefault().post(PopularMovieLoadedEvent);



                }else {

                    RestApiEvents.ErrorInvokingAPIEvent errorEvent =new
                            RestApiEvents.ErrorInvokingAPIEvent("no data could be load");

                    EventBus.getDefault().post(errorEvent);


                }

            }



            @Override
            public void onFailure(Call<GetPopularMovieResponse> call,
                                  Throwable t) {

                RestApiEvents.ErrorInvokingAPIEvent errorInvokingAPIEvent = new RestApiEvents.ErrorInvokingAPIEvent(t.getMessage());
                EventBus.getDefault().post(errorInvokingAPIEvent);


            }
        });

    }
}
