package com.jth.rooms.repo.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jth.rooms.repo.model.Room

@Dao
interface RoomDao {
    @Query("SELECT * FROM rooms ORDER BY id ASC")
    fun findAll(): DataSource.Factory<Int, Room>

    @Insert(onConflict = REPLACE)
    fun insert(room: Room)

    @Query("SELECT COUNT(id) FROM rooms")
    fun getRowCount(): Int
}