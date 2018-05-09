package com.example.fepeprog.currencies.APIs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fuel {

    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("regionCode")
    @Expose
    private String regionCode;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

}