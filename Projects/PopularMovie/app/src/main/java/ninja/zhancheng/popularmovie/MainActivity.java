package ninja.zhancheng.popularmovie;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;

import java.util.List;

import ViewAdapters.ImageAdapter;
import networkServices.tmdbRestServices;

public class MainActivity extends AppCompatActivity {
    private TextView debug_tv;
    private JsonArray bootstrap_data;
    //Adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Call to populate the boostrap data
        new getMovieTask().execute();


    }



    public void populateView(List<Bitmap> photos){


        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(bootstrap_data, this, photos));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                tmdbRestServices services = tmdbRestServices.getTMDBService();
                try {
                String movie_name = services.getMovieDetailBySequence(position).get("title").getAsString();
                Toast.makeText(MainActivity.this, "" + movie_name ,
                        Toast.LENGTH_SHORT).show();}
                        catch(Exception e) {
                    Toast.makeText(MainActivity.this, "Error occured" , Toast.LENGTH_LONG).show();
                        }
            }
        });

    }



    // Task to retrive information from the tmdb rest services
    public class getMovieTask extends AsyncTask<Void,Void,List<Bitmap>> {

        private JsonArray data;
        @Override
        protected List<Bitmap> doInBackground(Void... voids) {
            tmdbRestServices service = tmdbRestServices.getTMDBService();
            List<Bitmap> photos = null;
            try {
                data = service.getPopularMovieList();
                bootstrap_data = data;
                photos = service.getPopularPosters();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return photos;
        }

        @Override
        protected void onPostExecute(List<Bitmap> photos) {
            super.onPostExecute(photos);

            populateView(photos);

        }
    }



}
