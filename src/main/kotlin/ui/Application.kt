package org.example.ui

import data.model.InputDto
import domain.model.OutputModel
import kotlinx.coroutines.*
import org.example.data.PersonInfoDataStore
import org.example.domain.FetchPersonInfoSortedUseCase
import org.example.domain.mapper.IMapper
import org.example.domain.mapper.PersonInfoMapper
import org.example.repository.PersonInfoRepository

@OptIn(DelicateCoroutinesApi::class)
fun main() {

    val dataStore = PersonInfoDataStore()
    val mapper: IMapper<OutputModel, InputDto> = PersonInfoMapper()
    val repository = PersonInfoRepository(mapper, dataStore)
    val useCase = FetchPersonInfoSortedUseCase(repository)

    safeBlockingLaunch(
        action = {
            val response = useCase.invoke()

            response.adults.forEachIndexed { index, item ->
                println("Adult($index) : ${item.id} - ${item.name} - ${item.uniqueKeys.count()}")
            }

            response.children.forEachIndexed { index, item ->
                println("Children($index) : ${item.id} - ${item.name} - ${item.uniqueKeys.count()}")
            }
        },
        onError = {
            println("Ошибка поймана $it")
        }
    )

}

@OptIn(DelicateCoroutinesApi::class)
private fun safeBlockingLaunch(
    action: suspend () -> Unit,
    onError: (Throwable) -> Unit,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    val exceptionHandler = CoroutineExceptionHandler { _, t ->
        onError(t)
    }

    runBlocking {
        val job = GlobalScope.launch(exceptionHandler + dispatcher) {
            launch {
                action()
            }
        }
        job.join()
    }
}

