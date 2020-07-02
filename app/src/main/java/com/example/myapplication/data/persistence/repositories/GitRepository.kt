package com.example.myapplication.data.persistence.repositories

import com.example.myapplication.data.remote.Result
import com.example.myapplication.data.remote.datasource.GitDataResource
import com.example.myapplication.data.remote.response.GitMember
import javax.inject.Inject

class GitRepository @Inject constructor(
    private val remoteSource: GitDataResource
) {

    //apiCall
    suspend fun list() = remoteSource.gitPublicMembers()

    //fetch from localDB
    //suspend fun localList(data: String) = dao.list()

    //fetch from remote and saves into the db
    suspend fun newtworkDBOperation(): Result<List<GitMember>> {
        return remoteSource.gitPublicMembers().also {
            //it.data?.let { it1 -> dao.addAll(it1) }
        }
    }
}