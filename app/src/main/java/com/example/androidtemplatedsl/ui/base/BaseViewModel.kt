package com.anzu.evepatient.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * ༼-つ-◕_◕-༽つ
 * @author Rohan Pawar on 14/02/2020.
 */
open class BaseViewModel : ViewModel(){
    val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}