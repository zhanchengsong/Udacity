package com.udacity.sandwichclub.utils;
import android.util.Log;

import org.json.*;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            //All the fields will be fed to the Sandwich constructor
            String mainName = null;
            List<String> alsoKnownAs = new ArrayList<String>();
            String placeOfOrigin = null;
            String description = null;
            String image = null;
            List<String> ingredients = new ArrayList<String>();

            //Get the name object
            JSONObject sandwich_json = new JSONObject(json);
            JSONObject name_obj = sandwich_json.getJSONObject("name");
            //Assign the mainName
            mainName = name_obj.getString("mainName");
            //Populate the alsoKnowAs array
            JSONArray aka_arr = name_obj.getJSONArray("alsoKnownAs");
            for (int i=0;i<aka_arr.length();i++){
                alsoKnownAs.add(aka_arr.getString(i));
            }
            placeOfOrigin = sandwich_json.getString("placeOfOrigin");
            description = sandwich_json.getString("description");
            image = sandwich_json.getString("image");
            JSONArray ing_arr = sandwich_json.getJSONArray("ingredients");
            for (int j=0; j<ing_arr.length(); j++){
                ingredients.add(ing_arr.getString(j));
            }

            return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);




        } catch(Exception e){
            Log.v("JSON","JSON parsing error");
            e.printStackTrace();
        }

        return null;
    }




}
