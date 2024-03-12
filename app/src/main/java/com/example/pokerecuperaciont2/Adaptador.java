package com.example.pokerecuperaciont2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter <Celda> {
   private List<PokemonData> allTheData;
   private Activity activity;

   public Adaptador(List<PokemonData> dataSet, Activity activity){
       this.allTheData = dataSet;
       this.activity = activity;
   }
    @NonNull
    @Override
    public Celda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda,parent,false);
        return new Celda(view);
   }

    @Override
    public void onBindViewHolder(@NonNull Celda holder, int position) {
        PokemonData dataInPositionToBeRendered = allTheData.get(position);
        holder.showData(dataInPositionToBeRendered,activity);
    }

    @Override
    public int getItemCount() {
        return allTheData.size();
    }
}
