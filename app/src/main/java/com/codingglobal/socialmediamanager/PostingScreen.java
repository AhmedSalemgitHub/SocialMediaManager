package com.codingglobal.socialmediamanager;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PostingScreen extends AppCompatActivity {

    private EditText editText_Post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_screen);

        Button button_Post = findViewById(R.id.button_post_postingScreen);
        editText_Post = findViewById(R.id.editText_post_postingScreen);

        button_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBase64Key();
                postToSocialMedia();
            }
        });

    }

    private void postToSocialMedia() {

    }

    private void getBase64Key() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.codingglobal.socialmediamanager"
                    , PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KEY", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
