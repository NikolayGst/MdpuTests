package niko.ru.mdputests


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_result_test.*


/**
 * A simple [Fragment] subclass.
 * Use the [ResultTestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultTestFragment : Fragment() {

    private var text: String? = null
    private var countPoint: Int? = null

    companion object {

        private val TEXT = "text"
        private val COUNT_POINT = "countPoint"

        fun newInstance(text: String, countPoint: Int): ResultTestFragment {
            val fragment = ResultTestFragment()
            val args = Bundle()
            args.putString(TEXT, text)
            args.putInt(COUNT_POINT, countPoint)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            text = arguments.getString(TEXT)
            countPoint = arguments.getInt(COUNT_POINT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_test, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtResultText.text = text
        txtCountPoint.text = countPoint.toString()
    }


}
