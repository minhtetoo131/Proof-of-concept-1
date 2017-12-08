package com.minhtetoo.proofofconcept.events;

import com.minhtetoo.proofofconcept.data.VO.PopularMovieVO;

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

        public PopularMovieLoadedEvent(int loadedPageIndex, List<PopularMovieVO> loadNews) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadedPopularMovies = loadNews;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<PopularMovieVO> getLoadedPopularMovies() {
            return loadedPopularMovies;
        }
    }
}
