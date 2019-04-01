package com.dionisiofilho.sicoob.adapters

import SlideFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SlideAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val urlImages = arrayListOf<String>()

    override fun getItem(position: Int): Fragment {
        return SlideFragment.newInstance(urlImages[position])
    }

    override fun getCount(): Int {
        return urlImages.size
    }

    fun addUrls(urls: ArrayList<String>) {
        this.urlImages.addAll(urls)
        notifyDataSetChanged()
    }

    fun addUrl(url: String) {
        this.urlImages.add(url)
        notifyDataSetChanged()
    }

}