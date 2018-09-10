package com.example.android.cityapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class havadurumuActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutŞehirSeç, textInputLayoutŞehir, textInputLayoutSıcaklık, textInputLayoutHavaDurumu, textInputLayoutAçıklama;

    private AppCompatTextView editTextŞehir, editTextSıcaklık, editTextHavaDurumu, editTextAçıklama;

    private TextInputEditText textInputEditTextŞehirSeç;

    private AppCompatButton appCompatButtonHavaDurumu;

    private AppCompatImageView appCompatImageVieweather;

    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_havadurumu);

        textInputLayoutŞehirSeç = (TextInputLayout) findViewById(R.id.textInputLayoutŞehirSeç);
        textInputLayoutŞehir = (TextInputLayout) findViewById(R.id.textInputLayoutŞehir);
        textInputLayoutSıcaklık = (TextInputLayout) findViewById(R.id.textInputLayoutSıcaklık);
        textInputLayoutHavaDurumu = (TextInputLayout) findViewById(R.id.textInputLayoutHavaDurumu);
        textInputLayoutAçıklama = (TextInputLayout) findViewById(R.id.textInputLayoutAçıklama);
        textInputEditTextŞehirSeç = (TextInputEditText) findViewById(R.id.editTextŞehirSeç);

        editTextŞehir = (AppCompatTextView) findViewById(R.id.editTextŞehir);
        editTextSıcaklık = (AppCompatTextView) findViewById(R.id.editTextSıcaklık);
        editTextHavaDurumu = (AppCompatTextView) findViewById(R.id.editTextHavaDurumu);
        editTextAçıklama = (AppCompatTextView) findViewById(R.id.editTextAçıklama);

        appCompatImageVieweather = (AppCompatImageView) findViewById(R.id.weatherImage);

        appCompatButtonHavaDurumu = (AppCompatButton) findViewById(R.id.appCompatButtonHavaDurumu);

        appCompatButtonHavaDurumu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonParseWeather jsonParseWeather = new JsonParseWeather();
                city = String.valueOf(textInputEditTextŞehirSeç.getText());
                new JsonParseWeather().execute();
            }
        });

    }

    protected class JsonParseWeather extends AsyncTask<Void, Void, Void> {
        String result_main = "";
        String result_description = "";
        String result_icon = "";
        int result_temp;
        String result_city;
        Bitmap bitImage;

        @Override
        protected Void doInBackground(Void... voids) {

            String result = "";
            try {
                URL weather_url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=5519df78a91952f50079565124888a76");
//HttpURLConnection weather_url_con = (HttpURLConnection) weather_url.openConnection();
//InputStreamReader inputStreamReader = new InputStreamReader(weather_url_con.getInputStream());
                BufferedReader bufferedReader = null;
                bufferedReader = new BufferedReader(new InputStreamReader(weather_url.openStream()));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("weather");
                JSONObject jsonObject_weather = jsonArray.getJSONObject(0);
                result_main = jsonObject_weather.getString("main");
                result_description = jsonObject_weather.getString("description");
                result_icon = jsonObject_weather.getString("icon");

                JSONObject jsonObject_main = jsonObject.getJSONObject("main");
                Double temp = jsonObject_main.getDouble("temp");

                result_city = jsonObject.getString("name");

                result_temp = (int) (temp - 273);

                URL icon_url = new URL("http://openweathermap.org/img/w/" + result_icon + ".png");
                bitImage = BitmapFactory.decodeStream(icon_url.openConnection().getInputStream());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            editTextSıcaklık.setText(String.valueOf(result_temp));
            editTextHavaDurumu.setText(result_main);
            editTextŞehir.setText(result_city);
            editTextAçıklama.setText(result_description);
            appCompatImageVieweather.setImageBitmap(bitImage);
            super.onPostExecute(aVoid);
        }
    }
}
