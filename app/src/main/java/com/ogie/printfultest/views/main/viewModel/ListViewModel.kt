package com.ogie.printfultest.views.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogie.printfultest.model.RickMorty
import com.ogie.printfultest.model.RickMortyResponse
import com.ogie.printfultest.network.repository.NetworkRepositoryImpl
import com.ogie.printfultest.utils.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel : ViewModel() {
    private val repository = NetworkRepositoryImpl()

    val list = MutableLiveData<ApiResponse<RickMortyResponse>>()

    val item = MutableLiveData<ApiResponse<RickMorty>>()


    fun fetchList(){
        list.value = ApiResponse.Loading()
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val data = repository.fetchList()
                list.postValue(data)
            }
        }
    }

    fun fetchItem(id : Int){
        item.value = ApiResponse.Loading()
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val data = repository.fetchItem(id)
                item.postValue(data)
            }
        }
    }
}