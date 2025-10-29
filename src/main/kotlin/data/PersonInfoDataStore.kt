package org.example.data

import data.model.InputDto
import data.model.PersonInfoDto

private const val LIST_SIZE = 10_000

/**
 * DataStore - для получение мокк данных
 */
class PersonInfoDataStore {

    fun fetchPersonInfo(): InputDto {
        return InputDto(
                id = 123,
                adults = List(LIST_SIZE) { index ->
                    PersonInfoDto(
                        id = index,
                        name = "name_$index"
                    )
                },
                children = List(LIST_SIZE) { index ->
                    PersonInfoDto(
                        id = index,
                        name = "name_$index"
                    )
                },
            )

    }

}