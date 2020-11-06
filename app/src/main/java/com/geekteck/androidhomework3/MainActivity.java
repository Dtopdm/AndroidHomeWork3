package com.geekteck.androidhomework3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;
    private ImageView imageView;
    private TextView textView;
    String text;
    String img;
    public static final int REQUEST_CODE = 1;
    public static final String KEY_INTENT = "key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        clickListener();
    }

    @SuppressLint("IntentReset")
    private void clickListener() {
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(KEY_INTENT, "edit text");
            startActivityForResult(intent, REQUEST_CODE);
        });
        button.setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SENDTO);
            email.setType("text/plain");
            email.setData(Uri.parse("mailto:your.eamil@gmail.com"));
            email.putExtra(Intent.EXTRA_SUBJECT, text);
            email.putExtra(Intent.EXTRA_TEXT, text +" from user");
            startActivity(email);
        });
    }

    private void init() {
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            text = data.getStringExtra("text");
            img = data.getStringExtra("image");
            textView.setText(text);
            Glide.with(this).load(img).into(imageView);
            Toast.makeText(this, "Successful!", Toast.LENGTH_LONG).show();
        }
    }
}