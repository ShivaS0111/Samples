package com.example.myapplication.data.persistence.dao

import android.content.Context
import com.example.myapplication.data.remote.response.GitMember
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GitDao @Inject constructor(@ApplicationContext context: Context?) {

    fun list(): List<GitMember>? {
        return null
    }


    fun addAll(list: List<GitMember>) {
    }

}