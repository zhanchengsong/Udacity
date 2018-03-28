package ninja.zhancheng.popularmovie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.JsonArray;

import networkServices.tmdbRestServices;
import recyclerAdapters.homeGridAdapter;

public class MainActivity extends AppCompatActivity {
    private TextView debug_tv;
    private JsonArray bootstrap_data;
    //Adapter
    homeGridAdapter homeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Call to populate the boostrap data
        new getMovieTask().execute();


    }



    public void populateView(){
        //Get the view and set to a grid view
        RecyclerView recyclerView = findViewById(R.id.posterGrid);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        //Use the data to populate the adapter
        homeAdapter = new homeGridAdapter(this, bootstrap_data);
        recyclerView.setAdapter(homeAdapter);
    }



    // Task to retrive information from the tmdb rest services
    public class getMovieTask extends AsyncTask<Void,Void,Void> {

        private JsonArray data;
        @Override
        protected Void doInBackground(Void... voids) {
            tmdbRestServices service = tmdbRestServices.getTMDBService();
            try {
                data = service.getPopularMovieList();
                bootstrap_data = data;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            populateView();

        }
    }


}
