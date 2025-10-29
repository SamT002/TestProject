package domain.model

data class PersonInfoModel(
    val id: Int,
    val name: String,
    val uniqueKeys:List<Double> = listOf()
)