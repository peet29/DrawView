package biz.mwork.testsign

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val cFragment = SignFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.Fragment, cFragment, "SignFragment").commit()*/

        button.setOnClickListener {
            val intent = Intent(this, DrawActivity::class.java)
            startActivityForResult(intent, DrawActivity.drawCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                DrawActivity.drawCode -> {
                    val byteArray = data.getByteArrayExtra(DrawActivity.key)
                    val bitMap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                    imageView.setImageBitmap(bitMap)
                }
            }
        }
    }
}
