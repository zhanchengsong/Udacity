package ninja.zhancheng.popularmovie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonArray;

import networkServices.tmdbRestServices;

public class MainActivity extends AppCompatActivity {
    private TextView debug_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        debug_tv = findViewById(R.id.tv_debug);


    }

    public void debugRest(JsonArray json_arr){
        debug_tv.setText(json_arr.toString());
    }

    public class getMovieTask extends AsyncTask<Void,Void,Void> {

        private JsonArray data;
        @Override
        protected Void doInBackground(Void... voids) {
            tmdbRestServices service = tmdbRestServices.getTMDBService();
            data = service.getPopularMovieList();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            debugRest(data);
        }
    }
}
