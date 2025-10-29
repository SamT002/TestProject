package org.example.domain

import domain.model.OutputModel
import org.example.repository.PersonInfoRepository

/**
 * Сущность отвечает за получение данных и их сортировку
 */
class FetchPersonInfoSortedUseCase(private val repository: PersonInfoRepository) {

    suspend operator fun invoke(): OutputModel {
        val response =  repository.fetchPersonInfo()

        return response.copy(
            adults = response.adults.sortedBy { it.id },
            children = response.children.sortedBy { it.id }
        )
    }

}