package com.example.neredeyesemapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.neredeyesemapp.R;
import com.example.neredeyesemapp.adapters.RecyclerVeiwAdapter;
import com.example.neredeyesemapp.model.Venue;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private final String JSON_URL = "https://developers.zomato.com/api/v2.1/geocode?lat=38.464679&lon=27.115009";
    private JsonObjectRequest request;
    private RequestQueue requestQueue;
    private List<Venue> lstVenue;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstVenue = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
    }


    private void jsonrequest() {
        request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            //@Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                JSONObject jsonObject3 = null;

                try {
                    jsonArray = response.getJSONArray("nearby_restaurants");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0; i<jsonArray.length(); i++){

                    try{
                        jsonObject = jsonArray.getJSONObject(i);
                        jsonObject = jsonObject.getJSONObject("restaurant");
                        jsonObject2 = jsonObject.getJSONObject("user_rating");
                        jsonObject3 = jsonObject.getJSONObject("location");

                        Venue venue = new Venue();
                        venue.setName(jsonObject.getString("name"));
                        venue.setDescription(jsonObject3.getString("address"));
                        venue.setRating(jsonObject2.getString("aggregate_rating"));
                        venue.setCategorie(jsonObject.getString("cuisines"));
                        venue.setNb_episode(5);
                        venue.setStudio(jsonObject2.getString("rating_text"));
                        venue.setImage_url("https://lh3.googleusercontent.com/2Rv9XHqXD2ING24k_wYLyHohfBzkE-JhNrInD6gGw9hFVcmsugMGX9iBUmZZzDqQHjE");
                        lstVenue.add(venue);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(lstVenue);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            //Passing request headers
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Accept","application/json");
                headers.put("user-key","5a99dee458ca8fa92598d5b420957886");
                return headers;
            }
        };
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<Venue> lstVenue) {
        RecyclerVeiwAdapter myadapter = new RecyclerVeiwAdapter(this, lstVenue);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}