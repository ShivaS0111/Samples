package com.example.myapplication.di.module

import android.content.Context
import com.example.myapplication.data.persistence.dao.GitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class PersistenceModule {

    /*@Provides
    @Singleton
    fun provideDBHelper(context: Context) = DBHelper(context)*/


    @Provides
    @Singleton
    fun provideGitDao(context: Context) = GitDao(context)

    /*@Provides
    @Singleton
    fun provideGitRepository( dao:GitDao, dataResource: GitDataResource) = GitRepository(dao, dataResource )
*/
    /*@Provides
    @Singleton
    fun provideTaskManagementDao( context: Context) = TaskManagementDao(context)*/

    /*@Provides
    @Singleton
    fun provideTaskManagementRepository( dao:TaskManagementDao, dataResource: TaskMangementDataResource )=
            TaskManagementRepository( dao, dataResource )*/

    /*@Provides
    @Singleton
    fun provideTimesheetDao( context: Context)= TimesheetDao(context)*/

    /*@Provides
    @Singleton
    fun provideTimesheetRepository( dao:TimesheetDao, dataResource: TimesheetDataResource )=
            TimesheetRepository( dao, dataResource )*/


}