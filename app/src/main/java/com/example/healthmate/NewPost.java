package com.example.healthmate;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * NewPost activity allows users to create and add new posts to the Social activity.
 */
public class NewPost extends AppCompatActivity {
    Button SelectImage;
    int defaultimage = R.drawable.stock1;
    int image=0;
    private static final int REQUEST_GALLERY = 1001;
    private final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;
    int[] images = {R.drawable.stock1, R.drawable.stock2, R.drawable.stock3, R.drawable.stock4, R.drawable.stock5, R.drawable.stock6, R.drawable.stock7};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);
//        imageView = findViewById(R.id.imageView);

        // Call openCamera() when the user clicks on a button to open the camera
//        findViewById(R.id.button).setOnClickListener(view -> openCamera());
        // Initialize buttons
        Button cancel = findViewById(R.id.cancel_button);
        Button add = findViewById(R.id.add_meal);
        Button select = findViewById(R.id.select_image);

        // Set onClickListener for the add button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input values from the EditText fields
                EditText runName = findViewById(R.id.editRunName);
                EditText runDistance = findViewById(R.id.editRunDistance);
                EditText runTime = findViewById(R.id.editRunTime);

                // Convert EditText values to strings
                String name = runName.getText().toString();
                String distance = runDistance.getText().toString();
                String time = runTime.getText().toString();

                // Check if the input fields are not empty
                if (!name.isEmpty() && !distance.isEmpty() && !time.isEmpty()) {
                    // Set up the post with the input values
                    if (getImageBitmap() != null) {
                        setUpPost(name, distance, time, getImageBitmap());
                    } else {
                        setUpPost(name, distance, time, defaultimage);
                    }

                    // Redirect to the Social activity
                    startActivity(new Intent(NewPost.this, Social.class));
                }
            }
        });

        // Set onClickListener for the cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the Social activity
                startActivity(new Intent(NewPost.this, Social.class));
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new AlertDialog to display the options to the user
                AlertDialog.Builder builder = new AlertDialog.Builder(NewPost.this);
                builder.setTitle("Select Image");
                builder.setItems(new CharSequence[]{"Gallery", "Camera"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                // User selected "Gallery"
                                openGallery();
                                break;
                            case 1:
                                // User selected "Camera"
                                openCamera();
                                break;
                        }
                    }
                });
                builder.show();
            }
        });

    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_GALLERY);
    }

    // Call this method to open the camera
    private void openCamera() {
//        comment
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // Handle the result of the camera intent
    @Override// Handle the result of the camera intent
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            if (imageBitmap==null){
                Log.d("Get image from camera","Returns null");
            }
        } else if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Call this method to get the stored image
    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    // Set onClickListener for the select image button
    /**
     * Sets up the post using the input values and stores it in the Social_PostModelHolder.
     *
     * @param name     The name of the run.
     * @param distance The distance of the run.
     * @param time     The time of the run.
     */
    private void setUpPost(String name, String distance, String time,int image) {
        String userName = UserDataSingleton.getInstance().getUserData().getUserName();

        Social_PostModelHolder.getInstance().storePost(new Social_PostModel(userName, name, distance, time, "0",image ));
    }
    private void setUpPost(String name, String distance, String time,Bitmap customImage) {
        String userName = UserDataSingleton.getInstance().getUserData().getUserName();
        Social_PostModelHolder.getInstance().storePost(new Social_PostModel(userName, name, distance, time, "0",customImage ));
    }
}
