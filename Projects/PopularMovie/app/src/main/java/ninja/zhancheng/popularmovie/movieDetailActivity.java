package ninja.zhancheng.popularmovie;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

public class movieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Gson gson = new Gson();
        Intent fromHomePage = getIntent();
        JsonObject detailJson = gson.fromJson(fromHomePage.getStringExtra("detailJson"),JsonObject.class);
        this.setTitle(detailJson.get("title").getAsString());
        String picturePath = detailJson.get("poster_path").getAsString();
        Log.i("Detail Picasso", "Loading " + "http://image.tmdb.org/t/p/w185" + picturePath);
        Picasso.with(movieDetailActivity.this)
                .load("http://image.tmdb.org/t/p/w500" + picturePath)
                .into((ImageView)findViewById(R.id.iv_detailImage));
    }
}
