package com.openrun.wantrunning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import com.openrun.core_data.repository.ReqResRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@HiltViewModel
class MainViewModel @Inject constructor(
    private val reqResRepository: ReqResRepositoryImpl
) : ViewModel() {
    private val _result = MutableLiveData("");
    val result: LiveData<String>
    get() = _result

    fun fetchUserList() {
        viewModelScope.launch {
            val response = reqResRepository.fetchUserList()
            withContext(Dispatchers.Main) {
                _result.value = response.toString()
            }
        }
    }
}