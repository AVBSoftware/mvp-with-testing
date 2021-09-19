package com.example.photogalearykotlin

import com.example.photogalearykotlin.mvp.photogalerylist.PhotoListPresenter
import com.example.photogalearykotlin.mvp.photogalerylist.PhotoListContract.Model.OnFinishedListener
import kotlin.Throws
import com.example.photogalearykotlin.model.album
import com.example.photogalearykotlin.model.UserAccount
import com.example.photogalearykotlin.model.Urlimage
import com.example.photogalearykotlin.mvp.photogalerylist.PhotoListContract
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.*
import java.lang.Exception
import java.util.ArrayList

class PhotoListPresenterTest {
    @Mock
    private val view: PhotoListContract.View? = null

    @Mock
    private val photoRepo: PhotoListContract.Model? = null
    private var presenter: PhotoListPresenter? = null

    @Captor
    private val argumentCaptor: ArgumentCaptor<OnFinishedListener>? = null
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PhotoListPresenter(view, photoRepo)
    }

    @Test
    @Throws(Exception::class)
    fun loadPhotoList() {
        presenter!!.requestDataFromServer()
        Mockito.verify(photoRepo, Mockito.times(1))?.getPhotoList(
            argumentCaptor!!.capture()
        )
        argumentCaptor?.value?.onFinished(list)
        Mockito.verify(view, Mockito.times(1))?.hideProgress()
        val entityArgumentCaptor = ArgumentCaptor.forClass(
            List::class.java
        )
        Mockito.verify(view)?.setDataToRecyclerView(entityArgumentCaptor.capture() as List<album?>?)
        Assert.assertTrue(entityArgumentCaptor.value.size > 0)
    }

    @Test
    @Throws(Exception::class)
    fun loadonFailure() {
        presenter!!.requestDataFromServer()
        Mockito.verify(photoRepo, Mockito.times(1))?.getPhotoList(
            argumentCaptor!!.capture()
        )
        argumentCaptor?.value?.onFailure(Throwable())
        Mockito.verify(view, Mockito.times(1))?.hideProgress()
        val argumentCaptor = ArgumentCaptor.forClass(
            Throwable::class.java
        )
        Mockito.verify(view, Mockito.times(1))?.onResponseFailure(argumentCaptor.capture())
        Mockito.verify(view)?.onResponseFailure(argumentCaptor.value)
    }

    private val list: List<album?>
        private get() {
            val albumsphoto = ArrayList<album?>()
            val albumsusers = ArrayList<UserAccount>()
            val albumsurls = ArrayList<Urlimage>()
            val usera1 = UserAccount()
            usera1.id = "id1x"
            usera1.username = "usera1"
            usera1.name = "andia1"
            val urlimagea1 = Urlimage()
            urlimagea1.full = ""
            urlimagea1.raw = ""
            urlimagea1.regular =
                "https://images.unsplash.com/photo-1593642532454-e138e28a63f4?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNjA4NTN8MXwxfGFsbHwxfHx8fHx8Mnx8MTYzMTk5MjYwMA&ixlib=rb-1.2.1&q=80&w=1080"
            urlimagea1.small = ""
            urlimagea1.thumb = ""
            albumsusers.add(usera1)
            albumsurls.add(urlimagea1)
            val albuma1 = album()
            albuma1.description = "description1"
            albuma1.alt_description = "IT A1"
            albuma1.user = usera1
            albuma1.urls = urlimagea1
            albumsphoto.add(albuma1)
            return albumsphoto
        }
}