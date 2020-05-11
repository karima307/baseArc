package com.nmg.baseinfrastructure.base


import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nmg.baseinfrastructure.R
import com.nmg.baseinfrastructure.data.remote.Resource
import com.nmg.baseinfrastructure.databinding.ListItemsWithoutRefreshBinding

abstract class ListFrafmentWithoutRefresh<D : ViewDataBinding> : BaseFragment<D>() {
        override val layoutRes: Int
            get() = R.layout.list_items_without_refresh

        override fun initUI(savedInstanceState: Bundle?) {
            setupList()
        }

        fun setupList() {
            setupRecyclarView()
            getRecyclarView()?.adapter = listAdapter
        }


        open fun setupRecyclarView() {
            getRecyclarView()?.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        }

        open fun getRecyclarView(): RecyclerView?  = if(binding is ListItemsWithoutRefreshBinding) (binding as ListItemsWithoutRefreshBinding).rvList else null

        abstract val  listAdapter : BaseRVAdapter< *  , *>?

        open fun updateData(data: Resource<List<*>>?) {
            if (data == null || getRecyclarView() == null) return
            (getRecyclarView()?.adapter as BaseRVAdapter< *  , *>).submitData(data)
        }


}