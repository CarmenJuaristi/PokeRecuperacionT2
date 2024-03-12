package com.example.pokerecuperaciont2;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class Celda extends RecyclerView.ViewHolder {
    private TextView textView;

    private ImageView imageView;

    public Celda(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.nombre);
        imageView = itemView.findViewById(R.id.imagen);
    }
    public void showData(PokemonData data, Activity activity){
        textView.setText(data.getName());
        Glide.with(itemView.getContext())
                .load(data.getPokemon())
                .into(imageView);
    }
}
