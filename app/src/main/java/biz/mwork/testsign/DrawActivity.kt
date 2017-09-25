package biz.mwork.testsign

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_draw.*

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

    }
}
