package com.example.fepeprog.currencies.APIs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PrivatBankAPI {
    @GET("/p24api/pubinfo?exchange&json&coursid=11")
    Call<List<Currency>> getCurrency();

    @GET("/p24api/aviasstations?json&price&")
    Call<List<Fuel>> getFuelPrices(@Query("region") String region, @Query("type") String type);

}
