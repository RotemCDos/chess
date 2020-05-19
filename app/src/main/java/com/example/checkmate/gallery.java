package com.example.checkmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class gallery extends AppCompatActivity {
    ImageView ivPicture;
    ImageView ivPicGal;
    Button btnCamera;
    Button btnGallery;
    ImageButton btnBack;
    SharedPreferences myPreference;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    boolean camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnGallery = (Button) findViewById(R.id.btnGallery);
        ivPicture = (ImageView) findViewById(R.id.ivPicture);
        ivPicGal = (ImageView) findViewById(R.id.ivPicGal);

        myPreference = getPreferences(MODE_PRIVATE);
        Bitmap bitmap = decodeToBase64(myPreference.getString("imagePreferance", ""));
        ivPicture.setImageBitmap(bitmap);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCamera(true);
                openCamera();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCamera(false);
                openGallery();
            }
        });

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setCamera(boolean set)
    {
        camera=set;
    }

    private void openCamera()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    private void openGallery()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SharedPreferences.Editor editor = myPreference.edit();

        if(resultCode == RESULT_OK && requestCode==PICK_IMAGE)
        {
            imageUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                editor.putString("namePreferance", "imageView");
                editor.putString("imagePreferance", encodeToBase64(bitmap));
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ivPicGal.setImageURI(null);
            ivPicGal.setImageURI(imageUri);
        }
        else {
            if (camera && resultCode == RESULT_OK) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                editor.putString("namePreferance", "imageView");
                editor.putString("imagePreferance", encodeToBase64(bitmap));
                editor.commit();
                ivPicture.setImageBitmap(bitmap);
            }
        }
    }

    public static String encodeToBase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    public static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
