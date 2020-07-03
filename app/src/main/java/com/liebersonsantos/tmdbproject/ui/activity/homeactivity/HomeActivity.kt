package com.liebersonsantos.tmdbproject.ui.activity.homeactivity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.liebersonsantos.tmdbproject.R
import com.liebersonsantos.tmdbproject.ui.activity.baseactivity.BaseActivity
import com.liebersonsantos.tmdbproject.ui.activity.favoriteactivity.FavoriteActivity
import com.liebersonsantos.tmdbproject.ui.activity.profileactivity.ProfileActivity
import com.liebersonsantos.tmdbproject.ui.activity.searchactivity.SearchActivity
import com.liebersonsantos.tmdbproject.ui.pageadapter.HomePagerAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : BaseActivity() {

    private val fragmentAdapter =
        HomePagerAdapter(supportFragmentManager)

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_profile -> {
                startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_search -> {
                startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_favorite -> {
                startActivity(Intent(this@HomeActivity, FavoriteActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupToolbar(toolbarMovie, R.string.app_name, false)

        viewPagerMain.adapter = fragmentAdapter
        tabsMain.setupWithViewPager(viewPagerMain)

        val bottomNavigation: BottomNavigationView = bottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
