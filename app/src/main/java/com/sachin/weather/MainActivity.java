package com.sachin.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtTemp, txtPress, txtHumidity, txtFeels, txtCity, txtMain;
    private EditText edtCity;
    private Button btnSearch;

    ApiCall apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTemp = findViewById(R.id.temperature);
        txtPress = findViewById(R.id.txtPressure);
        txtCity = findViewById(R.id.cityName);
        txtFeels = findViewById(R.id.txtFeels);
        txtHumidity = findViewById(R.id.txtHumidity);
        //txtMain = findViewById(R.id.txtMain);
        edtCity = findViewById(R.id.edtCity);
        btnSearch = findViewById(R.id.btnSearch);


        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ServerInfo.Base_url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        apiCall = retrofit.create(ApiCall.class);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtCity.setText(edtCity.getText().toString());

                if(edtCity.getText().toString().equals("")){
                    edtCity.setError("Please enter city name");
                    edtCity.requestFocus();
                    return;
                }

                 Call<WeatherInfo> call = apiCall.getWeatherData(edtCity.getText().toString(), ServerInfo.API_key);
                 call.enqueue(new Callback<WeatherInfo>() {
                     @Override
                     public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                         //we will get response from the server

                         WeatherInfo weatherInfo = response.body();

                         txtTemp.setText((weatherInfo.getMainData().getTemperature()-273.15) +" "+ (char) 0x00B0+"C");
                         txtPress.setText(weatherInfo.getMainData().getPressure() + " hPa");
                         txtHumidity.setText(String.format("%1$d%%", weatherInfo.getMainData().getHumidity()));
                         txtFeels.setText((weatherInfo.getMainData().getFeelsLike()-273.15)+" "+ (char) 0x00B0+ "C");


                     }

                     @Override
                     public void onFailure(Call<WeatherInfo> call, Throwable t) {
                         Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                         
                     }
                 });


            }
        });
    }
}