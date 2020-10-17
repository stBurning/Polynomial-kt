package components.painters


import polynomials.Polynomial
import util.ConvertData
import util.Converter
import java.awt.Graphics

open class PolynomialPainter(private var convertData: ConvertData): Painter {

    private val polynomials = mutableListOf<Polynomial>()

    fun addPolynomial(polynomial: Polynomial){
        polynomials.add(polynomial)
    }
    fun removePolynomial(polynomial: Polynomial){
        polynomials.remove(polynomial)
    }

    override fun draw(g: Graphics?, Width: Int, Height: Int) {

        polynomials.forEach { polynomial ->
            for (i in (0..convertData.width)) {
                val x0 = Converter.xScr2Crt(i, convertData)
                val x1 = Converter.xScr2Crt(i + 1, convertData)
                g?.drawLine(i, Converter.yCrt2Scr(polynomial.invoke(x0), convertData),
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