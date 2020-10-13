package util

object Converter {
    fun xCrt2Scr(x: Double, data: ConvertData): Int {
        val kw = data.width / (data.xMax - data.xMin)
        return (x * kw + (-data.xMin) * kw).toInt()
    }

    fun xScr2Crt(x: Int, data: ConvertData): Double {
        val kw = data.width / (data.xMax - data.xMin)
        return x * (1.0 / kw) + data.xMin
    }

    fun yCrt2Scr(y: Double, data: ConvertData): Int {
        val kh = data.height / (data.yMax - data.yMin);
        return (kh * (data.yMax - y)).toInt()
    }

    fun yScr2Crt(y: Int, data: ConvertData): Double {
        val kh = data.height / (data.yMax - data.yMin)
        return (-y) * (1.0 / kh) + data.yMax
    }
}

data class ConvertData(val _width: Int, val _height: Int, val xMin: Double, val xMax: Double, val yMin: Double, val yMax: Double){
    val width: Int
    get() = _width - 1
    val height: Int
    get() = _height - 1
}