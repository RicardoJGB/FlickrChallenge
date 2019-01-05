package com.mobileapps.flickrchallenge;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int SELECT_IMAGE = 1;
    Button buttonSearch;
    EditText editTextSearch;
    ImageView imageViewPic;
    Uri uriImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSearch=findViewById(R.id.btnSearch);
        editTextSearch=findViewById(R.id.etSearch);
        imageViewPic=findViewById(R.id.imageView);

        findViewById(R.id.imageView).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        showImageChooser();
    }

    private void showImageChooser () {

        Intent intentGoToMetadata = new Intent();
        intentGoToMetadata.setType("image/*");
        intentGoToMetadata.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentGoToMetadata,"Select image"), SELECT_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==SELECT_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            uriImage=data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriImage);
                imageViewPic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
