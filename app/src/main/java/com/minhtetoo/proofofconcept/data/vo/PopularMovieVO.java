package com.minhtetoo.proofofconcept.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.minhtetoo.proofofconcept.persistence.MovieContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by min on 12/7/2017.
 */

public class PopularMovieVO {

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("id")
    private int id;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private  double voteAverage;

    @SerializedName("title")
    private  String title;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private  String originalLanguage;

    @SerializedName("original_title")
    private  String originalTitle;

    @SerializedName("genre_ids")
    private  List<Integer> genreIds;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private  boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private  String releaseDate;

    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public ContentValues parseToContentValue() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_VOTE_COUNT, voteCount);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_MOVIE_ID, id);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_VIDEO, video);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_VOTE_AVETAGE, voteAverage);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_TITLE, title);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_POPULARITY,popularity);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_POSTER_PATH, posterPath);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_LANGUAGE, originalLanguage);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_TITLE, originalTitle);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_BACK_DROP_PATH, backdropPath);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_ADULT, adult);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_OVER_VIEW, overview);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_RELEASE_DATE, releaseDate);

        return contentValues;
    }

    public static  PopularMovieVO parseFromCursor(Context context,Cursor cursor){
        PopularMovieVO popularMovieVO = new PopularMovieVO();
        popularMovieVO.voteCount = cursor.getInt(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_VOTE_COUNT));
        popularMovieVO.id = cursor.getInt(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_MOVIE_ID));
        popularMovieVO.video = cursor.getInt(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_VIDEO)) == 1;
        popularMovieVO.voteAverage = cursor.getDouble(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_VOTE_AVETAGE));
        popularMovieVO.title = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_TITLE));
        popularMovieVO.popularity = cursor.getDouble(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_POPULARITY));
        popularMovieVO.posterPath = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_POSTER_PATH));
        popularMovieVO.originalLanguage = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_LANGUAGE));
        popularMovieVO.originalTitle = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_TITLE));
        popularMovieVO.backdropPath = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_BACK_DROP_PATH));
        popularMovieVO.adult = cursor.getInt(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_ADULT))  == 1;
        popularMovieVO.overview = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_OVER_VIEW));
        popularMovieVO.releaseDate = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_RELEASE_DATE));

        popularMovieVO.genreIds = loadGenresInMovie(context,popularMovieVO.id);
        return  popularMovieVO;
    }

    private static List<Integer> loadGenresInMovie(Context context, int id) {
        Cursor genreIdsInMovieCursor = context.getContentResolver().query(MovieContract.GenreInMovieEntry.CONTENT_URI,
                null,
                MovieContract.GenreInMovieEntry.COLUMN_MOVIE_ID + " = ?", new String[]{id+""},
                null);
        if (genreIdsInMovieCursor != null && genreIdsInMovieCursor.moveToFirst()) {
            List<Integer> genreIdsInMovie = new ArrayList<>();
            do {
                genreIdsInMovie.add(
                        genreIdsInMovieCursor.getInt(
                                genreIdsInMovieCursor.getColumnIndex(MovieContract.GenreInMovieEntry.COLUMN_GENRE_ID)));
            } while (genreIdsInMovieCursor.moveToNext());
            genreIdsInMovieCursor.close();

            return genreIdsInMovie;

        }
        return null;
    }
}
