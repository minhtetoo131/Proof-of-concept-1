package com.minhtetoo.proofofconcept.persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by min on 12/22/2017.
 */

public class MovieProvider extends ContentProvider {


    public static final int POPULAR_MOVIE = 100;
    public static final int GENRE_ID = 200;
    public static final int GENRE_IN_MOVIE = 300;

    private PopularMovieDBHelper movieDBHelper ;

    private static final UriMatcher sUriMatcher = BuildUriMatcher();

    private static UriMatcher BuildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MovieContract.CONTENT_AUTHORITY,MovieContract.PATH_POPULAR_MOVIES, POPULAR_MOVIE);
        uriMatcher.addURI(MovieContract.CONTENT_AUTHORITY,MovieContract.PATH_GENRES, GENRE_ID);
        uriMatcher.addURI(MovieContract.CONTENT_AUTHORITY,MovieContract.PATH_GENRE_IN_MOVIE, GENRE_IN_MOVIE);

        return uriMatcher;
    }

    private String getTableName(Uri uri){

        switch(sUriMatcher.match(uri)){
            case POPULAR_MOVIE:
                return MovieContract.PopularMovieEntry.TABLE_NAME ;
            case GENRE_ID:
                return MovieContract.GenreEntry.TABLE_NAME ;
            case GENRE_IN_MOVIE:
                return MovieContract.GenreInMovieEntry.TABLE_NAME ;

        }

        return  null;

    }

    private Uri getContentUri(Uri uri){
        switch(sUriMatcher.match(uri)){
            case POPULAR_MOVIE:
                return MovieContract.PopularMovieEntry.CONTENT_URI;
            case GENRE_ID:
                return MovieContract.GenreEntry.CONTENT_URI;
            case GENRE_IN_MOVIE:
                return MovieContract.GenreInMovieEntry.CONTENT_URI;
        }
        return null;
    }



    @Override
    public boolean onCreate() {
        movieDBHelper = new PopularMovieDBHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor queryCursor = movieDBHelper.getReadableDatabase().query(getTableName(uri),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        Context context = getContext();
        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case POPULAR_MOVIE:
                return MovieContract.PopularMovieEntry.DIR_TYPE ;
            case GENRE_ID:
                return MovieContract.GenreEntry.DIR_TYPE ;
            case GENRE_IN_MOVIE:
                return MovieContract.GenreInMovieEntry.DIR_TYPE ;

        }

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id =db.insert(tableName,null,values);
        if (_id > 0 ){

            Uri contentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(contentUri, _id);

            if (getContext() !=null){
                getContext().getContentResolver().notifyChange(uri,null);
            }

            return insertedUri;

        }
        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, values , selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
