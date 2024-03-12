package com.example.pokerecuperaciont2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private TextView nombre1;
    private ImageView imagen;
    private TextView nombre2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_detail);
        new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArrayRequestAuthenticated request = new JSONArrayRequestAuthenticated(
                        Request.Method.GET,
                        "https://pokeapi.co/api/v2/pokemon",
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        //Convertimos cada objeto json en un Adaptador
                                        JSONObject pokemons = response.getJSONObject(i);
                                        System.out.println(pokemons.getString("name"));
                                        System.out.println(pokemons.getString("image_url"));
                                        System.out.println(pokemons.getString("name_type"));

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println();
                            }

                        }
                );
                requestQueue.add(request);
            }
        }
    }
}