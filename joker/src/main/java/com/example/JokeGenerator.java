package com.example;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JokeGenerator {

    private List<String> mJokes;

    /**
     * Constructor
     */
    public JokeGenerator() {
        init();
    }

    /**
     * Initialize a list of Jokes from a json file
     */
    public void init() {

        mJokes = new ArrayList<>();

        StringBuilder sBuilder = new StringBuilder();

        try {

            InputStream in = getClass().getResourceAsStream("/jokes.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String mLine = reader.readLine();
            while (mLine != null) {
                //process line
                sBuilder.append(mLine);
                mLine = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String jsonString = sBuilder.toString();

        if( jsonString != null ){

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();

            JsonArray jokesArray = jsonObject.getAsJsonArray("jokes");

            for (JsonElement element : jokesArray) {
                String joke =  element.getAsJsonObject().get("joke").getAsString();
                mJokes.add(joke);
            }
        }

    }

    /**
     * Returns a Random joke from teh list
     * @return
     */
    public String getJoke() {
        int index = (int)((float)Math.random() * mJokes.size());
        return mJokes.get(index);
    }
}
