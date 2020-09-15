package polynomials

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow


open class Polynomial(coef: DoubleArray): Comparable<Polynomial> {
    /**
     * Коэффициенты полинома
     */
    protected var coef: DoubleArray = coef.clone()

    val coeffitients: DoubleArray
        get() = coef.clone()

    private val ZERO = 0.0 //Изменить

    /**
     * Степень полинома
     */
    val power : Int
        get() = coef.size - 1


    init{
        correctPower()
    }

    /**
     * Вторичный конструктор для создания полинома нулевой степени = 0
     */
    constructor() : this(doubleArrayOf(0.0))

    /**
     * Удаление нулевых коэффициентов при старших степенях
     */
    private fun correctPower(){

        var b = true
        coef = coef.reversed().filterIndexed { i, v ->
            if (v.compareTo(0.0) != 0) b = false
            !b || (i == power)
        }.reversed().toDoubleArray()

    }

    /**
     * Сложение двух полиномов
     * @param other второй (правый) полином
     * @return полином, являющийся результатом суммирования данного полинома с другим
     */
    operator fun plus(other: Polynomial) =
        Polynomial( DoubleArray(max(power, other.power) + 1)
        {
            (if (it<coef.size) coef[it] else 0.0) +
                    (if (it<other.coef[it]) other.coef[it] else 0.0)
        }
        )

    /**
     * Определение значения произведения полинома на число
     * @param k вещественный коэффициент
     * @return результат умножения данного (this) полинома на число
     */
    operator fun times(k: Double) =
        Polynomial(DoubleArray(power+1){ coef[it] * k })

    /**
     * Определение разности двух полиномов
     * @param other вычитаемый полином
     * @return разность данного (this) и второго (other) полиномов
     */
    operator fun minus(other: Polynomial) =
        this + other * -1.0

    /**
     * Деление полинома на число
     * @param k - вещественный ненулевой делитель
     */
    @Throws(Exception::class)
    operator fun div(k: Double): Polynomial? {
        if (abs(k) < ZERO) throw Exception("Деление на 0.")
        return times(1 / k)
    }
    override fun toString(): String {
        //TODO refactor compares to CompareTo
        val out  = StringBuilder();
        coef.reversed().forEachIndexed { i, v ->
            val j = power - i //Reversed indexes
            if (v != 0.0 || power == 0) {
                if (j == power) out.append(if (v >= 0.0) "" else "-")
                    else out.append(if (v >= 0) "+ " else "- ")

                if (abs(v) != 1.0 || j == 0) out.append(if ((v.toLong() - v) != 0.0) abs(v) else abs(v.toLong()))
                if (j != 0) out.append("x")
                if (j > 1) out.append("^($j)")
                out.append(" ")
            }
        }
        return out.toString()
    }

    operator fun invoke(x: Double): Double {
//        var add = 1.0;
//        return coef.reduce { v, result ->
//            add; result + add *= x
//        }
      return coef.mapIndexed() { i, v -> v * x.pow(i) }.sum()
//       return coef.reduceIndexed { i, result, v -> result +  v * x.pow(i) }
    }

    /**
     * Произведение полиномов
     * @param other - другой полином
     */
    fun mul(other: Polynomial): Polynomial? {

        val result = DoubleArray(power + other.power + 1){0.0}

        coef.forEachIndexed { i, v ->
            other.coef.forEachIndexed { oi, ov ->
                result[i + oi] += v * ov
            }
        }
        return Polynomial(result)
    }
    /**
     * Сравнение двух полиномов
     * @param other - полином, с которым сравнивается данный
     * @return 1, если this больше other
     *        -1, если this меньше other
     *         0, если полиномы равны
     * */
    override fun compareTo(other: Polynomial): Int {
        when {
            coef.size > other.coef.size -> {
                return 1
            }
            coef.size < other.coef.size -> {
                return -1
            }
            else -> {
                for (i in coef.indices) {
                    if (coef[i] > other.coef[i]) return 1
                    if (coef[i] < other.coef[i]) return -1
                }
            }
        }
        return 0
    }




}