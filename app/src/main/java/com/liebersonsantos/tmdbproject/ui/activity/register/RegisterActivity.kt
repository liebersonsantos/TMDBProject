package com.liebersonsantos.tmdbproject.ui.activity.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.liebersonsantos.tmdbproject.R
import com.liebersonsantos.tmdbproject.data.database.modeldb.User
import com.liebersonsantos.tmdbproject.ui.activity.baseactivity.BaseActivity
import com.liebersonsantos.tmdbproject.ui.activity.login.LoginActivity
import com.liebersonsantos.tmdbproject.ui.activity.register.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : BaseActivity() {

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupToolbar(toolbarMovie, R.string.register, true)
        register()
    }

    private fun register() {
        buttonRegister.setOnClickListener {
            when (viewModel.isValid(edtRegisterName, edtRegisterEmail, edtRegisterPassword)) {
                0 -> Toast.makeText(this@RegisterActivity, "Preencha todos os campos para se cadastrar no aplicativo", Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(this@RegisterActivity, "Preencha o campo nome", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this@RegisterActivity, "Preencha corretamente o campo email", Toast.LENGTH_SHORT).show()
                3 -> Toast.makeText(this@RegisterActivity, "Preencha o campo senha", Toast.LENGTH_SHORT).show()
                else -> {
                    GlobalScope.launch {
                        viewModel.insertUser(
                            User(
                                edtRegisterEmail.text.toString(),
                                edtRegisterName.text.toString(),
                                edtRegisterPassword.text.toString(),
                                viewModel.verifyPhoneNumber(edtRegisterPhone)
                            )
                        )
                    }
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}
