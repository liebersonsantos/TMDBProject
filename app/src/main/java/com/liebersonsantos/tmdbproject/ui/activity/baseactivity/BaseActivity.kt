package com.liebersonsantos.tmdbproject.ui.activity.baseactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, title: Int, showBackButton: Boolean = false){
        toolbar.title = getString(title)
        setSupportActionBar(toolbar)

        if (showBackButton){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }
}