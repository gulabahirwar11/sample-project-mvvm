package com.example.myapplication.data.reposiotry.flickr

import com.google.gson.annotations.SerializedName

data class UpstoxResponseEntity(@SerializedName("data") val upstoxEntityList: List<UpstoxEntity>)


data class UpstoxEntity(@SerializedName("avg_price") val avgPrice: Float,
                        @SerializedName("cnc_used_quantity") val cncUsedQuantity: String,
                        @SerializedName("company_name") val companyName: String,
                        @SerializedName("quantity") val quantity: Float,
                        @SerializedName("ltp") val ltp: Float)