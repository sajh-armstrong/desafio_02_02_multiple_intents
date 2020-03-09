package com.desafiolatam.desafio_02_02_multiples_intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultActivity extends AppCompatActivity {

    String url;
    boolean hasLoadedImage;

    Button button_Result_Web,
            button_Result_Share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        url = getIntent().getStringExtra("WEBSITE");
        hasLoadedImage = getIntent().getBooleanExtra("HAS_LOADED_IMAGE", false);

        button_Result_Web = findViewById(R.id.button_Result_Web);
        button_Result_Web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                websiteIntent();
            }
        });

        button_Result_Share = findViewById(R.id.button_Result_Share);
        button_Result_Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIntent();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void websiteIntent(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void shareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url + " " + hasLoadedImage);
        startActivity(Intent.createChooser(intent, "COMPÁRTELOOOOOOOO"));
    }
}
