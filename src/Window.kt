import components.DrawingPanel
import components.ControlPanel
import components.painters.CartesianPainter
import components.painters.PointsPainter
import components.painters.PolynomialPainter
import polynomials.NewtonPolynomial
import util.ConvertData
import util.Converter
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.lang.Exception
import javax.swing.GroupLayout
import javax.swing.JFrame
import javax.swing.JPanel

/**Окно приложения для визуализации полинома Ньютона**/
class Window : JFrame() {

    private val minSize = Dimension(720, 480) // Размеры окна
    private val mainPanel: JPanel // Панель визуализации
    private val controlPanel: JPanel // Панель с параметрами

    init {
        size = minSize // Задаем размеры окна
        defaultCloseOperation = EXIT_ON_CLOSE // При закрытии окна завершаем программу
        minimumSize = minSize
        controlPanel = ControlPanel() // Инизиализируем панель с параметрами
        mainPanel = DrawingPanel() // Инизиализируем панель визуализации
        mainPanel.background = Color.WHITE //
        controlPanel.background = Color.LIGHT_GRAY

        /**Задаем макет - расположение обьектов в окне**/
        val gl = GroupLayout(contentPane)
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(4)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(4)
        )
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(4)
        )
        layout = gl
        pack()
        /**Задаем данные для конвертера
         * см. Converter - конвертер для соотнесения декартовых и экранных координат**/
        var convertData = ConvertData(
                // Параметры панели для отрисовки
                mainPanel.width, // Ширина панели
                mainPanel.height, // Высота панели
                //Параметры области декартовой системы координат [A, B] x [C, D]
                controlPanel.getXMin(), // A
                controlPanel.getXMax(), // B
                controlPanel.getYMin(), // C
                controlPanel.getYMax()) // D

        /**Создаем необходимые обьекты**/
        val cartesianPainter = CartesianPainter(convertData) // Объект для отрисовки декартовый системы координат
        val pointsPainter = PointsPainter(convertData, 7) // Объект для отрисовки узлов (с радиусом 5px)
        var polynomial = NewtonPolynomial() // Полином Ньютона
        val polynomialPainter = PolynomialPainter(convertData) // Объект для отрисоки полиномов
        val derPolynomialPainter = PolynomialPainter(convertData)
        polynomialPainter.addPolynomial(polynomial) // Добавляем полином в отрисовщик полиномов
        derPolynomialPainter.addPolynomial(polynomial.derivative())

        /** К панели визуализации добавляем отрисовщики: **/
        mainPanel.addPainter(cartesianPainter) // отрисовщик декартовой системы координат
        mainPanel.addPainter(pointsPainter) // отрисовщик точек
        mainPanel.addPainter(polynomialPainter) // отрисовщик полиномов
        mainPanel.addPainter(derPolynomialPainter)

        /**В случае изменения параметров области графика обновляем convertData и отрисовщики, и перерисовываем график**/
        controlPanel.addChangeListener { xMin: Double, xMax: Double, yMin: Double, yMax: Double ->
            convertData = ConvertData(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            cartesianPainter.update(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            pointsPainter.update(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            polynomialPainter.update(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            derPolynomialPainter.update(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            mainPanel.paint(mainPanel.graphics)
        }
        controlPanel.addColorListener {
            polynomialPainter.setColor(controlPanel.getColor1())
            derPolynomialPainter.setColor(controlPanel.getColor2())
            mainPanel.paint(mainPanel.graphics)
        }
        /**В случае изменения размеров окна обновляем convertData и отрисовщики*/
        mainPanel.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                convertData = ConvertData(
                        mainPanel.width,
                        mainPanel.height,
                        convertData.xMin,
                        convertData.xMax,
                        convertData.yMin,
                        convertData.yMax)
                mainPanel.updatePainters()
            }
        })
        /** Обрабатываем нажатия на кнопки мыши**/
        mainPanel.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                if (e != null) {
                    if (e.button == MouseEvent.BUTTON1) { // Если нажата левая кнопка мыши
                        try {
                            derPolynomialPainter.removeAll()
                            polynomial.addNode(Converter.xScr2Crt(e.x, convertData), // Добавляем узел к полиному Ньютона
                                    Converter.yScr2Crt(e.y, convertData))
                            derPolynomialPainter.addPolynomial(polynomial.derivative())
                            pointsPainter.addPoint(e.x, e.y) // Добавляем точку на отрисовку
                        } catch (e: Exception){
                            println(e.message)
                        }


                    }
                    if (e.button == MouseEvent.BUTTON3) { // Если нажата правая кнопка мыши
                        pointsPainter.removePoint(e.x, e.y) // Удаляем узел из отрисовщика точек
                        polynomialPainter.removePolynomial(polynomial) // Удаляем полином из отрисовщика полиномов
                        val newPoints = pointsPainter.getPoints() // Получаем оставшиеся точки
                        polynomial = NewtonPolynomial(arrayListOf()) // Создаем новый полином
                        newPoints.forEach { p -> polynomial.addNode(p.x, p.y) } // Добавляем оставшиеся точки как узлы полинома
                        polynomialPainter.addPolynomial(polynomial) // Добавляем полином на отрисовку
                        derPolynomialPainter.removeAll()
                        derPolynomialPainter.addPolynomial(polynomial.derivative())
                    }
                    mainPanel.paint(mainPanel.graphics) // Отрисовываем панель
                }
            }
        })

    }


}