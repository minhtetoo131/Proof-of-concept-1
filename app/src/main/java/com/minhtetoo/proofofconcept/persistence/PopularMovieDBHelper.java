package com.minhtetoo.proofofconcept.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by min on 12/14/2017.
 */

public class PopularMovieDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "popularmovie.db";


    private static final String SQL_CREATE_POPULAR_MOVIE_TABLE = "CREATE TABLE " + MovieContract.PopularMovieEntry.TABLE_NAME + " (" +
            MovieContract.PopularMovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.PopularMovieEntry.COLUMN_VOTE_COUNT + " INTEGER, " +
            MovieContract.PopularMovieEntry.COLUMN_MOVIE_ID + " INTEGER, " +
            MovieContract.PopularMovieEntry.COLUMN_VIDEO + " BOOLEAN, " +
            MovieContract.PopularMovieEntry.COLUMN_VOTE_AVETAGE + " DOUBLE, " +
            MovieContract.PopularMovieEntry.COLUMN_TITLE + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_POPULARITY + " DOUBLE, " +
            MovieContract.PopularMovieEntry.COLUMN_POSTER_PATH + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_BACK_DROP_PATH + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_ADULT + " BOOLEAN, " +
            MovieContract.PopularMovieEntry.COLUMN_OVER_VIEW + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_RELEASE_DATE + " TEXT, " +

            " UNIQUE (" + MovieContract.PopularMovieEntry.COLUMN_TITLE + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_POPULAR_MOVIE_GENRE_ID_TABLE = "CREATE TABLE " + MovieContract.GenreEntry.TABLE_NAME + " (" +
            MovieContract.GenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.GenreEntry.COLUMN_GENRE_ID + " INTEGER, " +


            " UNIQUE ("  +
            MovieContract.GenreEntry.COLUMN_GENRE_ID + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_GENRE_IN_MOVIE_TABLE = "CREATE TABLE " + MovieContract.GenreInMovieEntry.TABLE_NAME + " (" +
            MovieContract.GenreInMovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.GenreInMovieEntry.COLUMN_GENRE_ID + " INTEGER, " +
            MovieContract.GenreInMovieEntry.COLUMN_MOVIE_ID + " INTEGER"  +

            " );";



    public PopularMovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_POPULAR_MOVIE_TABLE);
        db.execSQL(SQL_CREATE_POPULAR_MOVIE_GENRE_ID_TABLE);
        db.execSQL(SQL_CREATE_GENRE_IN_MOVIE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.PopularMovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.GenreEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.GenreInMovieEntry.TABLE_NAME);


        onCreate(db);
    }
}
