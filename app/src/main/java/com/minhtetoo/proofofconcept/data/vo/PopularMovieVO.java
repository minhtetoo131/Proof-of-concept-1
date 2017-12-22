package com.minhtetoo.proofofconcept.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.minhtetoo.proofofconcept.persistence.MovieContract;

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
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_ID, id);
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

    public static  PopularMovieVO parseFromCursor(Cursor cursor){
        PopularMovieVO PopularMovieVO = new PopularMovieVO();
        PopularMovieVO.voteCount = cursor.getInt(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_VOTE_COUNT));
        PopularMovieVO.id = cursor.getInt(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_ID));
        PopularMovieVO.video = cursor.getInt(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_VIDEO)) == 1;
        PopularMovieVO.voteAverage = cursor.getDouble(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_VOTE_AVETAGE));
        PopularMovieVO.title = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_TITLE));
        PopularMovieVO.popularity = cursor.getDouble(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_POPULARITY));
        PopularMovieVO.posterPath = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_POSTER_PATH));
        PopularMovieVO.originalLanguage = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_LANGUAGE));
        PopularMovieVO.originalTitle = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_TITLE));
        PopularMovieVO.backdropPath = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_BACK_DROP_PATH));
        PopularMovieVO.adult = cursor.getInt(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_ADULT))  == 1;
        PopularMovieVO.overview = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_OVER_VIEW));
        PopularMovieVO.releaseDate = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_RELEASE_DATE));

        return  PopularMovieVO;
    }
}
