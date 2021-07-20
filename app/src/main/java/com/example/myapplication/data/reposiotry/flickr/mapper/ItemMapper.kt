package com.example.myapplication.data.reposiotry.flickr.mapper

import com.example.myapplication.data.reposiotry.flickr.UpstoxResponseEntity
import com.example.myapplication.domain.entity.Upstox
import java.util.*

object ItemMapper {
    fun transform(response: UpstoxResponseEntity): List<Upstox> {
        val upstoxList = ArrayList<Upstox>()

        for (entity in response.upstoxEntityList) {
            upstoxList.add(Upstox(entity.avgPrice,
                    entity.cncUsedQuantity,
                    entity.companyName,
                    entity.quantity,
                    entity.ltp))
        }
        return upstoxList;
    }
}