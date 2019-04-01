package com.dionisiofilho.sicoob.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.view.MenuItem
import android.widget.FrameLayout
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.application.bases.BaseActivity
import com.dionisiofilho.sicoob.application.bases.BaseFragment
import com.dionisiofilho.sicoob.main.fragments.HomeFragment


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var frameLayout: FrameLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var homeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initFragments()
        startFragment(homeFragment)
    }

    private fun initFragments() {
        homeFragment = HomeFragment()
    }

    private fun startFragment(fragment: BaseFragment) {
        if (!fragment.isVisible) {
            supportFragmentManager.beginTransaction().apply {
                replace(frameLayout.id, fragment, fragment::class.java.simpleName)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                commit()
            }
        }
    }


    private fun initViews() {
        frameLayout = findViewById(R.id.fl_main)
        bottomNavigationView = findViewById(R.id.bnv_main)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId) {

            R.id.act_home -> {
                startFragment(homeFragment)
                return true
            }

        }

        return false

    }
}
