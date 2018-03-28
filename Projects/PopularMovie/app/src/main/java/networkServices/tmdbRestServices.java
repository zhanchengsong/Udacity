package networkServices;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by zhancheng-ibm on 3/27/18.
 */

public class tmdbRestServices {
    private String baseUrl;
    private String apiKey;
    private Gson _gson;

    private static tmdbRestServices _service;

    //Private constructor for Singleton
    private tmdbRestServices(){
        this.baseUrl = "https://api.themoviedb.org/3";
        //TODO: Remove API key before commit/ push
        this.apiKey = "8350d10bc2933344952c5f211b409d2a";
        this._gson = new Gson();

    }

    //Singleton factory method
    public static tmdbRestServices getTMDBService (){
        if (_service ==null){ _service = new tmdbRestServices(); }
        return _service;
    }
    //Rest call using HttpClient
    public JsonArray getPopularMovieList(){
        JsonArray resultMovieList = null;
        String popularMovieUrl =


        return resultMovieList;
    }
}
