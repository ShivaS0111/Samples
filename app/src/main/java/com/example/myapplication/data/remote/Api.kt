package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.response.GitMember
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("orgs/github/public_members")
    suspend fun getPublicMembers(): Response<List<GitMember>>
/*
    @FormUrlEncoded
    @POST("mobileapi/getbeatsbyffid")
    suspend fun getBeatsByFfidSearchKotlin(@Field("data") data: String)
            : Response<GetBeatsApiResp>

    @FormUrlEncoded
    @POST("cpmanager/timesheetmanage")
    suspend fun getTaskMangementApi(@Field("data") data: String): Response<TaskManagementResp>

    @FormUrlEncoded
    @POST("cpmanager/getdepartment")
    suspend fun departmentList(@Field("data") data: String): Response<DepartmentListResp>

    @FormUrlEncoded
    @POST("cpmanager/getintended")
    suspend fun getIntendStatusList(@Field("data") data: String): Response<IndentListResp>

    @FormUrlEncoded
    @POST("cpmanager/edittask")
    suspend fun edittask(@Field("data") data: String): Response<TaskResp>

    @FormUrlEncoded
    @POST("cpmanager/updatetimesheettask")
    suspend fun updateTask(@Field("data") data: String): Response<ApiResponse>

    @FormUrlEncoded
    @POST("cpmanager/deletetask")
    suspend fun getDeleteTaskApi(@Field("data") data: String): Response<TaskManagementResp>*/

}
