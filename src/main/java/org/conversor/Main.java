package org.conversor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Ventana v = new Ventana();
        v.setVisible(true);

        /*Scanner scanner = new Scanner(System.in);
        MonedaApiClient apiClient = new MonedaApiClient();
        ConvertidorMonedaUtil convertidorMonedaUtil = new ConvertidorMonedaUtil();

        System.out.println("Type corruncy to convert from");
        String convertFrom = scanner.nextLine();
        System.out.println("Type corruncy to convert to");
        String convertTo = scanner.nextLine();
        System.out.println("Type quantity to convert");
        BigDecimal quantity = scanner.nextBigDecimal();

        BigDecimal rate = apiClient.obtenerTipoDeCambio(convertFrom, convertTo);
        BigDecimal result = convertidorMonedaUtil.convertirMoneda(quantity, rate);

        System.out.println("El resultado de la conversión es: " + result);


        String urlString = "https://api.exchangerate.host/latest?base=" + convertFrom.toUpperCase();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String stringResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(stringResponse);
        JSONObject ratesObject = jsonObject.getJSONObject("rates");
        BigDecimal rate = ratesObject.getBigDecimal(convertTo.toUpperCase());

        BigDecimal result = rate.multiply(quantity);
        System.out.println(result);*/

    }
}