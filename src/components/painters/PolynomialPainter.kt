package components.painters


import polynomials.Polynomial
import util.ConvertData
import util.Converter
import java.awt.*

open class PolynomialPainter(private var convertData: ConvertData): Painter {

    private var color = Color.GREEN
    public fun setColor(newColor: Color){color = newColor}


    private val polynomials = mutableListOf<Polynomial>()

    fun addPolynomial(polynomial: Polynomial){
        polynomials.add(polynomial)
    }
    fun removePolynomial(polynomial: Polynomial){
        polynomials.remove(polynomial)
    }
    fun removeAll(){
        polynomials.clear()
    }

    override fun draw(g: Graphics?, Width: Int, Height: Int) {

        val g2 = g as Graphics2D
        g2.color = color
        g2.stroke = BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
        val rh = mapOf(
                RenderingHints.KEY_ANTIALIASING to RenderingHints.VALUE_ANTIALIAS_ON,
                RenderingHints.KEY_INTERPOLATION to RenderingHints.VALUE_INTERPOLATION_BICUBIC,
                RenderingHints.KEY_RENDERING to RenderingHints.VALUE_RENDER_QUALITY,
                RenderingHints.KEY_DITHERING to RenderingHints.VALUE_DITHER_ENABLE)

        g2.setRenderingHints(rh)
        polynomials.forEach { polynomial ->
            for (i in (0..convertData.width)) {
                val x0 = Converter.xScr2Crt(i, convertData)
                val x1 = Converter.xScr2Crt(i + 1, convertData)
                g2?.drawLine(i, Converter.yCrt2Scr(polynomial.invoke(x0), convertData),
                        i + 1, Converter.yCrt2Scr(polynomial.invoke(x1), convertData))
            }
        }
    }

    fun update(Width: Int, Height: Int, xMin: Double, xMax: Double, yMin: Double, yMax: Double) {
        convertData = ConvertData(Width, Height, xMin, xMax, yMin, yMax)
    }

    override fun update(Width: Int, Height: Int) {
        convertData = ConvertData(Width, Height, convertData.xMin, convertData.xMax, convertData.yMin, convertData.yMax)
    }
}