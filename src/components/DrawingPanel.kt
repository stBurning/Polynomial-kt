package components

import components.painters.Painter
import java.awt.Graphics
import javax.swing.JPanel

class DrawingPanel : JPanel() {
    /** Панель, для отрисовки графики **/
    private val painters = mutableListOf<Painter>()

    override fun paint(g: Graphics?) {
        super.paint(g)
        painters.forEach { painter ->
            //painter.update(this.width, this.height)
            painter.draw(g, this.width, this.height)
        }

    }

    fun addPainter(p: Painter) {
        painters.add(p)
        repaint()
    }

    fun removePainter(p: Painter) {
        painters.remove(p)
        repaint()
    }
    fun updatePainters(){
        painters.forEach { painter ->
            painter.update(width, height)
        }
    }


}