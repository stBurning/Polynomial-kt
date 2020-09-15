package polynomials

import kotlin.collections.MutableMap

class LagrangePolynomial(x_fx: MutableMap<Double, Double>) : Polynomial(){

    val x_fx = x_fx.toMutableMap()

    init {
        var p = Polynomial()
        x_fx.forEach{
            p += fundamental(it.key) * it.value
        }

    }

    private fun fundamental(key: Double): Polynomial {
        var p = Polynomial(doubleArrayOf(1.0))
        x_fx.forEach {
            if (it.key != key){
                val m = Polynomial(doubleArrayOf(it.key, 1.0)) /
                        (key - it.key)
                if (m != null) {
                    p *= m
                }else return null

            }


        }
        return p;
    }

}