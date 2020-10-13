package components.painters

import polynomials.NewtonPolynomial
import util.ConvertData
import java.awt.Graphics

class GraphsPainter(private var convertData: ConvertData): Painter {
    


    override fun draw(g: Graphics?, Width: Int, Height: Int) {

    }

    fun update(Width: Int, Height: Int, xMin: Double, xMax: Double, yMin: Double, yMax: Double) {
        convertData = ConvertData(Width, Height, xMin, xMax, yMin, yMax)
    }

    override fun update(Width: Int, Height: Int) {
        convertData = ConvertData(Width, Height, convertData.xMin, convertData.xMax, convertData.yMin, convertData.yMax)
    }
}