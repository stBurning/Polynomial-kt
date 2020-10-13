import components.painters.CartesianPainter
import components.DrawingPanel
import components.LagrangeControl
import components.painters.PointsPainter
import util.ConvertData
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
        val convertData = ConvertData(mainPanel.width, mainPanel.height, controlPanel.getXMin(), controlPanel.getXMax(), controlPanel.getYMin(), controlPanel.getYMax())
        val cartesianPainter = CartesianPainter(convertData)
        val pointsPainter = PointsPainter(convertData)
        mainPanel.addPainter(cartesianPainter)
        mainPanel.addPainter(pointsPainter)
        controlPanel.addChangeListener { xMin: Double, xMax: Double, yMin: Double, yMax: Double ->
            cartesianPainter.update(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            pointsPainter.update(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            mainPanel.paint(mainPanel.graphics)
        }
        mainPanel.addComponentListener(object : ComponentAdapter(){
            override fun componentResized(e: ComponentEvent?) {
                mainPanel.updatePainters()
            }
        })

        mainPanel.addMouseListener(object: MouseAdapter(){
            var currX = 0
            var currY = 0

            override fun mouseClicked(e: MouseEvent?) {
                if (e != null) {
                    if (e.button == MouseEvent.BUTTON1)
                        pointsPainter.addPoint(e.x, e.y)
                    if (e.button == MouseEvent.BUTTON3)
                        pointsPainter.removePoint(e.x, e.y)
                    mainPanel.paint(mainPanel.graphics)
                }
            }

            override fun mouseMoved(e: MouseEvent?) {

            }
        })

    }


}