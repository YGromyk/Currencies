package com.example.fepeprog.currencies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fepeprog.currencies.APIs.Currency;
import com.example.fepeprog.currencies.APIs.Fuel;
import com.example.fepeprog.currencies.APIs.PrivatBankAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static PrivatBankAPI privatBankAPI;
    private TextView mTextMessage;
    String[] currencies = {"usd_uah", "eur_uah", "rub_uah", "pln_uah" };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(MainActivity.this, "Currencies", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(MainActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(MainActivity.this, "Notifications", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        privatBankAPI = retrofit.create(PrivatBankAPI.class); //Создаем объект, при помощи ко
        final ArrayList<Currency> currencies =  new ArrayList<>();
        getAPI().getCurrency().enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                Toast.makeText(MainActivity.this, "Opa", Toast.LENGTH_SHORT).show();
                currencies.addAll(response.body());

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);

                // use a linear layout manager
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
                mRecyclerView.setLayoutManager(mLayoutManager);
                AdapterCurrency adapterCurrency = new AdapterCurrency(currencies);
                mRecyclerView.setAdapter(adapterCurrency);
                // specify an adapter (see also next example)
            }
            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
            }
        });
        privatBankAPI = retrofit.create(PrivatBankAPI.class);
        String[] fuels = {"A80","A92","A95", "DT"};
        for(String type : fuels)
        getAPI().getFuelPrices("20", type).enqueue(new Callback<List<Fuel>>() {
            @Override
            public void onResponse(Call<List<Fuel>> call, Response<List<Fuel>> response) {
                for(Fuel fuel:response.body())
                    Log.d("FUEL", "onResponse: "+fuel.getType()+"   "+fuel.getPrice());
            }

            @Override
            public void onFailure(Call<List<Fuel>> call, Throwable t) {

            }
        });







    }
    public static PrivatBankAPI getAPI(){
        return privatBankAPI;
    }
}
