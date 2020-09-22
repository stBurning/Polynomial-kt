import java.awt.Color
import java.awt.Dimension
import javax.swing.GroupLayout
import javax.swing.JButton
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
        mainPanel = JPanel()
        mainPanel.background = Color.CYAN
        controlPanel = JPanel()
        controlPanel.background = Color.GREEN
        controlPanel.add(JButton("Button"))
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