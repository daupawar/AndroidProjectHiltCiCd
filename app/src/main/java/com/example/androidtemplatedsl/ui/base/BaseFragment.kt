package com.anzu.evepatient.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    abstract val layoutId: Int


    var bindView: T? = null
        private set


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindView = DataBindingUtil.inflate(inflater, layoutId, container, false)
        setupListener()
        setupObserver()
        return bindView?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView?.executePendingBindings()
    }


    protected fun setTitle(res: Int) {
        (activity as AppCompatActivity).supportActionBar?.setTitle(res)
    }

    protected fun setTitle(res: String) {
        (activity as AppCompatActivity).supportActionBar?.title = res
    }

    open fun setupObserver(){}

    open fun setupListener(){}

}
