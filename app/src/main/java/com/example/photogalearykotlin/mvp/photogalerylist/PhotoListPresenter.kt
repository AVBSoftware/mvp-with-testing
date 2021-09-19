package com.example.photogalearykotlin.mvp.photogalerylist

import com.example.photogalearykotlin.mvp.photogalerylist.PhotoListContract.Presenter
import com.example.photogalearykotlin.mvp.photogalerylist.PhotoListContract.Model.OnFinishedListener
import com.example.photogalearykotlin.model.album

class PhotoListPresenter     //photoListModel = new PhotoListModel();
    (
    private var photoListView: PhotoListContract.View?,
    private val photoListModel: PhotoListContract.Model?
) : Presenter, OnFinishedListener {
    override fun onDestroy() {
        photoListView = null
    }

    override fun requestDataFromServer() {
        if (photoListView != null) {
            photoListView!!.showProgress()
        }
        photoListModel?.getPhotoList(this)
    }

    override fun onFinished(photoArrayList: List<album?>?) {
        photoListView!!.setDataToRecyclerView(photoArrayList)
        if (photoListView != null) {
            photoListView!!.hideProgress()
        }
    }

    override fun onFailure(t: Throwable?) {
        photoListView!!.onResponseFailure(t)
        if (photoListView != null) {
            photoListView!!.hideProgress()
        }
    }
}