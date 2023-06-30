package org.conversor;

import java.math.BigDecimal;

public class ConvertidorMonedaUtil {
    public BigDecimal convertirMoneda(BigDecimal cantidad, BigDecimal tasa){
        return cantidad.multiply(tasa);
    }
}
