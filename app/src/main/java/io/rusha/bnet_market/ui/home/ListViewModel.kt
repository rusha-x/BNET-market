package io.rusha.bnet_market.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListViewModel : ViewModel() {
    private val mainApi = ServiceLocator.getMainApi()
    private val compositeDisposable = CompositeDisposable()
    val appsLiveData = MutableLiveData<List<App>>()
    val isRetryShowedLiveEvent = SingleLiveEvent<Unit>()
    val isActionShowedLiveEvent = SingleLiveEvent<App>()

    init {
        loadApps()
    }

    fun onRetryClick() {
        loadApps()
    }

    fun onAppClick(app: App) {
        isActionShowedLiveEvent.value = app
    }

    fun onDownloadAppClick(app: App) {
        // скачать файл приложения
    }

    private fun loadApps() {
        compositeDisposable.add(
            mainApi.getApps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { apps ->
                        appsLiveData.value = apps
                    },
                    { e ->
                        Log.e("ListViewModel", Log.getStackTraceString(e))
                        isRetryShowedLiveEvent.call()
                    }
                )
        )
    }
}