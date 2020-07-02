package com.example.myapplication.ui.search

import android.text.TextUtils
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.persistence.repositories.GitRepository
import com.example.myapplication.data.remote.response.GitMember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class SearchViewModel @ViewModelInject constructor(val repository: GitRepository) : ViewModel() {

    var list = MutableLiveData<List<GitMember>>()
    var keyword = MutableLiveData<String>()


    fun search(keyword: String) {
        var dataObj: JSONObject? = null
        val salesToken = "5aff8f5cde1c5b531e35b4f106693820";
        val customerId = "5aff8f5cde1c5b531e35b4f106693820";

        if (!TextUtils.isEmpty(customerId)) {
            try {
                dataObj = JSONObject()
                dataObj.put("sales_token", salesToken)
                dataObj.put("keyword", keyword)

                Log.e("ïnput", dataObj.toString())
                CoroutineScope(Dispatchers.IO).launch {
                    val resp = repository.list()
                    list.postValue(resp.data)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun gitMembers() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.list()
            list.postValue(res.data)
        }
    }

    fun gitMemberSearch(keyword: String?) {

    }

}