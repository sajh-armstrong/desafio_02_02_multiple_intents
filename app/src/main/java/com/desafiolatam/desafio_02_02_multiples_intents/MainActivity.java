package com.desafiolatam.desafio_02_02_multiples_intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button_Main_LoadImage,
            button_Main_Next;
    ImageView imageView_Main_LoadedImage;
    boolean hasLoadedImage = false;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_Main_LoadImage = findViewById(R.id.button_Main_LoadImage);
        button_Main_Next = findViewById(R.id.button_Main_Next);
        imageView_Main_LoadedImage = findViewById(R.id.imageView_Main_LoadedImage);

        button_Main_LoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureIntent();
            }
        });

        button_Main_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hasLoadedImage)
                    Toast.makeText(getApplicationContext(), R.string.warning_hasnot_loaded_image, Toast.LENGTH_LONG).show();
                else{
                    resultIntent();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView_Main_LoadedImage.setImageBitmap(imageBitmap);
            hasLoadedImage = true;
        }
    }

    private void takePictureIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    private void resultIntent(){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("HAS_LOADED_IMAGE", hasLoadedImage);
        intent.putExtra("WEBSITE", getResources().getString(R.string.website));
        startActivity(intent);
        //TODO arreglar website
    }

}
