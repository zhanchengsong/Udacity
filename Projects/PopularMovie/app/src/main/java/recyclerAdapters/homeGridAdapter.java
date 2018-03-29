package recyclerAdapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import ninja.zhancheng.popularmovie.R;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by zhancheng-ibm on 3/28/18.
 */
//This is the Adapter for the home popular movie recycler view
public class homeGridAdapter extends RecyclerView.Adapter<homeGridAdapter.ViewHolder> {
    private JsonArray _data;
    private Context _context;
    private LayoutInflater _inflator;
    private String image_basUrl = "image.tmdb.org";
    private HttpUrl.Builder urlBuilder;


    public homeGridAdapter(Context context, JsonArray data) {
        this._data = data;
        this._context = context;
        this._inflator = LayoutInflater.from(this._context);
        this.urlBuilder = new HttpUrl.Builder();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = _inflator.inflate(R.layout.poster_element, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //TODO Get the url fragment from the json and populate use the full url
        //Fetch the object from the JsonArray
        JsonObject movieObj = _data.get(position).getAsJsonObject();
        String shortPosterPath = movieObj.get("poster_path").getAsString().replace("/","");
        Log.i("onBindViewHolder","shortPostPath " + shortPosterPath);
        urlBuilder = new HttpUrl.Builder();
        HttpUrl getImageUrl = urlBuilder.scheme("https")
                                .host(image_basUrl)
                                .addPathSegment("t")
                                .addPathSegment("p")
                                .addPathSegment("w185")
                                .addPathSegment(shortPosterPath)
                                .build();
        Log.i("ImageRequest", getImageUrl.toString() );
        Picasso.with(this._context).load(getImageUrl.toString()).into(holder.posterIV);
        shortPosterPath="";
    }

    @Override
    public int getItemCount() {
        Log.i("GridAdapter","Item counted: " + _data.size());
        return _data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView posterIV;
        //Constructor for the ViewHolder
        ViewHolder (View gridView){
            super(gridView);
            posterIV = (ImageView) gridView.findViewById(R.id.posterElement);
            gridView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
