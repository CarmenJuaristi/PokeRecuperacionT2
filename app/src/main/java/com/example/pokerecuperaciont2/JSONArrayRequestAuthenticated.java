package com.example.pokerecuperaciont2;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class JSONArrayRequestAuthenticated  extends JsonArrayRequest {
    private Context context;

    public JSONArrayRequestAuthenticated (int method, String url, @Nullable JSONArray jsonRequest,
                                          Response.Listener<JSONArray> listener,
                                          @Nullable Response.ErrorListener errorListener,
                                          Context context){
        super(method,url,jsonRequest,listener,errorListener);
        this.context = context;
    }
    //Sobrescribe el método getHeaders para incluir el Token de sesión en los encabezados de la solicitud
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError, NullPointerException{
        SharedPreferences preferences = context. getSharedPreferences("SESSIONS_APP_PREFS", Context.MODE_PRIVATE);
        String sessionToken = preferences.getString("VALID_TOKEN", null);
        // Verifica si el token de sesión está presente
        if (sessionToken == null) {
            throw new AuthFailureError();
        }
        // Crea un mapa de encabezados y agrega el token de sesión
        HashMap<String, String> myHeaders = new HashMap<>();
        myHeaders.put("sessionToken", sessionToken);
        return myHeaders;
    }
}
