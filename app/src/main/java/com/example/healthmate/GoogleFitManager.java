package com.example.healthmate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * This class is used to manage Google Fit API integration and related tasks.
 * It provides methods to request permissions, handle activity results, and read fitness data.
 */
public class GoogleFitManager {

    private static final int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1001;
    private final Activity activity;
    private final Context context;

    /**
     * Constructor for the GoogleFitManager class.
     *
     * @param activity the activity that will be used to request permissions
     * @param context  the context of the application
     */
    public GoogleFitManager(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    /**
     * This method requests the necessary Google Fit permissions.
     */
    public void requestGoogleFitPermissions() {
        FitnessOptions fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
                .addDataType(DataType.TYPE_DISTANCE_DELTA)
                .addDataType(DataType.TYPE_CALORIES_EXPENDED)
                .addDataType(DataType.TYPE_HEART_RATE_BPM)
                .build();

        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(context), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    activity,
                    GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
                    GoogleSignIn.getLastSignedInAccount(context),
                    fitnessOptions);
        }
    }

    /**
     * This method handles the activity result for the Google Fit permissions request.
     *
     * @param requestCode the request code of the activity result
     * @param resultCode  the result code of the activity result
     * @param data        the intent data of the activity result
     * @return true if permissions are granted, false otherwise
     */
    public boolean handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Permissions granted, you can access Google Fit API data here.
                return true;
            } else {
                // Permissions denied, handle this case in your app.
                return false;
            }
        }
        return false;
    }

    /**
     * This method retrieves today's step count and passes it to the provided listener.
     *
     * @param listener the listener that will receive the step count data
     */
    public void getTodayStepCount(OnDataPointListener listener) {
        readFitnessData(DataType.TYPE_STEP_COUNT_DELTA, Field.FIELD_STEPS, listener);
    }

    /**
     * This method retrieves today's distance traveled and passes it to the provided listener.
     *
     * @param listener the listener that will receive the distance data
     */
    public void getTodayDistance(OnDataPointListener listener) {
        readFitnessData(DataType.TYPE_DISTANCE_DELTA, Field.FIELD_DISTANCE, listener);
    }

    /**
     * This method retrieves today's calories burned and passes it to the provided listener.
     *
     * @param listener the listener that will receive the calories burned data
     */
    public void getTodayCaloriesBurned(OnDataPointListener listener) {
        readFitnessData(DataType.TYPE_CALORIES_EXPENDED, Field.FIELD_CALORIES, listener);
    }

    /**
     * This method retrieves the latest heart rate data and passes it to the provided listener.
     *
     * @param listener the listener that will receive the heart rate data
     */
    public void getLatestHeartRate(OnDataPointListener listener) {
        readFitnessData(DataType.TYPE_HEART_RATE_BPM, Field.FIELD_BPM, listener);
    }

    /**
     * This method reads fitness data of a specified DataType and Field, and passes the data to the provided listener.
     *
     * @param dataType the DataType of the fitness data to be read
     * @param field    the Field of the fitness data to be read
     * @param listener the listener that will receive the fitness data
     */
    private void readFitnessData(DataType dataType, Field field, OnDataPointListener listener) {
        Fitness.getHistoryClient(context, GoogleSignIn.getLastSignedInAccount(context))
                .readDailyTotal(dataType)
                .addOnSuccessListener(new OnSuccessListener<DataSet>() {
                    @Override
                    public void onSuccess(DataSet dataSet) {
                        if (!dataSet.isEmpty()) {
                            listener.onDataPoint(dataSet.getDataPoints().get(0));
                        } else {
                            // Show a message when no data is available
                            Toast.makeText(context, "No data available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * This interface defines a callback for receiving fitness data points.
     */
    public interface OnDataPointListener {
        void onDataPoint(DataPoint dataPoint);
    }
}

/**
 *
 This updated documentation explains the purpose and functionality of the `GoogleFitManager` class, its methods, and the `OnDataPointListener` interface.
 It describes how the class manages Google Fit API integration, requests permissions, handles activity results, and reads different types of fitness data.
 */

