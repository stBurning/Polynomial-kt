package components.painters

import util.ConvertData
import util.Converter
import java.awt.Color
import java.awt.Font
import java.awt.Graphics

class CartesianPainter(private var convertData: ConvertData) : Painter {

    override fun draw(g: Graphics?, Width: Int, Height: Int) {

        if (g != null) {
            g.color = Color.BLACK
            drawAxes(g) // Рисуем оси
            drawTicks(g) // Рисуем черточки
            drawLabels(g) // Рисуем нумерацию и подписи
        }
    }

    private fun drawAxes(g: Graphics?) {
        /** Отрисовываем оси X и Y
         * (x0, y0) - координаты точки (0, 0) на экране **/
        val x0 = Converter.xCrt2Scr(0.0, convertData)
        val y0 = Converter.yCrt2Scr(0.0, convertData)
        if (g != null) {
            g.color = Color.BLACK
            g.drawLine(0, y0, convertData.width, y0)
            g.drawLine(x0, 0, x0, convertData.height)
        }
    }

    private fun drawTicks(g: Graphics) {
        val dt = 2 // Половина длины черточки
        g.color = Color.DARK_GRAY // Цвет черточек
        var x0 = Converter.xCrt2Scr(0.0, convertData)
        var y0 = Converter.yCrt2Scr(0.0, convertData)

        if (x0 <= 0) x0 = 0
        if (x0 > convertData.width) x0 = convertData.width
        if (y0 <= 0) y0 = 0
        if (y0 > convertData.height) y0 = convertData.height

        val kX = when {
            convertData.xMax - convertData.xMin < 10 -> { 10.0 }
            convertData.xMax - convertData.xMin < 24 -> { 5.0 }
            convertData.xMax - convertData.xMin < 36 -> { 2.5 }
            else -> { 1.25 }
        }
        for (i in (convertData.xMin * kX).toInt() until (convertData.xMax * kX).toInt()) {

            val gap = if (i % 10 == 0) 2 else if (i % 5 == 0) 1 else 0
            val x = Converter.xCrt2Scr((i / kX), convertData)
            g.drawLine(x, y0 - dt - gap, x, y0 + dt + gap)
        }
        val kY = when {
            convertData.yMax - convertData.yMin < 10 -> { 10.0 }
            convertData.yMax - convertData.yMin < 24 -> { 5.0 }
            convertData.yMax - convertData.yMin < 36 -> { 2.5 }
            else -> { 1.25 }
        }
        for (i in (convertData.yMin * kY).toInt() until (convertData.yMax * kY).toInt()) {
            val gap = if (i % 10 == 0) 2 else if (i % 5 == 0) 1 else 0
            val y = Converter.yCrt2Scr((i / kY), convertData)
            g.drawLine(x0 - dt - gap, y, x0 + dt + gap, y)
        }
    }

    private fun drawLabels(g: Graphics) {//числа

        g.font = Font("Cambria", Font.BOLD, 12)
        val m = g.fontMetrics

        var x0 = Converter.xCrt2Scr(0.0, convertData)
        var y0 = Converter.yCrt2Scr(0.0, convertData)
        if (x0 <= 0) x0 = 0

        if (x0 > convertData.width) x0 = convertData.width
        if (y0 <= 0) y0 = 0
        if (y0 > convertData.height) y0 = convertData.height

        g.color = Color.BLUE

        val kX = when {
            convertData.xMax - convertData.xMin < 10 -> { 10.0 }
            convertData.xMax - convertData.xMin < 24 -> { 5.0 }
            convertData.xMax - convertData.xMin < 36 -> { 2.5 }
            else -> { 1.25 }
        }

        for (i in (convertData.xMin * kX).toInt() until (convertData.xMax * kX).toInt()) {
            if (i % 5 != 0 || i == 0) continue
            val x = Converter.xCrt2Scr(((i / kX)), convertData)
            val dy = m.getStringBounds((((i / kX))).toString(), g).height.toInt()
            val dx = m.getStringBounds(((i / kX)).toString(), g).width.toInt()
            val dt: Int = if (y0 > convertData.height - dy) dy + 5 else 0
            g.drawString((i / kX).toString(), x - dx / 2, y0 + dy - dt)
        }

        val kY = when {
            convertData.yMax - convertData.yMin < 10 -> { 10.0 }
            convertData.yMax - convertData.yMin < 24 -> { 5.0 }
            convertData.yMax - convertData.yMin < 36 -> { 2.5 }
            else -> { 1.25 }
        }
        for (i in (convertData.yMin * kY).toInt() until (convertData.yMax * kY).toInt()) {
            if (i % 5 != 0 || i == 0) continue
            val y = Converter.yCrt2Scr((i / kY), convertData)
            val dy = m.getStringBounds((i / kY).toString(), g).height.toInt()
            val dx = m.getStringBounds((i / kY).toString(), g).width.toInt()
            val dt: Int = if (x0 > convertData.width - dx) 10 + dx else 0
            g.drawString((i / kY).toString(), x0 + 4 - dt, y + dy / 3)
        }
    }

    fun update(Width: Int, Height: Int, xMin: Double, xMax: Double, yMin: Double, yMax: Double) {
        convertData = ConvertData(Width, Height, xMin, xMax, yMin, yMax)
    }

    override fun update(Width: Int, Height: Int) {
        convertData = ConvertData(Width, Height, convertData.xMin, convertData.xMax, convertData.yMin, convertData.yMax)
    }
}