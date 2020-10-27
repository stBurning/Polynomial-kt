import polynomials.LagrangePolynomial
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.cosh
import kotlin.math.pow
fun f(x: Double): Double{
    return 2.0.pow(4*x/5)
}
fun main() {
    val window = Window()
    window.isVisible = true
    window.isResizable = true
}
