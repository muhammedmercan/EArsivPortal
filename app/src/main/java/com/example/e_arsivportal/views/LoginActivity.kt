package com.example.e_arsivportal.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.biochakraastralterapi.utilities.CustomSharedPreferences
import com.example.biochakraastralterapi.utilities.Token
import com.example.e_arsivportal.MainActivity
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityIncomingInvoicesBinding
import com.example.e_arsivportal.databinding.ActivityLoginBinding
import com.example.e_arsivportal.databinding.ActivityNewInvoiceAddProductBinding
import com.example.e_arsivportal.models.LoginModel
import com.example.e_arsivportal.service.Database
import com.example.e_arsivportal.viewmodels.IncomingInvoicesViewModel
import com.example.e_arsivportal.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityLoginBinding
    private val binding get() = _binding!!



    private lateinit var viewModel: LoginViewModel

    private lateinit var username : String
    private lateinit var password: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        init()
        autoLogin()

        onClick()

        observeLiveData()
    }

    private fun init() {

        username = CustomSharedPreferences.invoke(this).getUsername().toString()
        password = CustomSharedPreferences.invoke(this).getPassword().toString()

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

                CustomSharedPreferences.invoke(this).saveUser(binding.loginPageUsernameTextInputLayout.editText?.text.toString(),binding.loginPagePasswordTextInputLayout.editText?.text.toString())

                intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)

            }
        })
    }
}