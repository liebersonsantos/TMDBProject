package com.liebersonsantos.tmdbproject.ui.activity.profileactivity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.liebersonsantos.tmdbproject.R
import com.liebersonsantos.tmdbproject.data.utils.SharedPreference
import com.liebersonsantos.tmdbproject.ui.activity.baseactivity.BaseActivity
import com.liebersonsantos.tmdbproject.ui.activity.login.LoginActivity
import com.liebersonsantos.tmdbproject.ui.activity.profileactivity.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.toolbar.*

class ProfileActivity : BaseActivity() {

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setupToolbar(toolbarMovie, R.string.txt_perfil, true)

        val sharedPreference = SharedPreference(this)
        sharedPreference.getData("USER")?.let { email ->
            viewModel.getUserByEmail(email).observe(this, Observer { user ->
                user?.let {
                    txtNameProfile.text = "Nome: ${it.name}"
                    txtEmailProfile.text = "Email: ${it.email}"
                    txtPasswordProfile.text = "Senha: ${it.password}"
                    txtPhoneProfile.text = "Phone: ${it.phone}"
                }
            })
        }

        buttonLogout.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
        }
    }

}