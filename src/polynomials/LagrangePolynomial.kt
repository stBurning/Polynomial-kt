
package polynomials

class Lagrange(xfx: MutableMap<Double, Double>) : Polynomial(){

    val xfx = xfx.toMutableMap()
    init{
        val p = Polynomial()
        xfx.forEach {
            val r = fundamental(it.key)
            if (r != null) p += r * it.value
            else return@forEach
        }
        coef = p.coeffitients
    }

    /**
     * Вычисление фундаментальных полиномов Лагранжа
     */
    private fun fundamental(key: Double): Polynomial? {
        var p: Polynomial = Polynomial(doubleArrayOf(1.0))
        xfx.forEach {
            if (it.key.compareTo(key)!=0){
                val m = Polynomial(doubleArrayOf(-it.key, 1.0)) /
                        (key - it.key)
                if (m != null){
                    //TODO
                } else return@fundamental null
            }
        }
        return p
    }

}
