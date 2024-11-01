package com.gleb.lemana.task.data.network.model

import com.gleb.lemana.task.domain.model.ProductDomainModel
import kotlinx.serialization.Serializable

@Serializable
class ProductNetworkModel(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingNetworkModel
)

fun ProductNetworkModel.asDomainModel(): ProductDomainModel =
    ProductDomainModel(
        id = id,
        title = title,
        description = description.split(".").first(),
        image = image,
        price = price.toString(),
        rating = rating.rate.toString()
    )

fun List<ProductNetworkModel>.asDomainModels(): List<ProductDomainModel> =
    this.map { networkModel -> networkModel.asDomainModel() }
