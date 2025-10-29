package org.example.repository

import data.model.InputDto
import domain.model.OutputModel
import org.example.data.PersonInfoDataStore
import org.example.domain.mapper.IMapper

class PersonInfoRepository(
    private val mapper: IMapper<OutputModel, InputDto>,
    private val dataStore: PersonInfoDataStore
) {

    suspend fun fetchPersonInfo(): OutputModel {

        val response = dataStore.fetchPersonInfo()

        return mapper.fromNetwork(response)
    }

}