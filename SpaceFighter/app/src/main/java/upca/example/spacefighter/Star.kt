package upca.example.spacefighter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import java.util.Random


class Star : Sprite {

    var speed = 0
    var isBoosting = false

    val MAX_SPEED = 20
    val MIN_SPEED = 1

    constructor(
        context: Context,
        width :  Int,
        height : Int
    ) :super (context, width,height){

        x = generator.nextInt(width)
        y = generator.nextInt(height)
        speed = generator.nextInt(12) + 1
    }

    override fun update(){

        if(isBoosting) speed +=2
        else speed -= 5
        if (speed < MIN_SPEED) speed = MIN_SPEED
        if (speed > MAX_SPEED) speed = MAX_SPEED

        x -= speed
        x -= 10
        if (x < 0) {
            x = width
            y = generator.nextInt(height)
            speed = generator.nextInt(12) + 1
        }
    }

    override fun draw(canvas: Canvas){
        paint.color = Color.YELLOW
        paint.strokeWidth = starRadius.toFloat()
        canvas.drawPoint(
            x.toFloat(),
            y.toFloat(),
            paint
        )
    }

    var starRadius : Int = 1
        get(){
            return generator.nextInt(6) + 2
        }


}