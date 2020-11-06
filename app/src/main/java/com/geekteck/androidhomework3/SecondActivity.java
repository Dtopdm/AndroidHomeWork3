package com.geekteck.androidhomework3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SecondActivity extends AppCompatActivity {
    private Button button;
    private ImageView imageView;
    private EditText editText;
    public static final int SELECT_IMAGE = 2;
    public String image;
    public String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        clickListener();
    }

    private void clickListener() {
        button.setOnClickListener(v -> {
            text = editText.getText().toString();
            Intent intent2 = getIntent();
            intent2.putExtra("text", text);
            intent2.putExtra("image", image);
            setResult(RESULT_OK, intent2);
            finish();
            Log.d("hey", "get image" + text + image);
        });
        imageView.setOnClickListener(v -> {
            openGallery();
        });
    }

    private void init() {
        button = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView2);
        editText = findViewById(R.id.editText);
    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        setResult(RESULT_OK, intent);
        startActivityForResult(intent, SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            image = data.getDataString();
            Glide.with(this).load(image).placeholder(R.drawable.ic_launcher_background).into(imageView);
            Log.d("hey", image);
        }
    }
}