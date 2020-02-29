package org.battleshipgame.render

import org.battleshipgame.render.ButtonState.DEFAULT

/**
 * Вьюха, представление, абстрактное что-то на экране
 * 
 * @author Кирилл Испольнов
 * @version 1.0
 * @since 2.0.0
 */
class View(var rectangle: Rectangle) { }

trait ClickListener {
    def onClick(): Unit
}

/**
 * Вьюха с текстом: надпись или текстовое поле
 * 
 * @author Кирилл Испольнов
 * @version 1.0
 * @since 2.0.0
 */
class TextView(rectangle: Rectangle, var text: String, val textSize: Double, var dark: Boolean, var listener: ClickListener) extends View(rectangle) {
    def this(x: Int, y: Int, w: Int, h: Int, text: String, textSize: Double) =
        this(new Rectangle(x, y, w, h), text, textSize, false, () => {})
        
    def this(x: Int, y: Int, w: Int, h: Int, text: String, textSize: Double, dark: Boolean) =
        this(new Rectangle(x, y, w, h), text, textSize, dark, () => {})
        
    def this(x: Int, y: Int, w: Int, h: Int, r: Double, text: String, textSize: Double, dark: Boolean) =
        this(new Rectangle(x, y, w, h, r), text, textSize, dark, () => {})
}

/**
 * Кнопка
 * 
 * @author Кирилл Испольнов
 * @version 1.0
 * @since 2.0.0
 */
class Button(rectangle: Rectangle, text: String, textSize: Double, dark: Boolean, listener: ClickListener, var state: ButtonState) 
    extends TextView(rectangle, text, textSize, dark, listener) {
        
    def this(x: Int, y: Int, w: Int, h: Int, text: String, textSize: Double) =
        this(new Rectangle(x, y, w, h), text, textSize, false, () => {}, DEFAULT)
        
    def this(x: Int, y: Int, w: Int, h: Int, r: Double, text: String, textSize: Double) =
        this(new Rectangle(x, y, w, h, r), text, textSize, false, () => {}, DEFAULT)
}

/**
 * Вьюха с картинкой
 * 
 * @author Кирилл Испольнов
 * @version 1.0
 * @since 2.0.0
 */
class ImageView(rectangle: Rectangle, var image: Image, var listener: ClickListener) extends View(rectangle) {
    def this(x: Int, y: Int, w: Int, h: Int, image: Image) = 
        this(new Rectangle(x, y, w, h), image, () => {})
}

/**
 * МэпГридВью - сетка игрового поля
 * 
 * @author Кирилл Испольнов
 * @version 1.0
 * @since 2.0.0
 */
class MapGridView(rectangle: Rectangle, var cellSize: Int) extends View(rectangle) {
    def this(x: Int, y: Int, width: Int, height: Int, styles: StylesResolver) = this(new Rectangle(x, y, width, height), (width) / 10)
    
    def toGridCoords(pixel: Point): Point = {
        val gridRect = rectangle
        
        val ox = pixel.x - gridRect.x
        val oy = pixel.y - gridRect.y
        
        val i = ox / cellSize
        var j = oy / cellSize
        
        return new Point(i, j)
    }
    
    def toPixelCoords(grid: Point): Point = {
        val x = grid.x * cellSize
        var y = grid.y * cellSize
        
        return new Point(x, y)
    }
    
    def toGridSize(pixel: Size): Size = {
        val i = pixel.width / cellSize
        var j = pixel.height / cellSize
        
        return new Size(i, j)
    }
    
    def toPixelSize(grid: Size): Size = {
        val w = grid.width * cellSize
        var h = grid.height * cellSize 
        
        return new Size(w, h)
    }
}