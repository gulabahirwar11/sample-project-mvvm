package com.example.myapplication.flickr.feature.flickr.viewmodel

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.events.Event
import com.example.myapplication.domain.events.EventType
import com.example.myapplication.domain.datasouce.UpstoxDataSource
import com.example.myapplication.domain.entity.Upstox
import com.example.myapplication.domain.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable

class UpstoxViewmodel(private val dataSource: UpstoxDataSource) : ViewModel() {
    var savedRecyclerLayoutState: Parcelable? = null
    var upstoxList: ArrayList<Upstox>? = null

    private val disposables = CompositeDisposable()
    val eventBus = MutableLiveData<Event<EventType, Any>>()

    fun getFetchItems() {
        val disposable = dataSource.getUpstoxItems()
                .compose(RxUtil.applySingleSchedulers())
                .subscribe({
                    eventBus.postValue(Event(EventType.SUCCESS, it))
                }, {
                    eventBus.postValue(Event(EventType.ERROR))
                })
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}