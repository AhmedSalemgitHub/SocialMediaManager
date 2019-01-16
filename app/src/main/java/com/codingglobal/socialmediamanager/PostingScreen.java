package com.codingglobal.socialmediamanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostingScreen extends AppCompatActivity {

    private Button button_Post;
    private EditText editText_Post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_screen);

        button_Post = findViewById(R.id.button_post_postingScreen);
        editText_Post = findViewById(R.id.editText_post_postingScreen);

        button_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostingToSocialMedia();
            }
        });

    }

    private void PostingToSocialMedia() {

    }
}
