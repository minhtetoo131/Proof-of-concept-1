package com.minhtetoo.proofofconcept.events;

import android.content.Context;

import com.minhtetoo.proofofconcept.data.vo.PopularMovieVO;

import java.util.List;

/**
 * Created by min on 12/7/2017.
 */

public class RestApiEvents {

    public static class EmptyResponseEvent{



    }

    public static class ErrorInvokingAPIEvent{
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class PopularMovieLoadedEvent{
        int loadedPageIndex;
        List<PopularMovieVO> loadedPopularMovies;
        private Context context;

        public PopularMovieLoadedEvent(int loadedPageIndex, List<PopularMovieVO> loadNews,Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadedPopularMovies = loadNews;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<PopularMovieVO> getLoadedPopularMovies() {
            return loadedPopularMovies;
        }

        public Context getContext() {
            return context;
        }
    }
}
