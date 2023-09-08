package com.example.e_arsivportal.presentation.login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.domain.model.LoginModel
import com.example.e_arsivportal.domain.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    val liveData = MutableLiveData<String>()

    fun login(username : String, password : String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
            println(throwable)

        }

        viewModelScope.launch(handler) {
            val response = repository.login(LoginModel(username,password))

            println(response)

            if(response.isSuccessful) {
                response.body()?.let {

                    println(it)
                    liveData.value = it
                }
            }
        }
    }
}