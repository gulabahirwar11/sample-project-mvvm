package com.example.myapplication.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Upstox(val avgPrice: Float,
                  val cncUsedQuantity: String,
                  val companyName: String,
                  val quantity: Float,
                  val ltp: Float)