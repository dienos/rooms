package com.jth.rooms.usecase

import android.content.Context

class MainActivityUseCase(private val context: Context) {
    fun fromAssets(fileName: String?): String? {
        return try {
            fileName?.let {
                val assetManager = context.resources.assets
                val inputStream = assetManager.open(fileName)
                val buffer = ByteArray(inputStream.available())
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charsets.UTF_8)
            }
        } catch (e: Exception) {
            ""
        }
    }
}