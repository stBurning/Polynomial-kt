package components

import javax.swing.*


class ControlPanel : JPanel() {

    private val xMinSpinnerModel = SpinnerNumberModel(-1.0, -100.0, 9.9, 0.1)
    private val xMaxSpinnerModel = SpinnerNumberModel(10.0, 0.1, 100.0, 0.1)
    private val yMinSpinnerModel = SpinnerNumberModel(-1.0, -100.0, 9.9, 0.1)
    private val yMaxSpinnerModel = SpinnerNumberModel(10.0, 0.1, 100.0, 0.1)
    private val xMinSpinner: JSpinner = JSpinner(xMinSpinnerModel)
    private val xMaxSpinner: JSpinner = JSpinner(xMaxSpinnerModel)
    private val yMinSpinner: JSpinner = JSpinner(yMinSpinnerModel)
    private val yMaxSpinner: JSpinner = JSpinner(yMaxSpinnerModel)
    private val xMinLabel: JLabel = JLabel("xMin")
    private val xMaxLabel: JLabel = JLabel("xMax")
    private val yMinLabel: JLabel = JLabel("yMin")
    private val yMaxLabel: JLabel = JLabel("yMax")


    fun getXMin(): Double { return xMinSpinnerModel.number.toDouble() }

    fun getXMax(): Double { return xMaxSpinnerModel.number.toDouble() }

    fun getYMin(): Double { return yMinSpinnerModel.number.toDouble() }

    fun getYMax(): Double { return yMaxSpinnerModel.number.toDouble() }

    private val changeListeners = mutableListOf<(Double, Double, Double, Double) -> Unit>()

    fun addChangeListener(l: (Double, Double, Double, Double) -> Unit) {
        changeListeners.add(l)
    }

    fun removeChangeListener(l: (Double, Double, Double, Double) -> Unit) {
        changeListeners.remove(l)
    }

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
                        .addComponent(xMaxLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(yMaxLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(10)
                .addGroup(gl.createParallelGroup()
                        .addComponent(xMaxSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(yMaxSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                ).addGap(20)
        )

        xMinSpinner.addChangeListener {
            xMaxSpinnerModel.minimum = xMinSpinnerModel.number.toDouble() + 0.1
            changeListeners.forEach { l -> l(getXMin(), getXMax(), getYMin(), getYMax()) }
        }
        xMaxSpinner.addChangeListener {
            xMinSpinnerModel.maximum = xMaxSpinnerModel.number.toDouble() - 0.1
            changeListeners.forEach { l -> l(getXMin(), getXMax(), getYMin(), getYMax()) }
        }
        yMinSpinner.addChangeListener {
            yMaxSpinnerModel.minimum = yMinSpinnerModel.number.toDouble() + 0.1
            changeListeners.forEach { l -> l(getXMin(), getXMax(), getYMin(), getYMax()) }
        }
        yMaxSpinner.addChangeListener {
            yMinSpinnerModel.maximum = yMaxSpinnerModel.number.toDouble() - 0.1
            changeListeners.forEach { l -> l(getXMin(), getXMax(), getYMin(), getYMax()) }
        }
        layout = gl
    }
}