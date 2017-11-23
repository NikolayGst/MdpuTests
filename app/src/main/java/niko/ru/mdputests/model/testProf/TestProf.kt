package niko.ru.mdputests.model.testProf

import com.google.gson.annotations.SerializedName

data class TestProf(

        @field:SerializedName("test")
        val test: String? = null,

        @field:SerializedName("questions")
        val questions: List<Question>? = null,

        @field:SerializedName("result")
        val result: List<Result>? = null
) {

    fun getQuestion(position: Int): Question {
        return questions?.get(position) ?: Question()
    }
}