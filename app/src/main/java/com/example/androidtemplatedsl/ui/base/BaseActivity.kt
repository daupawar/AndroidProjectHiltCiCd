package com.example.androidtemplatedsl.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.Disposable


/**
 * ༼-つ-◕_◕-༽つ
 * @author Rohan Pawar on 14/02/2020.
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    private var disposable: Disposable? = null

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int


    var bindView: T? = null
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        setupListener()
        setupObserver()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun enableBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun performDataBinding() {
        bindView = DataBindingUtil.setContentView(this, layoutId)
        bindView?.executePendingBindings()
    }


    open fun setupObserver() {}

    open fun setupListener() {}

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
