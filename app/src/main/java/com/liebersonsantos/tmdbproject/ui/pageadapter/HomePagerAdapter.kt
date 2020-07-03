package com.liebersonsantos.tmdbproject.ui.pageadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.liebersonsantos.tmdbproject.ui.fragment.action.ActionFragment
import com.liebersonsantos.tmdbproject.ui.fragment.adventurefragment.AdventureFragment
import com.liebersonsantos.tmdbproject.ui.fragment.animationfragment.AnimationFragment
import com.liebersonsantos.tmdbproject.ui.fragment.comedyfragment.ComedyFragment

class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ActionFragment()
            1 -> AdventureFragment()
            2 -> AnimationFragment()
            3 -> ComedyFragment()
           else -> ActionFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Ação"
            1 -> "Aventura"
            2 -> "Animação"
            3 -> "Comédia"
            else -> null
        }
    }
}