package com.imamsutono.footballmatchschedule.detail

import com.imamsutono.footballmatchschedule.model.MatchDetail
import com.imamsutono.footballmatchschedule.model.MatchDetailData
import com.imamsutono.footballmatchschedule.service.ServiceGenerator
import com.imamsutono.footballmatchschedule.service.ServiceInterface
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenterTest {

    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var call: Call<MatchDetail>

    @Mock
    private lateinit var config: ServiceInterface

    private lateinit var presenter: DetailPresenter
    private val id = "441613"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = DetailPresenter(view)
        config = ServiceGenerator.createBase().create(ServiceInterface::class.java)
        call = config.getMatchDetail(id)
    }

    @Test
    fun testGetMatchDetail() {
        val list: List<MatchDetailData> = listOf()

        // execute get match detail to request data from API
        presenter.getMatchDetail(id)

        // verify that showLoading method is called
        verify(view).showLoading()

        call.enqueue(object: Callback<MatchDetail> {
            override fun onFailure(call: Call<MatchDetail>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchDetail>, response: Response<MatchDetail>) {
                // verify that showMatchDetail method is called
                verify(view).showMatchDetail(list)
                // verify that hideLoading method is called
                verify(view).hideLoading()
            }
        })
    }
}