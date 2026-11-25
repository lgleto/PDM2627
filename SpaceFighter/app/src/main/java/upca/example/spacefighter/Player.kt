package upca.example.spacefighter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas

class Player : Sprite {

    var isBoosting = false
    val GRAVITY = -10
    val MAX_SPEED = 20
    val MIN_SPEED = 1

    var speed = 20

    constructor(
        context: Context,
        width :  Int,
        height : Int
    ) : super(context, width,height){
        bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.player
        )

        this.width = width
        this.height = height

        x = 75
        y = 50
    }

    override fun update(){
        if(isBoosting) speed +=2
        else speed -= 5
        if (speed < MIN_SPEED) speed = MIN_SPEED
        if (speed > MAX_SPEED) speed = MAX_SPEED

        y -= speed + GRAVITY
        if (y > height - bitmap!!.height) y = height - bitmap!!.height
        if (y < 0) y = 0

        super.update()
    }


}