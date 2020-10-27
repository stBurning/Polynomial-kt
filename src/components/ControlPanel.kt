package components

import java.awt.Color
import java.awt.event.ComponentAdapter
import javax.swing.*


class ControlPanel : JPanel() {

    private val colorChooser = JColorChooser()
    private val chButton1 = JButton("Цвет графика")
    private val chButton2 = JButton("Цвет производной")
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

    private var color1 = Color.GREEN
    fun getColor1(): Color {return color1}
    private var color2 = Color.GREEN
    fun getColor2(): Color {return color2}



    fun getXMin(): Double { return xMinSpinnerModel.number.toDouble() }

    fun getXMax(): Double { return xMaxSpinnerModel.number.toDouble() }

    fun getYMin(): Double { return yMinSpinnerModel.number.toDouble() }

    fun getYMax(): Double { return yMaxSpinnerModel.number.toDouble() }

    private val changeListeners = mutableListOf<(Double, Double, Double, Double) -> Unit>()

    private val colorListeners = mutableListOf<(Unit) -> Unit>()

    fun addColorListener(l: (Unit) -> Unit){
        colorListeners.add(l)
    }
    fun removeColorListener(l: (Unit) -> Unit){
        colorListeners.remove(l)
    }

    fun addChangeListener(l: (Double, Double, Double, Double) -> Unit) {
        changeListeners.add(l)
    }

    fun removeChangeListener(l: (Double, Double, Double, Double) -> Unit) {
        changeListeners.remove(l)
    }

    init {
        chButton1.addActionListener {
            run {
                color1 = JColorChooser.showDialog(this, "Select a color", Color.RED)
                colorListeners.forEach { l -> l(Unit) }
            }
        }
        chButton2.addActionListener {
            run {
                color2 = JColorChooser.showDialog(this, "Select a color", Color.RED)
                colorListeners.forEach { l -> l(Unit) }
            }
        }


        val gl = GroupLayout(this)
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(10)
                .addGroup(gl.createParallelGroup()
                        .addComponent(chButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(xMinLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(xMinSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(xMaxLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(xMaxSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)

                ).addGap(10)
                .addGroup(gl.createParallelGroup()
                        .addComponent(chButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(yMinLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(yMinSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(yMaxLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(yMaxSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                ).addGap(10)
        )

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(20)
                .addGroup(gl.createParallelGroup()
                        .addComponent(chButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(chButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
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