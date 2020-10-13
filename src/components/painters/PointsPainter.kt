package components.painters

import util.ConvertData
import util.Converter
import java.awt.Color
import java.awt.Graphics
import kotlin.math.abs

data class Point(var x: Double, var y: Double)
class PointsPainter(private var convertData: ConvertData) : Painter {

    private var radius = 5 // Радиус в пикселях
    private var crtRad = Converter.xScr2Crt(radius, convertData) - Converter.xScr2Crt(0, convertData) // Радиус в декатровых координатах

    private val points = mutableListOf<Point>()

    override fun draw(g: Graphics?, Width: Int, Height: Int) {
        if (g != null) {
            g.color = Color.RED
            points.forEach { point ->
                g.fillOval(Converter.xCrt2Scr(point.x, convertData) - radius,
                        Converter.yCrt2Scr(point.y, convertData) - radius, radius * 2, radius * 2)
            }
        }
    }

    fun update(Width: Int, Height: Int, xMin: Double, xMax: Double, yMin: Double, yMax: Double) {
        convertData = ConvertData(Width, Height, xMin, xMax, yMin, yMax)
        crtRad = Converter.xScr2Crt(radius, convertData) - Converter.xScr2Crt(0, convertData)

    }

    override fun update(Width: Int, Height: Int) {
        convertData = ConvertData(Width, Height, convertData.xMin, convertData.xMax, convertData.yMin, convertData.yMax)
        crtRad = Converter.xScr2Crt(radius, convertData) - Converter.xScr2Crt(0, convertData)
    }

    fun addPoint(xScr: Int, yScr: Int) {
        points.add(Point(Converter.xScr2Crt(xScr, convertData), Converter.yScr2Crt(yScr, convertData)))
    }

    fun removePoint(xScr: Int, yScr: Int) {

        val x = Converter.xScr2Crt(xScr, convertData)
        val y = Converter.yScr2Crt(yScr, convertData)
        var pointToRemove: Point? = null
        points.forEach { point ->
            if (abs(x - point.x) < crtRad && (abs(y - point.y) < crtRad)) {
                pointToRemove = point
                return@forEach
            }
        }
        if (pointToRemove != null) {
            points.remove(pointToRemove!!)
        }
    }
}