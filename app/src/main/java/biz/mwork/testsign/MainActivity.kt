package biz.mwork.testsign

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val cFragment = SignFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.Fragment, cFragment, "SignFragment").commit()*/

        button.setOnClickListener {
            startActivity<DrawActivity>()
        }
    }
}
