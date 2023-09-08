package com.example.e_arsivportal.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.e_arsivportal.domain.model.Token
import com.example.e_arsivportal.MainActivity
import com.example.e_arsivportal.databinding.ActivityLoginBinding
import com.example.e_arsivportal.data.repository.Repository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: Repository

    private lateinit var binding: ActivityLoginBinding


    private lateinit var viewModel: LoginViewModel

    private lateinit var username : String
    private lateinit var password: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        init()
        autoLogin()

        onClick()

        observeLiveData()

        intent = Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(intent)
    }

    private fun init() {

        username = repository.getUsername().toString()
        password = repository.getPassword().toString()

        binding.loginPageUsernameTextInputLayout.editText?.setText(username)
        binding.loginPagePasswordTextInputLayout.editText?.setText(password)


    }

    private fun autoLogin() {

        viewModel.login(username,password)


    }

    private fun onClick() {

        binding.loginPageLoginButton.setOnClickListener() {

            viewModel.login(binding.loginPageUsernameTextInputLayout.editText?.text.toString(),binding.loginPagePasswordTextInputLayout.editText?.text.toString())

        }

    }

    private fun observeLiveData() {

        viewModel.liveData.observe(this, Observer {

            Token.setValue(it)
            println(it)

            if (!Token.getValue().isNullOrEmpty()) {

                val username = binding.loginPageUsernameTextInputLayout.editText?.text.toString()
                val password = binding.loginPagePasswordTextInputLayout.editText?.text.toString()
                repository.saveUser(username, password)

                intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)

            }
        })
    }
}