import components.painters.CartesianPainter
import components.DrawingPanel
import components.LagrangeControl
import components.painters.PolynomialPainter
import components.painters.PointsPainter
import polynomials.NewtonPolynomial
import util.ConvertData
import util.Converter
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.GroupLayout
import javax.swing.JFrame
import javax.swing.JPanel

class Window : JFrame() {

    private val minSize = Dimension(720, 480)
    private val mainPanel: JPanel
    private val controlPanel: JPanel


    init {
        size = minSize
        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = minSize
        controlPanel = LagrangeControl()
        mainPanel = DrawingPanel()
        mainPanel.background = Color.WHITE
        controlPanel.background = Color.LIGHT_GRAY

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
        var convertData = ConvertData(
                mainPanel.width,
                mainPanel.height,
                controlPanel.getXMin(),
                controlPanel.getXMax(),
                controlPanel.getYMin(),
                controlPanel.getYMax())

        val cartesianPainter = CartesianPainter(convertData) // Объект для отрисовки декартовый системы координат
        val pointsPainter = PointsPainter(convertData, 5) // Объект для отрисовки узлов
        var polynomial = NewtonPolynomial(ArrayList()) // Полином Ньютона
        val polynomialPainter = PolynomialPainter(convertData) // Объект для отрисоки полиномов
        polynomialPainter.addPolynomial(polynomial)
        mainPanel.addPainter(cartesianPainter)
        mainPanel.addPainter(pointsPainter)
        mainPanel.addPainter(polynomialPainter)
        //В случае изменения области обновляем отрисовщики
        controlPanel.addChangeListener { xMin: Double, xMax: Double, yMin: Double, yMax: Double ->
            convertData = ConvertData(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            cartesianPainter.update(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            pointsPainter.update(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            polynomialPainter.update(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            mainPanel.paint(mainPanel.graphics)
        }
        mainPanel.addComponentListener(object : ComponentAdapter(){
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

        mainPanel.addMouseListener(object: MouseAdapter(){
            override fun mouseClicked(e: MouseEvent?) {
                if (e != null) {
                    println("${Converter.xScr2Crt(e.x, convertData)} , ${Converter.yScr2Crt(e.y, convertData)}")
                    if (e.button == MouseEvent.BUTTON1) {
                        pointsPainter.addPoint(e.x, e.y)
                        polynomial.addNode(Converter.xScr2Crt(e.x, convertData),
                                Converter.yScr2Crt(e.y, convertData))
                    }
                    if (e.button == MouseEvent.BUTTON3) {
                        pointsPainter.removePoint(e.x, e.y) //Удаляем узел из отрисовщика точек
                        polynomialPainter.removePolynomial(polynomial) // Удаляем полином из отрисовщика полиномов
                        val newPoints = pointsPainter.getPoints()
                        polynomial = NewtonPolynomial(arrayListOf()) //Создаем новый полином
                        newPoints.forEach {p -> polynomial.addNode(p.x, p.y)}
                        polynomialPainter.addPolynomial(polynomial)
                    }
                    mainPanel.paint(mainPanel.graphics)
                }
            }

            override fun mouseMoved(e: MouseEvent?) {

            }
        })

    }


}