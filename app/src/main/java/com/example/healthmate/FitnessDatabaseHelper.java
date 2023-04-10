package com.example.healthmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a helper class for managing the SQLite database used to store
 * fitness data in the HealthMate application.
 */
public class FitnessDatabaseHelper extends SQLiteOpenHelper {

    // Database configuration
    private static final String DATABASE_NAME = "fitness_data.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_FITNESS_DATA = "fitness_data";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_STEPS = "steps";
    private static final String COLUMN_DISTANCE = "distance";
    private static final String COLUMN_CALORIES = "calories";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    // Constructor for FitnessDatabaseHelper
    public FitnessDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_FITNESS_DATA + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STEPS + " INTEGER, " +
                COLUMN_DISTANCE + " REAL, " +
                COLUMN_CALORIES + " REAL, " +
                COLUMN_TIMESTAMP + " INTEGER)";
        db.execSQL(createTable);
    }

    // Called when database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FITNESS_DATA);
        onCreate(db);
    }

    // Save the given FitnessData object to the database, if it is not a duplicate
    public void saveFitnessData(FitnessData data) {
        if (!isDuplicateEntry(data)) { // to prevent duplicate entries
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_STEPS, data.getSteps());
            contentValues.put(COLUMN_DISTANCE, data.getDistance());
            contentValues.put(COLUMN_CALORIES, data.getCalories());
            contentValues.put(COLUMN_TIMESTAMP, data.getTimestamp());
            long id = db.insert(TABLE_FITNESS_DATA, null, contentValues);
            data.setId(id);
            db.close();
        }
    }

    // Get a list of all FitnessData objects
    public List<FitnessData> getAllFitnessData() {
        List<FitnessData> fitnessDataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_FITNESS_DATA;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int stepsIndex = cursor.getColumnIndex(COLUMN_STEPS);
                int distanceIndex = cursor.getColumnIndex(COLUMN_DISTANCE);
                int caloriesIndex = cursor.getColumnIndex(COLUMN_CALORIES);
                int timestampIndex = cursor.getColumnIndex(COLUMN_TIMESTAMP);

                if (stepsIndex >= 0 && distanceIndex >= 0 && caloriesIndex >= 0 && timestampIndex >= 0) {
                    int steps = cursor.getInt(stepsIndex);
                    float distance = cursor.getFloat(distanceIndex);
                    float calories = cursor.getFloat(caloriesIndex);
                    long timestamp = cursor.getLong(timestampIndex);

                    FitnessData data = new FitnessData(steps, distance, calories, timestamp);
                    fitnessDataList.add(data);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return fitnessDataList;
    }

    // Get fitness data for the specified date range.
    public List<FitnessData> getFitnessDataByDateRange(long startDate, long endDate) {
        List<FitnessData> fitnessDataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_FITNESS_DATA + " WHERE " +
                COLUMN_TIMESTAMP + " >= ? AND " + COLUMN_TIMESTAMP + " <= ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(startDate), String.valueOf(endDate)});

        if (cursor.moveToFirst()) {
            do {
                int stepsIndex = cursor.getColumnIndex(COLUMN_STEPS);
                int distanceIndex = cursor.getColumnIndex(COLUMN_DISTANCE);
                int caloriesIndex = cursor.getColumnIndex(COLUMN_CALORIES);
                int timestampIndex = cursor.getColumnIndex(COLUMN_TIMESTAMP);

                if (stepsIndex >= 0 && distanceIndex >= 0 && caloriesIndex >= 0 && timestampIndex >= 0) {
                    int steps = cursor.getInt(stepsIndex);
                    float distance = cursor.getFloat(distanceIndex);
                    float calories = cursor.getFloat(caloriesIndex);
                    long timestamp = cursor.getLong(timestampIndex);

                    FitnessData data = new FitnessData(steps, distance, calories, timestamp);
                    fitnessDataList.add(data);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return fitnessDataList;
    }

    // To check for any duplicate entries, duplicate returns true. Else returns false
    public boolean isDuplicateEntry(FitnessData data) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_FITNESS_DATA +
                " WHERE " + COLUMN_STEPS + "=? AND " + COLUMN_DISTANCE + "=? AND " + COLUMN_CALORIES + "=? AND " + COLUMN_TIMESTAMP + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{
                String.valueOf(data.getSteps()),
                String.valueOf(data.getDistance()),
                String.valueOf(data.getCalories()),
                String.valueOf(data.getTimestamp())
        });
        boolean isDuplicate = cursor.getCount() > 0;
        cursor.close();
        return isDuplicate;
    }

    // Clear all entries currently stored in the fitness history table
    public void clearFitnessHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FITNESS_DATA, null, null);
        db.close();
    }



}

