package com.example.e_arsivportal.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_arsivportal.models.LoginModel
import com.example.e_arsivportal.repo.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    private var job : Job? = null

    val liveData = MutableLiveData<String>()

    fun login(username : String, password : String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
            println(throwable)

        }

        job = CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = repository.login(LoginModel(username,password))

            println(response)

            withContext(Dispatchers.Main){
                if(response.isSuccessful) {
                    response.body()?.let {

                        println(it)

                        liveData.value = it

                    }
                }
            }
        }
    }
}