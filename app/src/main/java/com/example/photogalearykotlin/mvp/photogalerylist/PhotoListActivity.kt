package com.example.photogalearykotlin.mvp.photogalerylist

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.photogalearykotlin.model.album
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.photogalearykotlin.R
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import java.util.ArrayList

class PhotoListActivity : AppCompatActivity(), PhotoListContract.View, ShowEmptyView {
    private var photoListPresenter: PhotoListPresenter? = null
    private lateinit var rvPhotoList: RecyclerView
    private var photosList: MutableList<album?>? = null
    private var photosAdapter: PhotosAdapter? = null
    private var pbLoading: ProgressBar? = null
    private var tvEmptyView: TextView? = null
    private val mLayoutManager: GridLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)
        supportActionBar!!.title = getString(R.string.most_popular_photos)
        initUI()
        photoListPresenter = PhotoListPresenter(this, PhotoListModel())
        photoListPresenter!!.requestDataFromServer()
    }

    private fun initUI() {
        rvPhotoList = findViewById(R.id.rv_photo_list)
        photosList = ArrayList()
        photosAdapter = PhotosAdapter(this, photosList)

        //mLayoutManager = new GridLayoutManager(this, 2);
        //rvPhotoList.setLayoutManager(mLayoutManager);
        rvPhotoList.setHasFixedSize(true)
        rvPhotoList.setLayoutManager(LinearLayoutManager(this))
        rvPhotoList.setAdapter(photosAdapter)
        pbLoading = findViewById(R.id.pb_loading)
        tvEmptyView = findViewById(R.id.tv_empty_view)
    }

    override fun showProgress() {
        pbLoading!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbLoading!!.visibility = View.GONE
    }

    override fun setDataToRecyclerView(photoArrayList: List<album?>?) {
        if (photoArrayList != null) {
            photosList!!.addAll(photoArrayList)
            photosAdapter!!.notifyDataSetChanged()
        } else {
            showEmptyView()
        }
    }

    override fun onResponseFailure(throwable: Throwable?) {
        Log.e(TAG, throwable!!.message!!)
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        photoListPresenter!!.onDestroy()
    }

    override fun showEmptyView() {
        rvPhotoList!!.visibility = View.GONE
        tvEmptyView!!.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        rvPhotoList!!.visibility = View.VISIBLE
        tvEmptyView!!.visibility = View.GONE
    }

    companion object {
        private const val TAG = "PhotoListActivity"
    }
}