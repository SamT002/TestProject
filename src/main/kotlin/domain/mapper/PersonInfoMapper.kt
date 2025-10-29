package org.example.domain.mapper

import data.model.InputDto
import data.model.PersonInfoDto
import domain.model.OutputModel
import domain.model.PersonInfoModel
import org.example.core.DEFAULT_INT_VALUE
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

private const val LIST_SIZE = 10_000

class PersonInfoMapper : IMapper<OutputModel, InputDto> {

    override suspend fun fromNetwork(item: InputDto): OutputModel {
        return OutputModel(
            id = item.id ?: DEFAULT_INT_VALUE,
            adults = item.adults
                ?.asSequence()
                ?.filterNotNull()
                ?.map { fromNetwork(it) }
                .orEmpty()
                .toList(),

            children = item.children
                ?.asSequence()
                ?.filterNotNull()
                ?.map { fromNetwork(it) }
                .orEmpty()
                .toList()
        )
    }

    override suspend fun toNetwork(item: OutputModel): InputDto {
        throw IllegalArgumentException("Mapper not implemented")
    }


    private fun fromNetwork(item: PersonInfoDto): PersonInfoModel {
        return PersonInfoModel(
            id = item.id ?: DEFAULT_INT_VALUE,
            name = item.name.orEmpty(),
            uniqueKeys = generateUniqueKeys(item.id ?: Random.nextInt())
        )
    }

    /**
     * @Warning
     * @suppress По хорошему я бы вынес данную логику в UseCase. Но боюсь я таким образом не буду
     * соблюдать требования ТЗ. Его можно оптимизировать :)
     * */
    private fun generateUniqueKeys(id:Int): List<Double> {
        return List(LIST_SIZE) {
            cos(sin(Random(id).nextDouble()))
        }// не переопределять поле [uniqueKeys]
    }


}