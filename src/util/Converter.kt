package util

import components.CartesianPainter
import components.DrawingPanel

object Converter {
    fun xCrt2Scr(x: Double, plane: ConvertData): Int { //Декартовые в экранные
        val kw = plane.width / (plane.xMax - plane.xMin)
        return (x * kw + (-plane.xMin) * kw).toInt()
    }

    fun xScr2Crt(x: Int, plane: ConvertData): Double {//Экранные в декартовые
        val kw = plane.width / (plane.xMax - plane.xMin)
        return x * (1.0 / kw) + plane.xMin
    }

    fun yCrt2Scr(y: Double, plane: ConvertData): Int {//Декартовые в экранные
        val kh = plane.height / (plane.yMax - plane.yMin);
        return (kh * (plane.yMax - y)).toInt()
    }

    fun yScr2Crt(y: Int, plane: ConvertData): Double {//Экранные в декартовые
        val kh = plane.height / (plane.yMax - plane.yMin);
        return (-y) * (1.0 / kh) + plane.yMax;
    }
}

data class ConvertData( val width: Int, val height: Int, val xMin: Double, val xMax: Double, val yMin: Double, val yMax: Double)