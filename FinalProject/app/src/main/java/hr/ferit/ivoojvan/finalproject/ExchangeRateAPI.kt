package hr.ferit.ivoojvan.finalproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ExchangeRateAPI {
    @Headers(
        value = [
            "X-RapidAPI-Key: 339901c6d5mshbe2475b342ffb27p1697efjsnafeff8faafb4",
            "X-RapidAPI-Host: currency-converter-by-api-ninjas.p.rapidapi.com"
        ]
    )
    @GET("/v1/convertcurrency?")
    fun getNewCurrency(
        @Query("have") have:String,
        @Query("want") want:String,
        @Query("amount") amount:String
    ): Call<ConvertedCurrency>
}