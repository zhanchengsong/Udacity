package networkServices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhancheng-ibm on 3/27/18.
 */

public class tmdbRestServices {
    private String baseUrl;
    private String apiKey;
    private Gson _gson;
    private HttpUrl.Builder httpbuilder;
    private static tmdbRestServices _service;
    private OkHttpClient client;
    private JsonArray popularMovieListJson;
    //Private constructor for Singleton
    private tmdbRestServices(){
        this.baseUrl = "api.themoviedb.org";
        //TODO: Remove API key before commit/ push
        this.apiKey = "8350d10bc2933344952c5f211b409d2a";
        this._gson = new Gson();
        this.httpbuilder = new HttpUrl.Builder();
        this.client = new OkHttpClient();
        this.popularMovieListJson = null;
    }

    //Singleton factory method
    public static tmdbRestServices getTMDBService (){
        if (_service ==null){ _service = new tmdbRestServices(); }
        return _service;
    }
    //Rest call using HttpClient
    public JsonArray getPopularMovieList() throws Exception{

        if (this.popularMovieListJson!=null ) {
            return popularMovieListJson;
        }
        HttpUrl getPopularMovieListURL =
                httpbuilder.scheme("https")
                .host(baseUrl)
                        .addPathSegment("3")
                        .addPathSegment("discover")
                        .addPathSegment("movie")
                .addQueryParameter("sort_by","popularity.desc")
                .addQueryParameter("api_key",apiKey)
                        .addQueryParameter("include_adult", "false")
                        .addQueryParameter("include_video", "false")
                        .addQueryParameter("page","1")
                        .addQueryParameter("language", "en-US")
                        .build();


        Request popularmovieListRequest = new Request.Builder()
                                            .url(getPopularMovieListURL)
                                            .build();
        Response popularmovieListResponse = client.newCall(popularmovieListRequest).execute();
        String res_body = popularmovieListResponse.body().string();
        Log.i("REST Service", "GET " + getPopularMovieListURL.toString()
                                            + "\n" + "Response " + popularmovieListResponse.toString()
                                            + "\n" + "Body " + res_body);

        if (res_body!=null) {
            this.popularMovieListJson = _gson.fromJson(res_body,JsonObject.class).getAsJsonArray("results");

        }
        return popularMovieListJson;
    }

    public List<Bitmap> getPopularPosters() throws Exception {
        JsonArray internal_json = this.getPopularMovieList();
        Log.i ("getPopularPosters", "Processiong JSON: " + internal_json);
        List<Bitmap> posters = new ArrayList<>();
        for (int i=0; i<internal_json.size(); i++){
            URL photo_url = new URL("http://image.tmdb.org/t/p/w185/" + internal_json.get(i).getAsJsonObject().get("poster_path").getAsString());
            Bitmap bitmap = BitmapFactory.decodeStream(  (InputStream) photo_url.getContent() );
            posters.add(bitmap);
        }

        return posters;
    }


}
