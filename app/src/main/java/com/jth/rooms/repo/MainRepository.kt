package com.jth.rooms.repo

import com.jth.rooms.repo.model.Average

class MainRepository {
    var average : Average ?= null

    companion object {
        private var instance: MainRepository? = null

        fun getInstance(): MainRepository {
            if(instance == null) {
                instance = MainRepository()
            }

            return instance!!
        }
    }
}