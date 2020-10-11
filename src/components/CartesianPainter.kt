package components

import util.ConvertData
import util.Converter
import java.awt.Color
import java.awt.Graphics

class CartesianPainter(private var convertData: ConvertData): Painter {

    private val converter: Converter = Converter


    override fun draw(g: Graphics?, Width: Int, Height: Int) {

        val xMin = converter.xCrt2Scr(convertData.xMin, convertData)
        val xMax = converter.yCrt2Scr(convertData.yMin, convertData)
        val yMin = converter.xCrt2Scr(convertData.xMax, convertData)
        val yMax = converter.yCrt2Scr(convertData.yMax, convertData)

        if (g != null){
            g.color = Color.BLACK
            //drawAxis(g, Width, Height)
            drawAxis(g)
            drawTicks(g)
            drawLabel(g)
            println("Height: ${convertData.height}")
            println("Width: ${convertData.width}")
            println("xMin: ${convertData.xMin}")
            println("xMax: ${convertData.xMax}")
            println("yMin: ${convertData.yMin}")
            println("yMax: ${convertData.yMax}")
        }

    }

    private fun drawAxis(g: Graphics?){
        val x0 = Converter.xCrt2Scr(0.0, convertData)
        val y0 = Converter.yCrt2Scr(0.0, convertData)
        print("x0 = $x0, y0 = $y0,")
        if (g != null) {
            g.color = Color.BLACK
            g.drawLine(0, y0, convertData.width, y0)
            g.drawLine(x0, 0, x0, convertData.height)
        }
    }

    private fun drawTicks(g:Graphics){//Черточки
        val x0 = Converter.xCrt2Scr(0.0, convertData)
        val y0 = Converter.yCrt2Scr(0.0, convertData)
        for (i in  (convertData.xMin*10).toInt()..(convertData.xMax*10).toInt()) {
            g.color=Color.DARK_GRAY
            var d = 2;
            if ((i % 5)!=0) {
                d += 1;
                g.color=Color.RED
            }
            if ((i % 10)!=0) {
                d += 1;
                g.color=Color.DARK_GRAY
            }
            val x=Converter.xCrt2Scr((i/10.0),convertData)
            g.drawLine(x, y0 - d, x, y0 + d);
        }
        for (i in  (convertData.yMin*10).toInt()..(convertData.yMax*10).toInt()) {
            g.color=Color.BLUE
            var d = 2;
            if ((i % 5)!=0) {
                d += 1;
                g.color=Color.RED
            }
            if ((i % 10)!=0) {
                d += 1;
                g.color=Color.DARK_GRAY
            }
            val y = Converter.yCrt2Scr((i / 10.0),convertData)
            g.drawLine( x0-d, y, x0+d, y);
        }
    }
    private fun drawLabel(g:Graphics){//числа
        val x0 = Converter.xCrt2Scr(0.0, convertData)
        val y0 = Converter.yCrt2Scr(0.0, convertData)
        g.color= Color.BLUE
        for (i in  (convertData.xMin*10).toInt()..(convertData.xMax*10).toInt()) {
            if (i % 5!=0) continue
            if (i==0) continue
            val x = Converter.xCrt2Scr(i / 10.0,convertData)
            val d :Int;
            if (i>0){
                d=20
            }else d=-10
            g.drawString((i/10.0).toString(),x-8,y0+d)
        }
        for (i in  (convertData.yMin*10).toInt()..(convertData.yMax*10).toInt()) {
            if (i % 5!=0) continue;
            if (i==0) continue;
            val y = Converter.yCrt2Scr(i / 10.0,convertData)

            val d:Int
            if (i>0){
                d=5
            }else d=-30
            g.drawString((i/10.0).toString(),x0+d,y+5)
        }
    }

    fun update(Width: Int, Height: Int, xMin: Double, xMax: Double, yMin: Double, yMax: Double){
        convertData = ConvertData(Width, Height, xMin, xMax, yMin, yMax)
    }
}