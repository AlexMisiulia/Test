package com.danilketov.test.network;


import com.danilketov.test.model.Specialty;
import com.danilketov.test.model.Worker;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

        ArrayList<Worker> workersInfo = getWorkersInfo(jsonString);

        ArrayList<Specialty> specialties = new ArrayList<Specialty>();
        for(Worker workerInfo : workersInfo) {
            specialties.addAll(workerInfo.getSpecialty());
        }

        return specialties;
    }
}
