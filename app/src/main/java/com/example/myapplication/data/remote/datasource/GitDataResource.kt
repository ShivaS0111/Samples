package com.example.myapplication.data.remote.datasource

import com.example.myapplication.data.remote.Api
import com.example.myapplication.data.remote.BaseDataSource
import javax.inject.Inject

class GitDataResource @Inject constructor(private val service: Api) : BaseDataSource(service) {

    suspend fun gitPublicMembers() = getResult { service.getPublicMembers() }

}