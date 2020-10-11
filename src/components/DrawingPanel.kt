package components

import util.Converter
import java.awt.Graphics
import javax.swing.JPanel

class DrawingPanel(): JPanel(){
    /** Панель, для отрисовки графики **/
    private val painters = mutableListOf<Painter>()

    override fun paint(g: Graphics?) {
        super.paint(g)
        painters.forEach{painter -> painter.draw(g, this.width, this.height)}
    }

    fun addPainter(p: Painter){
        painters.add(p)
    }

    fun removePainter(p: Painter){
        painters.remove(p)
    }

    fun update(){

    }


}