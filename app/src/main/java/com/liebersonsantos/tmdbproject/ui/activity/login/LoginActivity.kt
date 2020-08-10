package com.liebersonsantos.tmdbproject.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.liebersonsantos.tmdbproject.R
import com.liebersonsantos.tmdbproject.data.utils.SharedPreference
import com.liebersonsantos.tmdbproject.ui.activity.baseactivity.BaseActivity
import com.liebersonsantos.tmdbproject.ui.activity.homeactivity.HomeActivity
import com.liebersonsantos.tmdbproject.ui.activity.login.viewmodel.LoginViewModel
import com.liebersonsantos.tmdbproject.ui.activity.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*

class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)
        setupToolbar(toolbarMovie, R.string.empty, false)

        val sharedPreference = SharedPreference(this)
        sharedPreference.getData("USER")?.let { email ->
            edtEmailLogin.setText(email)
        }
        createAccount()

        login(sharedPreference)
    }

    private fun login(sharedPreference: SharedPreference) {
        buttonLogin.setOnClickListener {
            when (viewModel.isValid(edtEmailLogin, edtPasswordLogin)) {
                0 -> Toast.makeText(this@LoginActivity, "Preencha todos os campos para acessar o aplicativo", Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(this@LoginActivity, "Preencha o campo senha", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this@LoginActivity, "Preencha corretamente o campo email", Toast.LENGTH_SHORT).show()
                else -> {
                    viewModel.getUserDB(edtEmailLogin.text.toString(), edtPasswordLogin.text.toString()).observe(this, Observer { user ->
                        user?.let { user ->
                            sharedPreference.saveData("USER", user.email)
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                        } ?: run {
                            Toast.makeText(
                                this@LoginActivity,
                                "Email ou senha inválido",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }
            }
        }
    }

    private fun createAccount() {
        txtRegisterNow.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

