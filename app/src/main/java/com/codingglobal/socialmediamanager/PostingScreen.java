package com.codingglobal.socialmediamanager;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PostingScreen extends AppCompatActivity {

    private EditText editText_Post;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_posting_screen);

        Button button_Post = findViewById(R.id.button_post_postingScreen);
        editText_Post = findViewById(R.id.editText_post_postingScreen);


        button_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postToSocialMedia();
            }
        });

    }

    private void postToSocialMedia() {

        String thePost = editText_Post.getText().toString();

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(PostingScreen.this, "post done", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                .setQuote(thePost)
                //.setContentUrl(Uri.parse(thePost))
                .build();

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            shareDialog.show(shareLinkContent);
        }
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
