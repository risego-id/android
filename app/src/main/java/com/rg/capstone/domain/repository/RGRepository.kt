package com.rg.capstone.domain.repository

interface RGRepository {
    suspend fun doNetworkCall()
}