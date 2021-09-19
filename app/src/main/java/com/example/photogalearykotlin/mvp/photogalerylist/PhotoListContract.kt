package com.example.photogalearykotlin.mvp.photogalerylist

import com.example.photogalearykotlin.model.album

interface PhotoListContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(photoArrayList: List<album?>?)
            fun onFailure(t: Throwable?)
        }

        fun getPhotoList(onFinishedListener: OnFinishedListener?)
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(photoArrayList: List<album?>?)
        fun onResponseFailure(throwable: Throwable?)
    }

    interface Presenter {
        fun onDestroy()
        fun requestDataFromServer()
    }
}