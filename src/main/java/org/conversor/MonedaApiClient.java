package org.conversor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;

public class MonedaApiClient {

    public BigDecimal obtenerTipoDeCambio(String fuenteDeMoneda, String monedaDestino){
        try{
            String urlString = "https://api.exchangerate.host/latest?base=" + fuenteDeMoneda.toUpperCase();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlString)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            String stringResponse = response.body().string();
            JSONObject jsonObject = new JSONObject(stringResponse);
            JSONObject ratesObject = jsonObject.getJSONObject("rates");

            return ratesObject.getBigDecimal(monedaDestino.toUpperCase());

        }catch (IOException e){
            e.printStackTrace();
            return BigDecimal.ZERO;
        }

    }


}
