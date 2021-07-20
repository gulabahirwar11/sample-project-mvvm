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
import com.example.myapplication.R
import com.example.myapplication.di.DaggerAppComponent
import com.example.myapplication.domain.factory.ViewmodelFactory
import com.example.myapplication.domain.datasouce.UpstoxDataSource
import com.example.myapplication.domain.entity.Upstox
import com.example.myapplication.domain.events.EventType
import com.example.myapplication.flickr.feature.flickr.adapter.UpstoxRecyclerAdapter
import com.example.myapplication.flickr.feature.flickr.viewmodel.UpstoxViewmodel
import kotlinx.android.synthetic.main.upstox_fragment.*
import javax.inject.Inject

class UpstoxFragment : Fragment() {
    @Inject
    lateinit var upstoxDataSource: UpstoxDataSource
    private lateinit var upstoxViewmodel: UpstoxViewmodel

    private val flickrAdapter = UpstoxRecyclerAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder().build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        upstoxViewmodel = ViewModelProvider(this, ViewmodelFactory(upstoxDataSource))
                .get(UpstoxViewmodel::class.java)

        if (upstoxViewmodel.upstoxList != null) {
            flickrAdapter.upstoxList = upstoxViewmodel.upstoxList!!
            upstoxViewmodel.upstoxList = null // reset saved upstox in viewmodel
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.upstox_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (upstoxViewmodel.savedRecyclerLayoutState != null) {
            displayData()
        } else {
            initView()
        }

        observeUIEvents()
        swipe_refresh.setOnRefreshListener {

            observeUIEvents()
            upstoxViewmodel.getFetchItems()
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        upstoxViewmodel.upstoxList = flickrAdapter.upstoxList
        upstoxViewmodel.savedRecyclerLayoutState = recycler_view.layoutManager?.onSaveInstanceState()
    }

    private fun initView() {
        recycler_view.adapter = flickrAdapter
        upstoxViewmodel.getFetchItems()
    }

    private fun displayData() {
        val savedRecyclerLayoutState = upstoxViewmodel.savedRecyclerLayoutState

        if (savedRecyclerLayoutState != null) {
            (recycler_view.layoutManager as LinearLayoutManager).onRestoreInstanceState(savedRecyclerLayoutState)
            upstoxViewmodel.savedRecyclerLayoutState = null // reset stored recycler state
        }
        recycler_view.adapter = flickrAdapter
    }

    private fun observeUIEvents() {
        upstoxViewmodel.eventBus.observe(this, Observer {
            swipe_refresh.isRefreshing = false
            when (it.eventIfNotHandled ?: return@Observer) {
                EventType.SUCCESS -> {
                    val data = it.dataIfNotHandled as ArrayList<Upstox>
                    flickrAdapter.setItems(data)
                }

                EventType.ERROR -> {
                    Toast.makeText(context,
                            getString(R.string.default_error_message), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}