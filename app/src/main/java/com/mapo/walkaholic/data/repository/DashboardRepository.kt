package com.mapo.walkaholic.data.repository

import com.mapo.walkaholic.data.UserPreferences
import com.mapo.walkaholic.data.network.Api

class DashboardRepository(
        private val api: Api,
        private val preferences: UserPreferences
) : BaseRepository() {
    suspend fun anyService(
            par1: String,
            par2: String
    ) = safeApiCall {
        api.anyService()
    }

    suspend fun saveAuthToken(id: Long) {
        preferences.saveAuthToken(id)
    }
}