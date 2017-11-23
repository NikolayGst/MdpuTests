package niko.ru.mdputests.model.testProf

import com.google.gson.annotations.SerializedName

data class Answer(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("point")
	val point: Int? = null
)