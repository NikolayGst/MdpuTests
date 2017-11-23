package niko.ru.mdputests


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_test.*
import niko.ru.mdputests.model.testProf.Result
import niko.ru.mdputests.model.testProf.TestProf

class TestProfFragment : Fragment() {


    interface OnTestFinishedListener {
        fun onTestFinished(countPoint: Int, result: List<Result>? = null)
    }


    private lateinit var testData: String
    private var position = 0
    private var radioButtonId = -1
    private var countPoint = 0
    private val gson = Gson()
    private lateinit var testProf: TestProf
    private var onTestFinishedListener: OnTestFinishedListener? = null

    fun setTestFinishedListener(
            onTestFinishedListener: OnTestFinishedListener) {
        this.onTestFinishedListener = onTestFinishedListener
    }

    companion object {

        private val TEST_DATA = "test_data"

        fun newInstance(testData: String): TestProfFragment {
            val fragment = TestProfFragment()
            val args = Bundle()
            args.putString(TEST_DATA, testData)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            testData = arguments.getString(TEST_DATA)
        } else {
            testData = context.getAssetData("test_prof.json")
        }
        testProf = gson.fromJson<TestProf>(testData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeQuestion()

        btnNext.setOnClickListener {
            if (answers.checkedRadioButtonId != -1) {
                when (answers.checkedRadioButtonId) {
                    R.id.answerOne -> radioButtonId = 0
                    R.id.answerTwo -> radioButtonId = 1
                    R.id.answerThree -> radioButtonId = 2
                }
                countPoint += testProf.getQuestion(position).answers?.get(radioButtonId)?.point ?: 0
                position++
                answers.clearCheck()
                changeQuestion()
            } else {
                context.toast("Пожалуйста, выберите вариант ответа")
            }
        }
    }

    private fun changeQuestion() {
        val size = testProf.questions?.size ?: -1
        if (position >= size && size != -1) {
            onTestFinishedListener?.onTestFinished(countPoint, testProf.result)
        } else {
            val question = testProf.getQuestion(position)
            desc.text = question.desc
            answerOne.text = question.answers?.get(0)?.text ?: ""
            answerTwo.text = question.answers?.get(1)?.text ?: ""
            answerThree.text = question.answers?.get(2)?.text ?: ""
        }
    }

}
