import polynomials.LagrangePolynomial
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.cosh
import kotlin.math.pow
fun f(x: Double): Double{
    return 2.0.pow(4*x/5)
}
fun main() {
    //val window = Window()
    //window.isVisible = true
    //window.isResizable = true
    val x1 = cos(PI/10) + 1
    val x2 = cos(3*PI/10) + 1
    val x3 = cos(5*PI/10) + 1
    val x4 = cos(7*PI/10) + 1
    val x5 = cos(9*PI/10) + 1

    val cx1 = 0.29
    val cx2 = 1.42
    val cx3 = 1.76
    
    val p = LagrangePolynomial(mutableMapOf(x1 to f(x1),
            x2 to f(x2),
            x3 to f(x3),
            x4 to f(x4),
            x5 to f(x5)))
    println(p)


    println("L(x) = ${p.invoke(cx1)}; f(x) = ${f(cx1)}")
    println("L(x) = ${p.invoke(cx2)}; f(x) = ${f(cx2)}")
    println("L(x) = ${p.invoke(cx3)}; f(x) = ${f(cx3)}")

}
