package com.jth.rooms.repo

import android.content.Context
import com.jth.rooms.repo.database.AppDatabase
import com.jth.rooms.repo.model.Average
import com.jth.rooms.repo.model.Room

class MainRepository(context : Context) {
    val db = AppDatabase.getInstance(context).roomDao()
    var average : Average ?= null

    companion object {
        private var instance: MainRepository? = null

        fun getInstance(context: Context): MainRepository {
            if(instance == null) {
                instance = MainRepository(context)
            }

            return instance!!
        }
    }

    fun dbInsert(data : Room) {
        val addRunnable = Runnable {
            db.insert(data)
        }

        val thread = Thread(addRunnable)
        thread.start()
    }
}