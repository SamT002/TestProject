package domain.model

data class OutputModel(
    val id: Int,
    val adults: List<PersonInfoModel>,
    val children: List<PersonInfoModel>,
)