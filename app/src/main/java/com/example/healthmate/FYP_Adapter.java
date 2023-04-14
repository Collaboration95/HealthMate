package com.example.healthmate;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FYP_Adapter is the adapter for the RecyclerView in the social section of the app.
 * It displays social posts in a list format.
 */
public class FYP_Adapter extends RecyclerView.Adapter<FYP_Adapter.myViewHolder> {
    Context context;
    ArrayList<Social_PostModel> data;

    /**
     * Constructor that takes in the context and social post data.
     *
     * @param context the application context
     * @param data    an ArrayList of Social_PostModel objects
     */
    public FYP_Adapter(Context context, ArrayList<Social_PostModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FYP_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the RecyclerView element layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fyp_recyclerelement, parent, false);

        // Return a new ViewHolder for the layout
        return new FYP_Adapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FYP_Adapter.myViewHolder holder, int position) {
        // Set the text for the TextViews
        holder.Name.setText(data.get(position).getName());
        holder.activityName.setText(data.get(position).getActivityName());
        holder.Distance.setText(data.get(position).getDistance());
        holder.Time.setText(data.get(position).getTime());
        holder.likes.setText(data.get(position).getLikes());

        // Set the image resources for the ImageViews
        int profileIcon = R.drawable.ic_launcher_person;
        int activityIcon = R.drawable.ic_launcher_rightsymbol;
        int likeIcon = R.drawable.ic_launcher_thumbsup;
        int timeIcon = R.drawable.ic_launcher_timer;
        int distanceIcon = R.drawable.ic_launcher_distance;
        int shareicon = R.drawable.ic_launcher_share;

        holder.AddFriendicon.setImageResource(profileIcon);
        holder.activityIcon.setImageResource(activityIcon);
        holder.likeIcon.setImageResource(likeIcon);
        holder.timeIcon.setImageResource(timeIcon);
        holder.distanceIcon.setImageResource(distanceIcon);
        holder.shareIcon.setImageResource(shareicon);
            if(UserDataSingleton.getInstance().getUserData().getUserName().equals(data.get(holder.getAdapterPosition()).getName())) {
                holder.shareIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Log.d("Something is not working","why ");
                        shareToSocialMedia(holder.getAdapterPosition());
                    }

                });

            }

        // Get the image resource ID and calculate its aspect ratio
        int imageResId = data.get(position).getImage();
        Bitmap imageBitmap = data.get(position).getCustomImage();
        if (imageBitmap == null) {
            // Load the default image Bitmap
            imageBitmap = BitmapFactory.decodeResource(context.getResources(), imageResId);
        }

        // Set the ImageView's ScaleType based on the aspect ratio of the imageBitmap
        float aspectRatio = (float) imageBitmap.getWidth() / (float) imageBitmap.getHeight();
        if (aspectRatio > 1.0) {
            holder.Image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            holder.Image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

    // Scale the imageBitmap to a larger size
        int newWidth = 800; // adjust as needed
        int newHeight = (int) (newWidth / aspectRatio);
        imageBitmap = Bitmap.createScaledBitmap(imageBitmap, newWidth, newHeight, true);

    // Set the image Bitmap for the ImageView
        holder.Image.setImageBitmap(imageBitmap);

    }

    // Return the number of social posts in the data list
    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class myViewHolder extends RecyclerView.ViewHolder {
        // Define TextViews and ImageViews for each RecyclerView element
        TextView Name;
        TextView activityName;
        TextView Distance;
        TextView Time;
        TextView likes;
        ImageView Image;
        ImageView AddFriendicon;
        ImageView activityIcon;
        ImageView likeIcon;
        ImageView timeIcon;
        ImageView distanceIcon;
        ImageView shareIcon;

        /**
         * Constructor that takes in a view for the RecyclerView element.
         *
         * @param itemView the view representing a single RecyclerView element
         */
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.MainImage);
            Name = itemView.findViewById(R.id.Name);
            activityName = itemView.findViewById(R.id.activityName);
            Distance = itemView.findViewById(R.id.Distance);
            Time = itemView.findViewById(R.id.Time);
            likes = itemView.findViewById(R.id.Likes);
            AddFriendicon = itemView.findViewById(R.id.addFriend);
            activityIcon = itemView.findViewById(R.id.iconArrow);
            likeIcon = itemView.findViewById(R.id.iconLike);
            timeIcon = itemView.findViewById(R.id.iconTime);
            distanceIcon = itemView.findViewById(R.id.iconDistance);
            shareIcon = itemView.findViewById(R.id.share);
        }
    }

    // This method shares a post from the given position in the list to Instagram
    private void shareToSocialMedia(int position) {
        // Get the post from the given position
        Social_PostModel post = Social_PostModelHolder.getInstance().getPost(position);
        // Get the activity name from the post
        String postName = post.getActivityName();
        // Get the custom image if available
        Bitmap bitmap = post.getCustomImage();

        // If custom image is not available, load the default image Bitmap
        if (bitmap== null) {
         bitmap = BitmapFactory.decodeResource(context.getResources(),post.getImage());
        }

        // Get the image Uri from the Bitmap
        Uri imageUri = getImageUriFromBitmap(bitmap);

        // Create a new intent for sharing
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, postName);
        shareIntent.setPackage("com.instagram.android");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            // Start the Instagram sharing activity
            context.startActivity(shareIntent);
            // Copy the post caption to the clipboard
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Caption", postName);
            clipboard.setPrimaryClip(clip);
            // Show a message to the user
            Toast.makeText(context, "Caption copied to clipboard. Paste it in the Instagram post.", Toast.LENGTH_SHORT).show();
        } catch (ActivityNotFoundException e) {
            // Show an error message if Instagram is not installed
            Toast.makeText(context, "Instagram is not installed on this device", Toast.LENGTH_SHORT).show();
        }
    }

    // This method converts a Bitmap into an Uri for sharing
    private Uri getImageUriFromBitmap(Bitmap bitmap) {
        // Compress the Bitmap into a ByteArrayOutputStream
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        // Insert the compressed Bitmap into the MediaStore and get the Uri
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        // Return the Uri
        return Uri.parse(path);
    }
}
