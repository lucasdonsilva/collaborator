package br.com.components;

import java.math.BigDecimal;

import static br.com.components.AppConstants.HUNDRED;

public class CalculatorUtil {

    public static BigDecimal calculatePercentage(Long firstValue, int secondValue){
        if(secondValue != 0){
            BigDecimal totalWithAge = BigDecimal.valueOf(firstValue);
            BigDecimal totalCollaborators = BigDecimal.valueOf(secondValue);
            return totalWithAge.multiply(HUNDRED).divide(totalCollaborators, BigDecimal.ROUND_UP);
        }
        return BigDecimal.ZERO;
    }
}
