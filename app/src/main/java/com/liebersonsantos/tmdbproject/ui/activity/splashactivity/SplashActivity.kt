package com.liebersonsantos.tmdbproject.ui.activity.splashactivity

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.liebersonsantos.tmdbproject.R

class SplashActivity : AppCompatActivity() {

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        zupflix.animate().apply {
//            duration = 1000
//            rotationYBy(360f)
//        }.withEndAction {
//            zupflix.animate().apply {
//                duration = 1000
//                rotationYBy(3600f)
//            }.withEndAction {
//                startActivity(
//                    Intent(this@SplashActivity, LoginActivity::class.java),
//                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
//                )
//            }.start()
//        }
    }
}
