package com.example.photogalearykotlin.mvp.photogalerylist

import android.util.Log
import com.example.photogalearykotlin.model.album
import com.example.photogalearykotlin.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.photogalearykotlin.network.ApiInterface

class PhotoListModel : PhotoListContract.Model {
    private val TAG = "PhotoListModel"
    override fun getPhotoList(onFinishedListener: PhotoListContract.Model.OnFinishedListener?) {
        val apiService: ApiInterface? = ApiClient.getPhotoListView?.create(ApiInterface::class.java)
        val call: Call<List<album?>?>? =
            apiService?.getPhotosList("Client-ID mLzkVFH8j-VS2FWmDcxmztCrtjegBxB--bcw4ZOa8gc")
        call?.enqueue(object : Callback<List<album?>?> {

            override fun onFailure(call: Call<List<album?>?>?, t: Throwable) {
                // Log error here since request failed
                Log.e(TAG, t.toString())
                onFinishedListener?.onFailure(t)
            }

            override fun onResponse(call: Call<List<album?>?>, response: Response<List<album?>?>) {

                val photos: List<album?>? = response.body()!!

                onFinishedListener?.onFinished(photos)
            }
        })
    }
}