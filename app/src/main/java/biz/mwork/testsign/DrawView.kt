package biz.mwork.testsign

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


/**
 * DrawView use in signature
 *
 * @author Thanawat Hanthong
 * @since 21/9/2017
 *
 */


class DrawView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    lateinit private var mBitmap: Bitmap
    lateinit private var mCanvas: Canvas

    private var mPaint: Paint = Paint()
    private var mPath: Path = Path()
    private var mBitmapPaint: Paint = Paint(Paint.DITHER_FLAG)
    private var circlePaint: Paint = Paint()
    private var circlePath: Path = Path()

    private var mX: Float = 0f
    private var mY: Float = 0f
    private val TOLERANCE = 4f

    //Attributes
    var strokeColor: Int = Color.RED
        set(value) {
            field = value
            mPaint.color = field
        }
    var strokeWidth: Float = 2f
        set(value) {
            field = value
            mPaint.strokeWidth = field
        }
    var circleRadius: Float = 10f
    var circleStrokeWidth: Float = 2f
        set(value) {
            field = value
            circlePaint.strokeWidth = field
        }

    constructor(context: Context) : this(context, null)

    init {
        getAttributes(attrs)
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.color = strokeColor
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = strokeWidth

        circlePaint.isAntiAlias = true
        circlePaint.color = Color.BLACK
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeJoin = Paint.Join.MITER
        circlePaint.strokeWidth = circleStrokeWidth
    }

    private fun getAttributes(attributeSet: AttributeSet?) {
        attributeSet?.let {
            val typedArray = context.theme.obtainStyledAttributes(
                    it,
                    R.styleable.DrawView,
                    0, 0)
            try {
                strokeColor = typedArray.getColor(R.styleable.DrawView_color, strokeColor)
                strokeWidth = typedArray.getFloat(R.styleable.DrawView_strokeWidth, strokeWidth)
                circleRadius = typedArray.getFloat(R.styleable.DrawView_circleRadius, circleRadius)
                circleStrokeWidth = typedArray.getFloat(R.styleable.DrawView_circleStrokeWidth, circleStrokeWidth)
            } finally {
                typedArray.recycle()
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldW: Int, oldH: Int) {
        super.onSizeChanged(w, h, oldW, oldH)
        createBitmap(w, h)
    }

    private fun createBitmap(w: Int, h: Int) {
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(mBitmap, 0f, 0f, mBitmapPaint)
        canvas?.drawPath(mPath, mPaint)
        canvas?.drawPath(circlePath, circlePaint)
    }

    private fun touchStart(x: Float, y: Float) {
        mPath.reset()
        mPath.moveTo(x, y)
        mX = x
        mY = y
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - mX)
        val dy = Math.abs(y - mY)
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y

            //remove circle
            circlePath.reset()
            //set circle size and position
            circlePath.addCircle(mX, mY, circleRadius, Path.Direction.CW)
        }
    }

    private fun touchUp() {
        mPath.lineTo(mX, mY)
        circlePath.reset()
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint)
        // kill this so we don't double draw
        mPath.reset()
    }

    /**
     * Clear bitmap data in [mBitmap]
     */
    fun clearBitmap() {
        val size = Pair(mBitmap.width, mBitmap.height)
        mBitmap.recycle()
        createBitmap(size.first, size.second)
    }

    /**
     * get Bitmap data
     */
    fun getBitmap(): Bitmap = mBitmap


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        event?.let {
            val x = it.x
            val y = it.y

            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    touchStart(x, y)
                    invalidate()
                }
                MotionEvent.ACTION_MOVE -> {
                    touchMove(x, y)
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    touchUp()
                    invalidate()
                }
            }
        }
        return true
    }
}
