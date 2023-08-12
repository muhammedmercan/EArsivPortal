package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.biochakraastralterapi.utilities.CustomSharedPreferences
import com.example.e_arsivportal.repo.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {


    fun exit() {

        repository.deleteUser()

    }
}