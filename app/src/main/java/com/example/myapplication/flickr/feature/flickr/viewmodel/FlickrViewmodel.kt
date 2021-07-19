package com.example.myapplication.flickr.feature.flickr.viewmodel

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.events.Event
import com.example.myapplication.domain.events.EventType
import com.example.myapplication.domain.datasouce.FlickrDataSource
import com.example.myapplication.domain.entity.Flickr
import com.example.myapplication.domain.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable

class FlickrViewmodel(private val dataSource: FlickrDataSource) : ViewModel() {
    var totalPages = 0
    var savedRecyclerLayoutState: Parcelable? = null
    var flickrList: ArrayList<Flickr>? = null
    var page = 1

    private val disposables = CompositeDisposable()
    val eventBus = MutableLiveData<Event<EventType, Any>>()

    fun getFetchItems(page: Int) {
        val observable = if (page > 1) {
            dataSource.getFetchFlickrItems(page)
        } else dataSource.getKamaterahoItems()

        val disposable = observable
                .compose(RxUtil.applySingleSchedulers())
                .subscribe({
                    if (page > 1) {
                        eventBus.postValue(Event(EventType.LOAD_MORE, it))
                    } else {
                        eventBus.postValue(Event(EventType.SUCCESS, it))
                    }
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