package upca.example.spacefighter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import java.util.Random

abstract class Sprite {

    var x = 0
    var y = 0
    var width = 0
    var height = 0
    var bitmap : Bitmap? = null
    var generator = Random()
    var paint = Paint()

    var collisionBox : Rect = Rect()

     constructor(
         context: Context,
         width :  Int,
         height : Int
    ){
        this.width = width
        this.height = height

         collisionBox = Rect(x, y, bitmap?.width?:1, bitmap?.height?:1)

     }

    open fun update() {
        collisionBox.left = x
        collisionBox.top = y
        collisionBox.right = x + (bitmap?.width?:1)
        collisionBox.bottom =y + (bitmap?.height?:1)
    }

    open fun draw(canvas: Canvas) {
        bitmap?.let {
            canvas.drawBitmap(it,
                x.toFloat(),
                y.toFloat(),
                paint
            )
        }
        paint.color = Color.GREEN
        paint.strokeWidth = 1.0f
        paint.style = Paint.Style.STROKE
        canvas.drawRect(collisionBox, paint)
    }

}