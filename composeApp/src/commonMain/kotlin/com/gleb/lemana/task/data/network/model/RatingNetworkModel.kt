package com.gleb.lemana.task.data.network.model

import kotlinx.serialization.Serializable

@Serializable
class RatingNetworkModel(
    val rate: Float,
    val count: Int
)
