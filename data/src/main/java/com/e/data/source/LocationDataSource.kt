package com.e.data.source

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}