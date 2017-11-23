package niko.ru.mdputests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import niko.ru.mdputests.model.testProf.Result

class MainFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testProf.setOnClickListener {

            val fragment = TestProfFragment()

            fragment.setTestFinishedListener(object : TestProfFragment.OnTestFinishedListener {
                override fun onTestFinished(countPoint: Int, result: List<Result>?) {
                    val resultText: String
                    val position: Int
                    when (countPoint) {
                        in 0..12 -> position = 0
                        in 13..24 -> position = 1
                        in 25..36 -> position = 2
                        in 37..48 -> position = 3
                        in 49..60 -> position = 4
                        else -> position = -1
                    }
                    resultText = result?.get(position)?.text ?: ""
                    fragmentManager.popBackStack()
                    showFragment(R.id.container, ResultTestFragment.newInstance(resultText, countPoint))
                }
            })
            showFragment(R.id.container, fragment)
        }
    }

}
