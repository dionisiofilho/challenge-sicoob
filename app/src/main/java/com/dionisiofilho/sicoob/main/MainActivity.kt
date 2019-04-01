package com.dionisiofilho.sicoob.main

import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.adapters.SlideAdapter
import com.dionisiofilho.sicoob.application.bases.BaseActivity
import java.util.*

class MainActivity : BaseActivity() {

    private lateinit var viewPagerMain: ViewPager


    private val slideAdapter: SlideAdapter by lazy {
        SlideAdapter(supportFragmentManager)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initViews()
        convigureViewPager()
    }

    private fun convigureViewPager() {

        val urlImage = "http://api-hom.contactcloud.dionisiofilho.com/images/icon_new.png"

        slideAdapter.addUrl(urlImage)
        slideAdapter.addUrl(urlImage)
        slideAdapter.addUrl(urlImage)
        slideAdapter.addUrl(urlImage)
        slideAdapter.addUrl(urlImage)

        viewPagerMain.adapter = slideAdapter

        var currentPage = 0
        val handler = Handler()

        val runnable = Runnable {
            if (currentPage == slideAdapter.count) {
                currentPage = 0
            }
            viewPagerMain.setCurrentItem(currentPage++, true)
        }

        Timer().apply {
            schedule(object : TimerTask() {
                override fun run() {
                    handler.post(runnable)
                }

            }, 3000, 3000)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    private fun initViews() {
        viewPagerMain = findViewById(R.id.vp_slide)
    }
}
