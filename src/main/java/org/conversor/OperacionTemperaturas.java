package org.conversor;

import java.util.HashMap;
import java.util.Map;

public class OperacionTemperaturas {
    private static final Map<String, Integer> OPERACIONES_TEMPERATURA = new HashMap<>();

    static {
        OPERACIONES_TEMPERATURA.put("C-F", 1);
        OPERACIONES_TEMPERATURA.put("F-C", 2);
        OPERACIONES_TEMPERATURA.put("K-C", 3);
        OPERACIONES_TEMPERATURA.put("C-K", 4);
        OPERACIONES_TEMPERATURA.put("F-K", 5);
        OPERACIONES_TEMPERATURA.put("K-F", 6);
    }

    private int asignacionDeOperacionTemperatura(String temperatura1, String temperatura2) {
        String key = temperatura1.toUpperCase() + "-" + temperatura2.toUpperCase();
        Integer operacion = OPERACIONES_TEMPERATURA.get(key);
        if (operacion != null) {
            return operacion;
        } else {
            throw new IllegalArgumentException("Unidades de temperatura no válidas.");
        }
    }

    public double convertirTemperatura(String temperatura1, String temperatura2, double temperatura) {
        int operacion = asignacionDeOperacionTemperatura(temperatura1, temperatura2);

        switch (operacion) {
            case 1:
                return celsiusToFahrenheit(temperatura);
            case 2:
                return fahrenheitToCelsius(temperatura);
            case 3:
                return kelvinToCelsius(temperatura);
            case 4:
                return celsiusToKelvin(temperatura);
            case 5:
                return fahrenheitToKelvin(temperatura);
            case 6:
                return kelvinToFahrenheit(temperatura);
            default:
                throw new IllegalArgumentException("Operación de conversión no válida.");
        }
    }

    /*private int asignacionDeOperacionTemperatura(String temperatura1, String temperatura2) {
        if (temperatura1.equalsIgnoreCase("C") && temperatura2.equalsIgnoreCase("F")) {
            return 1;
        } else if (temperatura1.equalsIgnoreCase("F") && temperatura2.equalsIgnoreCase("C")) {
            return 2;
        } else if (temperatura1.equalsIgnoreCase("K") && temperatura2.equalsIgnoreCase("C")) {
            return 3;
        } else if (temperatura1.equalsIgnoreCase("C") && temperatura2.equalsIgnoreCase("K")) {
            return 4;
        } else if (temperatura1.equalsIgnoreCase("F") && temperatura2.equalsIgnoreCase("K")) {
            return 5;
        } else if (temperatura1.equalsIgnoreCase("K") && temperatura2.equalsIgnoreCase("F")) {
            return 6;
        } else {
            throw new IllegalArgumentException("Unidades de temperatura no válidas.");
        }
    }*/

    private double celsiusToFahrenheit(double celsius) {
        return celsius * 1.8 + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) / 1.8;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return (5.0 / 9.0) * (fahrenheit - 32) + 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return 1.8 * (kelvin - 273.15) + 32;
    }
}

