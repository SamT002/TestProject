package data.model

data class InputDto(
    val id: Int?,
    val adults: List<PersonInfoDto?>?,
    val children: List<PersonInfoDto?>?,
)