package org.example.domain.mapper

interface IMapper<O, I> {

    suspend fun toNetwork(item:O) : I

    suspend fun fromNetwork(item: I) : O

}