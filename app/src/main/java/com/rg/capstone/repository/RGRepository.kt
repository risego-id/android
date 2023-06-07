package com.rg.capstone.repository

interface RGRepository {
    suspend fun doNetworkCall()

    suspend fun getAllGoalCategories()
}