package com.example.pokerecuperaciont2;

import org.json.JSONException;
import org.json.JSONObject;

public class PokemonData {
    private String name;
    private String pokemon;
    public String getName(){return name;}

    public String getPokemon() {
        return pokemon;
    }

    public PokemonData(String name, String pokemon){
        this.name = name;
        this.pokemon = pokemon;
    }
    public PokemonData (JSONObject json){
        try{
            this.name = json.getString("name");
            this.pokemon = json.getString("pokemon");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
