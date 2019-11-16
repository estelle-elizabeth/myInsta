package com.example.myinsta;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText postText;
    Button photoButton;
    ImageView image;
    Button submitButton;
    static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postText = findViewById(R.id.postText);
        photoButton = findViewById(R.id.photoButton);
        image = findViewById(R.id.postImage);
        submitButton = findViewById(R.id.submitButton);

        queryPost();
    }

    private void queryPost() {
        // Specify which class to query
                ParseQuery<Post> query = new ParseQuery<Post>(Post.class);
                query.include(Post.KEY_USER);

                query.findInBackground(new FindCallback<Post>() {
                    @Override
                    public void done(List<Post> posts, ParseException e) {
                        if (e != null){
                            Log.e(TAG, "Issue with post query");
                            e.printStackTrace();
                            return;
                        }
                    }
                });
    }
}
