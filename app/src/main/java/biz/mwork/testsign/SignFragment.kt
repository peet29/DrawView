package biz.mwork.testsign


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class SignFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_sign, container, false)
        /*val drawView = DrawView(context)
        drawView.strokeColor = Color.BLUE
        drawView.circleRadius = 40f
        drawView.strokeWidth = 5f
        drawView.circleStrokeWidth = 5f
        view.MainDraw.addView(drawView)*/
        return view
    }

    companion object {
        fun newInstance(): SignFragment = SignFragment()
    }

}
