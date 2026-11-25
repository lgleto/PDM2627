package upca.example.spacefighter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas


class Enemy : Sprite {
    var speed = 0
    var isBoosting = false

    val MAX_SPEED = 20
    val MIN_SPEED = 1

    constructor(
        context: Context,
        width :  Int,
        height : Int
    ) : super( context, width, height ){
        bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.enemy
        )

        x = width + generator.nextInt(width/2)
        y = generator.nextInt(height - bitmap!!.height)
        speed = generator.nextInt(6) + 10
    }

    override fun update(){

        if(isBoosting) speed +=2
        else speed -= 5
        if (speed < MIN_SPEED) speed = MIN_SPEED
        if (speed > MAX_SPEED) speed = MAX_SPEED

        x -= speed
        x -= 10
        if (x < -bitmap!!.width) {
            x = width
            y = generator.nextInt(height - bitmap!!.height)
            speed = generator.nextInt(6) + 10
        }

        super.update()
    }


}