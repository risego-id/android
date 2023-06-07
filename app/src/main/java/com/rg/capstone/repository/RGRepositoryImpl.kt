package com.rg.capstone.repository

import android.app.Application
import com.rg.capstone.R
import com.rg.capstone.domain.model.Category
import com.rg.capstone.network.ApiService

class RGRepositoryImpl(
    private val api: ApiService,
    private val appContext: Application
): RGRepository {

    private val category = mutableListOf<Category>()

    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from repository. The app name is $appName")
    }

    override suspend fun doNetworkCall() {

    }

    override suspend fun getAllGoalCategories() {

    }

//    override suspend fun getAllGoalCategories(): Flow<List<Category>> {
//        return flowOf(category)
//    }
}