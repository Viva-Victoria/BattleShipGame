package org.battleshipgame.ui

import scala.language.postfixOps

import org.battleshipgame.render.{Screen, TextView, Button, ImageView}

/**
 * Экран подключения к игре / создания игры
 * 
 * @author Кирилл Испольнов
 * @version 1.0
 * @since 2.0.0
 */
trait ConnectionScreen extends Screen {
    def buttons(): Array[Button]
    def backImage(): ImageView
    def backLabel(): TextView
    def gameId(): TextView
    
    private var gameIdCursorPosition: Int = 0
    
    override def render(): Unit = {
        super.render()
        
        renderer begin()
        
        renderer image(backImage rectangle, backImage image)
        label(backLabel)
        input(gameId)
        
        renderer end()
    }
    
    /**
     * Ооо, проверяем ввод символов и меняем содержимое текстового поля
     * Слабонервные - пройдите в следующий файл
     */
    override def onKeyPress(key: Int): Boolean = {
        var text = gameId text
        
        if (key == 8) {
            //BACKSPACE <-
            if (gameIdCursorPosition > 0) {
                gameIdCursorPosition -= 1
                val left = text substring(0, gameIdCursorPosition) 
                val right = text substring(gameIdCursorPosition + 1)
                
                text = left + right
            }
        } else if (key == 46) {
            //DELETE del
            if (gameIdCursorPosition < text.length) {
                val left = text substring(0, gameIdCursorPosition)
                val right = text substring(gameIdCursorPosition + 1)
                gameIdCursorPosition -= 1
                
                text = left + right
            }
        } else {
            //какой-то символ
            text = text + (key toChar)
        }
        
        //апдейт
        gameId text = text
        return true
    }
}