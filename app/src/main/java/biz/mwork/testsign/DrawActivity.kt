package biz.mwork.testsign

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_draw.*
import java.io.ByteArrayOutputStream

class DrawActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)

        toolBar.navigationIcon = getDrawable(R.drawable.ic_close_black_24dp)
        toolBar.setNavigationOnClickListener {
            finish()
        }

        clearButton.setOnClickListener {
            drawView.clearBitmap()
        }

        saveButton.setOnClickListener {
            val bitmap = drawView.getBitmap()
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.WEBP, 80, stream)
            val byteArray = stream.toByteArray()

            val intent = Intent()
            intent.putExtra(key, byteArray)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    companion object {
        val drawCode = 1
        val key = "draw"
    }


}
