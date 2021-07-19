package com.example.myapplication.flickr.feature.flickr

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.di.DaggerAppComponent
import com.example.myapplication.domain.factory.ViewmodelFactory
import com.example.myapplication.domain.datasouce.FlickrDataSource
import com.example.myapplication.domain.entity.FlickrData
import com.example.myapplication.domain.events.EventType
import com.example.myapplication.flickr.feature.flickr.adapter.FlickrRecyclerAdapter
import com.example.myapplication.flickr.feature.flickr.viewmodel.FlickrViewmodel
import kotlinx.android.synthetic.main.flickr_fragment.*
import javax.inject.Inject

class FlickrPhotoFragment : Fragment() {
    @Inject
    lateinit var flickrDataSource: FlickrDataSource
    private lateinit var flickrViewmodel: FlickrViewmodel

    private val flickrAdapter = FlickrRecyclerAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder().build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flickrViewmodel = ViewModelProvider(this, ViewmodelFactory(flickrDataSource))
                .get(FlickrViewmodel::class.java)

        if (flickrViewmodel.flickrList != null) {
            flickrAdapter.flickrList = flickrViewmodel.flickrList ?: ArrayList()
            flickrViewmodel.flickrList = null // reset saved flickrlist in viewmodel
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.flickr_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (flickrViewmodel.savedRecyclerLayoutState != null) {
            displayData()
        } else {
            initView()
        }

        observeUIEvents()

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && flickrViewmodel.page <= flickrViewmodel.totalPages) {
                    flickrViewmodel.page += 1
                    flickrViewmodel.getFetchItems(flickrViewmodel.page)
                }
            }
        })
        swipe_refresh.setOnRefreshListener {
            flickrViewmodel.page = 1
            observeUIEvents()
            flickrViewmodel.getFetchItems(flickrViewmodel.page)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        flickrViewmodel.flickrList = flickrAdapter.flickrList
        flickrViewmodel.savedRecyclerLayoutState = recycler_view.layoutManager?.onSaveInstanceState()
    }

    private fun initView() {
        recycler_view.adapter = flickrAdapter
        flickrViewmodel.getFetchItems(flickrViewmodel.page)
    }

    private fun displayData() {
        val savedRecyclerLayoutState = flickrViewmodel.savedRecyclerLayoutState

        if (savedRecyclerLayoutState != null) {
            (recycler_view.layoutManager as LinearLayoutManager).onRestoreInstanceState(savedRecyclerLayoutState)
            flickrViewmodel.savedRecyclerLayoutState = null // reset stored recycler state
        }
        recycler_view.adapter = flickrAdapter
    }

    private fun observeUIEvents() {
        flickrViewmodel.eventBus.observe(this, Observer {
            swipe_refresh.isRefreshing = false
            when (it.eventIfNotHandled ?: return@Observer) {
                EventType.SUCCESS -> {
                    val data = it.dataIfNotHandled as FlickrData
                    flickrViewmodel.totalPages = data.pages
                    flickrAdapter.setItems(data.arrayList)
                }
                EventType.LOAD_MORE -> {
                    val data = it.dataIfNotHandled as FlickrData
                    flickrViewmodel.totalPages = data.pages
                    flickrAdapter.addItems(data.arrayList)
                }
                EventType.ERROR -> {
                    Toast.makeText(context,
                            getString(R.string.default_error_message), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}