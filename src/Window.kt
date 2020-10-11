import components.CartesianPainter
import components.DrawingPanel
import components.LagrangeControl
import util.ConvertData
import java.awt.Color
import java.awt.Dimension
import javax.swing.GroupLayout
import javax.swing.JFrame
import javax.swing.JPanel

class Window: JFrame() {

    private val minSize = Dimension(720, 480)
    private val mainPanel: JPanel
    private val controlPanel: JPanel

    init {
        size = minSize
        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = minSize
        //isResizable = false
        controlPanel = LagrangeControl()
        mainPanel = DrawingPanel()
        val convertData = ConvertData(mainPanel.width, mainPanel.height, controlPanel.getXMin(),controlPanel.getXMax(),controlPanel.getYMin(),controlPanel.getYMax())
        val cartesianPainter = CartesianPainter(convertData)
        mainPanel.addPainter(cartesianPainter)
        controlPanel.addChangeListener{ xMin: Double, xMax: Double, yMin: Double, yMax: Double ->
            cartesianPainter.update(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
            mainPanel.paint(mainPanel.graphics)
        }

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



    }
}