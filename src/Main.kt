import polynomials.NewtonPolynomial
import polynomials.Polynomial

fun main(){
//    val window = Window()
//    window.isVisible = true
    //window.isResizable = false
    val p = NewtonPolynomial(arrayListOf(Pair(-1.0, -1.0)))
    println(p)
    p.addNode(2.0, 8.0)
    println(p)
    p.addNode(1.0, 1.0)
    println(p)
    p.addNode(0.0, 0.0)
    println(p)


}