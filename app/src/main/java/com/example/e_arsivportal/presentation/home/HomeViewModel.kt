package com.example.e_arsivportal.presentation.home

import androidx.lifecycle.ViewModel
import com.example.e_arsivportal.domain.repository.RepositoryInterface
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