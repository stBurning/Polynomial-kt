import polynomials.Polynomial

fun main(){
    val p = Polynomial(doubleArrayOf(1.0, -0.0, -0.3))
    println(p)
    println(p.invoke(10.0))
    println(p)

}