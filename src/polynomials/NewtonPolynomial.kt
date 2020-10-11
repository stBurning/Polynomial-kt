package polynomials


//this (mutableMapOf)
class NewtonPolynomial(private var points: ArrayList<Pair<Double, Double>>) : Polynomial() {

    private var divDifferenceData: MutableMap<Pair<Int, Int>, Double> = mutableMapOf()

    init {
        //TODO Реализовать через addNode
        val base = Polynomial()
        for (k in 0 until points.size) {
            var p = Polynomial(doubleArrayOf(1.0))
            for (i in 0..k) if (i != 0) p *= Polynomial(doubleArrayOf(-points[i - 1].first, 1.0))
            base += p * divDifference(0, k)
        }
        coef = base.coeffitients

    }

    private fun divDifference(first: Int, last: Int): Double {
        return when {
            divDifferenceData.containsKey(Pair(first, last)) -> divDifferenceData[Pair(first, last)] ?: error("")
            else -> {
                if (last == first) {
                    divDifferenceData[Pair(first, first)] = points[last].second
                    return points[last].second
                } // returns f(x)

                val left = divDifference(first + 1, last)
                val right = divDifference(first, last - 1)
                val difference = (left - right) / (points[last].first - points[first].first)
                divDifferenceData[Pair(first, last)] = difference
                return difference
            }
        }
    }

    fun addNode(x: Double, y: Double) {
        points.forEach { pair ->
            if (pair.first == x) {
                println("Невозможно добавить узел ($x, $y), так как для x = $x уже задано значение y = ${pair.first}.")
                return
            }
        }
        val base = Polynomial(coef)
        var p = Polynomial(doubleArrayOf(1.0))
        points.add(Pair(x, y))
        for (i in 0 until points.size) if (i != 0) p *= Polynomial(doubleArrayOf(-points[i - 1].first, 1.0))
        base += p * divDifference(0, points.size - 1)
        coef = base.coeffitients
    }

}