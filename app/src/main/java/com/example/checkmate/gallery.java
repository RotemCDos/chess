package com.example.checkmate;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

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
//                ivPicture.setImageDrawable(null);
//                ivPicGal.setImageDrawable(null);
                openCamera();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCamera(false);
//                ivPicture.setImageDrawable(null);
//                ivPicGal.setImageDrawable(null);
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
//            else{
//                if(camera && resultCode == RESULT_CANCELED)
//                    ivPicture.setImageBitmap(null);
//            }
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

    /**
     * A copy of the Android internals  insertImage method, this method populates the
     * meta data with DATE_ADDED and DATE_TAKEN. This fixes a common problem where media
     * that is inserted manually gets saved at the end of the gallery (because date is not populated).
     * @see android.provider.MediaStore.Images.Media#insertImage(ContentResolver, Bitmap, String, String)
     */
//    public static final String insertImage(ContentResolver cr,
//                                           Bitmap source,
//                                           String title,
//                                           String description) {
//
//        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.TITLE, title);
//        values.put(MediaStore.Images.Media.DISPLAY_NAME, title);
//        values.put(MediaStore.Images.Media.DESCRIPTION, description);
//        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//        // Add the date meta data to ensure the image is added at the front of the gallery
//        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
//        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
//
//        Uri url = null;
//        String stringUrl = null;    /* value to be returned */
//
//        try {
//            url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//
//            if (source != null) {
//                OutputStream imageOut = cr.openOutputStream(url);
//                try {
//                    source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
//                } finally {
//                    imageOut.close();
//                }
//
//                long id = ContentUris.parseId(url);
//                // Wait until MINI_KIND thumbnail is generated.
//                Bitmap miniThumb = MediaStore.Images.Thumbnails.getThumbnail(cr, id, MediaStore.Images.Thumbnails.MINI_KIND, null);
//                // This is for backward compatibility.
//                storeThumbnail(cr, miniThumb, id, 50F, 50F, MediaStore.Images.Thumbnails.MICRO_KIND);
//            } else {
//                cr.delete(url, null, null);
//                url = null;
//            }
//        } catch (Exception e) {
//            if (url != null) {
//                cr.delete(url, null, null);
//                url = null;
//            }
//        }
//
//        if (url != null) {
//            stringUrl = url.toString();
//        }
//
//        return stringUrl;
//    }

    /**
     * A copy of the Android internals StoreThumbnail method, it used with the insertImage to
     * populate the android.provider.MediaStore.Images.Media#insertImage with all the correct
     * meta data. The StoreThumbnail method is private so it must be duplicated here.
     * @see android.provider.MediaStore.Images.Media (StoreThumbnail private method)
     */
//    private static final Bitmap storeThumbnail(
//            ContentResolver cr,
//            Bitmap source,
//            long id,
//            float width,
//            float height,
//            int kind) {
//
//        // create the matrix to scale it
//        Matrix matrix = new Matrix();
//
//        float scaleX = width / source.getWidth();
//        float scaleY = height / source.getHeight();
//
//        matrix.setScale(scaleX, scaleY);
//
//        Bitmap thumb = Bitmap.createBitmap(source, 0, 0,
//                source.getWidth(),
//                source.getHeight(), matrix,
//                true
//        );
//
//        ContentValues values = new ContentValues(4);
//        values.put(MediaStore.Images.Thumbnails.KIND,kind);
//        values.put(MediaStore.Images.Thumbnails.IMAGE_ID,(int)id);
//        values.put(MediaStore.Images.Thumbnails.HEIGHT,thumb.getHeight());
//        values.put(MediaStore.Images.Thumbnails.WIDTH,thumb.getWidth());
//
//        Uri url = cr.insert(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, values);
//
//        try {
//            OutputStream thumbOut = cr.openOutputStream(url);
//            thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut);
//            thumbOut.close();
//            return thumb;
//        } catch (FileNotFoundException ex) {
//            return null;
//        } catch (IOException ex) {
//            return null;
//        }
//    }

}
