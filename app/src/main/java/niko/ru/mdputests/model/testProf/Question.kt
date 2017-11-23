package niko.ru.mdputests.model.testProf

import com.google.gson.annotations.SerializedName

data class Question(

		@field:SerializedName("answers")
	val answers: List<Answer>? = null,

		@field:SerializedName("desc")
	val desc: String? = null
)