package components.painters

import java.awt.Graphics

interface Painter {

    fun draw(g: Graphics?, Width: Int, Height: Int)

    fun update(Width: Int, Height: Int)

}