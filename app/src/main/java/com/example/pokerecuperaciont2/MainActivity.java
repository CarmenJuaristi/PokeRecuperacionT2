package com.example.pokerecuperaciont2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView imagen;
    private ProgressBar progressBar;
    private ConstraintLayout mainLayout;
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.celda);
        recyclerView = findViewById(R.id.recyclerView);
        mainLayout = findViewById(R.id.main_layout);
        progressBar = findViewById(R.id.progress_bar);
        imagen = findViewById(R.id.imagen);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,DetailActivity.class);
                activity.startActivity(intent);
            }
        });


        JSONArrayRequestAuthenticated request = new JSONArrayRequestAuthenticated(
                Request.Method.GET,
                "https://pokeapi.co/api/v2/pokemon",
                null,
                new Response.Listener<JSONArray>() {
                @Override
                public void onResponse (JSONArray response) {
                    progressBar.setVisibility(View.INVISIBLE);
                    List<PokemonData> allThePokemons = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            //Convertimos cada objeto json en un Adaptador
                            JSONObject pokemons = response.getJSONObject(i);
                            PokemonData data = new PokemonData(
                                    pokemons.getString("name"),
                                    pokemons.getString("imagen")
                            );
                            allThePokemons.add(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    //Creamos un adaptador
                    Adaptador adapter = new Adaptador(allThePokemons, activity);
                    // Configuramos el RecyclerView con el adaptador que creamos antes y un LinearLayoutManager
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    Snackbar.make(mainLayout,"List obtained", Snackbar.LENGTH_SHORT).show();

                    }

    },
    new Response.ErrorListener(){
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
}