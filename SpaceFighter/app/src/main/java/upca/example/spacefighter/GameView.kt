package upca.example.spacefighter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
class GameView : SurfaceView, Runnable {

    private var isPlaying = false
    private var gameThread : Thread? = null
    lateinit var surfaceHolder : SurfaceHolder
    lateinit var canvas : Canvas
    private var sprites = arrayListOf<Sprite>()

    constructor(context: Context, width : Int, height: Int) : super(context){
        surfaceHolder = holder

        sprites.add(Player(context, width, height))

        for (index in 0..2){
            sprites.add(Enemy(
                context, width, height
            ))
        }

        for (index in 0..99){
            sprites.add(Star(
                context,
                width,
                height
            ))
        }
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun run() {
        while(isPlaying){
            update()
            draw()
            control()
        }
    }

    fun resume(){
        isPlaying = true
        gameThread = Thread(this)
        gameThread!!.start()
    }

    fun pause(){
        isPlaying = false
        gameThread!!.join()
        gameThread = null
    }

    fun update(){
        val player = sprites.filter { s ->
            s is Player
        }.first()

        for ( s in sprites){
            s.update()
            if (s is Enemy){
                if (Rect.intersects(s.collisionBox, player.collisionBox)){
                    s.x = -300
                }
            }
        }
    }

    fun draw(){
        if (surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()

            canvas.drawColor(Color.BLACK)
            for(s in sprites) {
                s.draw(canvas)
            }

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control(){
        Thread.sleep(17)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                for (s in sprites) {
                    if (s is Player){
                        s.isBoosting = true
                    }
                    if (s is Enemy){
                        s.isBoosting = true
                    }
                    if (s is Star){
                        s.isBoosting = true
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                for (s in sprites) {
                    if (s is Player){
                        s.isBoosting = false
                    }
                    if (s is Enemy){
                        s.isBoosting = false
                    }
                    if (s is Star){
                        s.isBoosting = false
                    }
                }
            }
        }
        return true
    }
}