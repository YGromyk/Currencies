package com.example.fepeprog.currencies.APIs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PrivatBankAPI {
    @GET("/p24api/pubinfo?exchange&json&coursid=11")
    Call<List<Currency>> getCurrency();
}
