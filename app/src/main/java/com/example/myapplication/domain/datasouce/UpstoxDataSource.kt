package com.example.myapplication.domain.datasouce

import com.example.myapplication.domain.entity.Upstox
import io.reactivex.Single

interface UpstoxDataSource {
    fun getUpstoxItems() : Single<List<Upstox>>
}