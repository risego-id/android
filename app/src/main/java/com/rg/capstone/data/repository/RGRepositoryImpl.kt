package com.rg.capstone.data.repository

import android.app.Application
import com.rg.capstone.R
import com.rg.capstone.data.remote.RGApi
import com.rg.capstone.domain.repository.RGRepository

class RGRepositoryImpl(
    private val api: RGApi,
    private val appContext: Application
): RGRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from repository. The app name is $appName")
    }

    override suspend fun doNetworkCall() {

    }
}