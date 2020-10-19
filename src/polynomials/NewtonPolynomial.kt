package polynomials

/**
 * Класс реализующий полином Ньютона
 * @see Polynomial - базовый класс для полиномов
 * @author Устинов Константин
 */
class NewtonPolynomial() : Polynomial() {
    /**Список узлов, по которым строится полином Ньютноа*/
    private var points: ArrayList<Pair<Double, Double>> = arrayListOf()

    /**Поле, содержащее уже вычисленные разделенные разности*/
    private var divDifferenceData: MutableMap<Pair<Int, Int>, Double> = mutableMapOf()

    /**Конструктор, создающий полином по заданным узлам*/
    constructor(points: ArrayList<Pair<Double, Double>>) : this() {
        points.forEach { point -> addNode(point.first, point.second) }
    }

    /**Метод для вычисления разделенных разностей
     * @return f(x_{first}, ..., x_{last})
     * @param first - начальный индекс
     * @param last - конечный индекс */
    private fun divDifference(first: Int, last: Int): Double {
        return when {
            // Если такая разделенная разность уже вычислена, возвращаем ее
            divDifferenceData.containsKey(Pair(first, last)) -> divDifferenceData[Pair(first, last)] ?: error("")
            else -> {
                if (last == first) {
                    // Если начальный и конечный индексы совпадают,
                    // то искомое значение - это значение функции в точке соответствующей индексу
                    divDifferenceData[Pair(first, first)] = points[last].second // Сохраняем вычисленную разделенную разность
                    return points[last].second // Возвращаем f(x)
                } // Иначе вычисляем по рекурентной формуле
                val left = divDifference(first + 1, last)
                val right = divDifference(first, last - 1)
                val difference = (left - right) / (points[last].first - points[first].first)
                divDifferenceData[Pair(first, last)] = difference // Сохраняем вычисленную разделенную разность
                return difference
            }
        }
    }

    /**Метод для добавления узла интерполирования */
    fun addNode(x: Double, y: Double) {
        // В случае, если для данного x уже задан узел, выбрасываем исключение
        points.forEach { pair ->
            if (pair.first == x) {
                throw Exception("Невозможно добавить узел ($x, $y), так как для x = $x уже задано значение y = ${pair.first}.")
            }
        } // Иначе вычисляем по формуле
        val base = Polynomial(coef)
        var p = Polynomial(doubleArrayOf(1.0))
        points.add(Pair(x, y))
        for (i in 0 until points.size) if (i != 0) p *= Polynomial(doubleArrayOf(-points[i - 1].first, 1.0))
        base += p * divDifference(0, points.size - 1)
        coef = base.coeffitients
    }
}