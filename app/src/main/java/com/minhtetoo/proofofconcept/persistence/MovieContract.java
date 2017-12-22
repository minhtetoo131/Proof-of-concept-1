package com.minhtetoo.proofofconcept.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.minhtetoo.proofofconcept.ProofOfConcept;

/**
 * Created by min on 12/14/2017.
 */

public class MovieContract {
    public static final String CONTENT_AUTHORITY = ProofOfConcept.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_POPULAR_MOVIES = "popular_movies";
    public static final String PATH_GENRES = "popular_movie_genre_ids";
    public static final String PATH_GENRE_IN_MOVIE = "genre_in_movies";

    public static final class PopularMovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_POPULAR_MOVIES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POPULAR_MOVIES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POPULAR_MOVIES;

        public static final String TABLE_NAME = "popular_movies";

        public static final String COLUMN_VOTE_COUNT = "voteCount";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_VIDEO = "video";
        public static final String COLUMN_VOTE_AVETAGE = "voteAverage";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_POSTER_PATH = "posterPath";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "originalLanguage";
        public static final String COLUMN_ORIGINAL_TITLE = "originalTitle";
        public static final String COLUMN_BACK_DROP_PATH= "backdropPath";
        public static final String COLUMN_ADULT = "adult";
        public static final String COLUMN_OVER_VIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "releaseDate";

        public static Uri buildPopularMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class GenreEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GENRES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRES;

        public static final String TABLE_NAME = "popular_movie_genre_ids";

        public static final String COLUMN_GENRE_ID = "genre_id";

        public static Uri buildPopularMovieGenreIdUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class GenreInMovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GENRE_IN_MOVIE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRE_IN_MOVIE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRE_IN_MOVIE;

        public static final String TABLE_NAME = PATH_GENRE_IN_MOVIE;

        public static final String COLUMN_GENRE_ID = "genre_id";
        public static final String COLUMN_MOVIE_ID = "movie_id";

        public static Uri buildPopularMovieGenreIdUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildMovieUriWithGenre(String genreId) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_GENRE_ID, genreId)
                    .build();
        }

        public static String getGenreFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_GENRE_ID);
        }
        public static Uri buildGenreUriWithMovieId(String movieId) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_MOVIE_ID, movieId)
                    .build();
        }

        public static String getMovieIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_MOVIE_ID);
        }



    }
}
