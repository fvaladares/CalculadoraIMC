package dev.estudos.calculadoraimc

import android.util.Log
import java.math.BigDecimal

class BMI {

    fun calc(weight: BigDecimal, height: BigDecimal): BigDecimal {
        Log.d(TAG.plus(" weight"), weight.toString())
        Log.d(TAG.plus(" Height"), height.toString())
        val result = weight.divide(height.pow(2), 1, BigDecimal.ROUND_HALF_DOWN)
        Log.d(TAG.plus(" Result"), result.toString())
        return result
    }


    companion object {
        const val TAG = "BMI"
    }
}