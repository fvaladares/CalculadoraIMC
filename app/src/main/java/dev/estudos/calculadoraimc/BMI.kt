package dev.estudos.calculadoraimc

import java.math.BigDecimal

class BMI {

    fun calc(weight: BigDecimal, height: BigDecimal): BigDecimal {
        return (weight / height.pow(2))
    }
}