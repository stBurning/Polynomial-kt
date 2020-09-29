import javax.swing.*
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener

class LagrangeControl: JPanel() {
    private val xMinSpinner: JSpinner = JSpinner()
    private val xMaxSpinner: JSpinner = JSpinner()
    private val yMinSpinner: JSpinner = JSpinner()
    private val yMaxSpinner: JSpinner = JSpinner()
    private val xMinLabel: JLabel = JLabel("xMin")
    private val xMaxLabel: JLabel = JLabel("xMax")
    private val yMinLabel: JLabel = JLabel("yMin")
    private val yMaxLabel: JLabel = JLabel("yMax")

    init {
        val gl = GroupLayout(this)
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(10)
                .addGroup(gl.createParallelGroup()
                        .addComponent(xMinLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(xMinSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(xMaxLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(xMaxSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)

                ).addGap(10)
                .addGroup(gl.createParallelGroup()
                        .addComponent(yMinLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(yMinSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(yMaxLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(yMaxSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                ).addGap(10)
        )

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(20)
                .addGroup(gl.createParallelGroup()
                        .addComponent(xMinLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(yMinLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(10)
                .addGroup(gl.createParallelGroup()
                        .addComponent(xMinSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(yMinSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)

                ).addGap(100)
                .addGroup(gl.createParallelGroup()
                        .addComponent(xMaxLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(yMaxLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(10)
                .addGroup(gl.createParallelGroup()
                        .addComponent(xMaxSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(yMaxSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                ).addGap(20)
        )

        xMinSpinner.model = SpinnerNumberModel(0.0, -100.0, 100.0, 0.1)
        xMinSpinner.model = SpinnerNumberModel(0.0, -100.0, 100.0, 0.1)
        xMinSpinner.model = SpinnerNumberModel(0.0, -100.0, 100.0, 0.1)
        xMinSpinner.model = SpinnerNumberModel(0.0, -100.0, 100.0, 0.1)

        var xMinVal = xMinSpinner.value
        var xMaxVal = xMaxSpinner.value
        var yMinVal = yMinSpinner.value
        var yMaxVal = yMaxSpinner.value


       
        layout = gl
    }
}