package com.example.pokerecuperaciont2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private String nombre1;
    private ConstraintLayout mainLayout;
    private String imagen;
    private ProgressBar progressBar;
    private String nombre2;
    private Activity activity = this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_detail);
        nombre1 = String.valueOf(findViewById(R.id.nameRespuesta));
        nombre2 = String.valueOf(findViewById(R.id.nameType));
        progressBar = findViewById(R.id.progress_bar);
        imagen = String.valueOf(findViewById(R.id.imagen));
        mainLayout = findViewById(R.id.main_layout2);
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
                                        nombre1 = pokemons.getString("name");
                                        nombre2 = pokemons.getString("image_url");
                                        imagen = pokemons.getString("name_type");

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                Snackbar.make(mainLayout,"Datos obtenidos de forma exitosa", Snackbar.LENGTH_SHORT).show();
                                System.out.println(nombre1);
                                System.out.print(nombre2);
                                System.out.print(imagen);

                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Snackbar.make(mainLayout,"Error de conexion", Snackbar.LENGTH_SHORT).show();
                                Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                );
                requestQueue.add(request);
            }
        };
    }
}