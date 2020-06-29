package com.danilketov.test.network;


import com.danilketov.test.model.Specialty;
import com.danilketov.test.model.Worker;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonParser {

    private static final Gson GSON = new Gson();

    public ArrayList<Worker> getWorkersInfo(String jsonString) {

        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);
        JsonArray responseArray = jsonObject.getAsJsonArray("response");
        Type responseCollectionType = new TypeToken<ArrayList<Worker>>(){}.getType();
        ArrayList<Worker> result = GSON.fromJson(responseArray, responseCollectionType);

        return result;
    }

    public ArrayList<Specialty> getSpecialtyInfo(String jsonString) {

        // Здесь пройти через массив "response"...

        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);
        JsonArray specialtyArray = jsonObject.getAsJsonArray("specialty");
        Type responseCollectionType = new TypeToken<ArrayList<Specialty>>(){}.getType();
        ArrayList<Specialty> result = GSON.fromJson(specialtyArray, responseCollectionType);

        return result;
    }
}
