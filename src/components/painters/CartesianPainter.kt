package components.painters

import util.ConvertData
import util.Converter
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

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
        val x0 = Converter.xCrt2Scr(0.0, convertData)
        val y0 = Converter.yCrt2Scr(0.0, convertData)
        for (i in (convertData.xMin * 10).toInt()..(convertData.xMax * 10).toInt()) {
            val gap = if (i % 10 == 0) 2 else if (i % 5 == 0) 1 else 0
            val x = Converter.xCrt2Scr((i / 10.0), convertData)
            g.drawLine(x, y0 - dt - gap, x, y0 + dt + gap)
        }
        for (i in (convertData.yMin * 10).toInt()..(convertData.yMax * 10).toInt()) {
            val gap = if (i % 10 == 0) 2 else if (i % 5 == 0) 1 else 0
            val y = Converter.yCrt2Scr((i / 10.0), convertData)
            g.drawLine(x0 - dt - gap, y, x0 + dt + gap, y)
        }
    }

    private fun drawLabels(g: Graphics) {//числа
        val x0 = Converter.xCrt2Scr(0.0, convertData)
        val y0 = Converter.yCrt2Scr(0.0, convertData)
        g.color = Color.BLUE
        for (i in (convertData.xMin * 10).toInt()..(convertData.xMax * 10).toInt()) {
            if (i % 5 != 0 || i == 0) continue
            val x = Converter.xCrt2Scr(i / 10.0, convertData)
            val d: Int = if (i > 0) 20 else -10
            g.drawString((i / 10.0).toString(), x - 8, y0 + d)
        }
        for (i in (convertData.yMin * 10).toInt()..(convertData.yMax * 10).toInt()) {
            if (i % 5 != 0 || i == 0) continue
            val y = Converter.yCrt2Scr(i / 10.0, convertData)
            val d: Int = if (i > 0) 5 else -30
            g.drawString((i / 10.0).toString(), x0 + d, y + 5)
        }
    }

    fun update(Width: Int, Height: Int, xMin: Double, xMax: Double, yMin: Double, yMax: Double) {
        convertData = ConvertData(Width, Height, xMin, xMax, yMin, yMax)
    }

    override fun update(Width: Int, Height: Int) {
        convertData = ConvertData(Width, Height, convertData.xMin, convertData.xMax, convertData.yMin, convertData.yMax)
    }
}