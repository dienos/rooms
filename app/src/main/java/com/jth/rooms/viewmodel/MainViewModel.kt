package com.jth.rooms.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jth.rooms.repo.MainRepository
import com.jth.rooms.repo.model.ParsingData
import com.jth.rooms.repo.model.Room
import com.jth.rooms.repo.model.RoomType
import com.jth.rooms.repo.model.SellingType
import com.jth.rooms.usecase.MainActivityUseCase
import kotlinx.coroutines.*

class MainViewModel(private val useCase : MainActivityUseCase, val repo : MainRepository) : ViewModel() {
    companion object {
        const val fileName = "json.txt"
        const val VIEW_TYPE_LEFT = 0
        const val VIEW_TYPE_RIGHT = 1
        const val VIEW_TYPE_AVERAGE = 2
    }

    private val jsb = Job()
    private val scope = CoroutineScope(Dispatchers.Main + jsb)

    private val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(12)
        .setPageSize(12)
        .setPrefetchDistance(5)
        .setEnablePlaceholders(true)
        .build()

    var roomsData: LiveData<PagedList<Room>> = LivePagedListBuilder(repo.db.findAll(), config).build()

    fun getJsonString() {
        useCase.fromAssets(fileName)?.let {
            val realData = Room()
            val parsingData : ParsingData = parsingJsonToObject(it.trim())
            repo.average = parsingData.average.first()

            parsingData.rooms.forEachIndexed {
                i, rooms ->

                var hashTagString = ""
                val tagList = rooms.hash_tags

                for(j in tagList.indices) {
                    val hashTag : String = tagList[j]

                    if(tagList.size - 1 == j) {
                        hashTagString = hashTagString.plus(hashTag)
                    } else {
                        hashTagString = hashTagString.plus(hashTag).plus(",")
                    }
                }

                scope.launch {
                    withContext(Dispatchers.IO) {
                        realData.id = i
                        realData.desc = rooms.desc
                        realData.hash_tags = hashTagString
                        realData.price_title = rooms.price_title
                        realData.img_url = rooms.img_url
                        realData.is_check = rooms.is_check
                        realData.room_type = getRoomType(rooms.room_type)
                        realData.selling_type = getSellingType(rooms.selling_type)

                        if(rooms.room_type == 0 || rooms.room_type == 1) {
                            realData.viewType = VIEW_TYPE_RIGHT
                        } else {
                            realData.viewType = VIEW_TYPE_LEFT
                        }

                        if(realData.id.plus(1) % 12 == 0) {
                            realData.viewType = VIEW_TYPE_AVERAGE
                        }

                        repo.db.insert(realData)
                    }
                }
            }
        }
    }

    fun findAll() {
        roomsData = LivePagedListBuilder(repo.db.findAll(),12).build()
    }

    fun getCount() : Int {
        var count = 0

        scope.launch {
            withContext(Dispatchers.IO) {
                count = repo.db.getRowCount()
            }
        }

        return count
    }

    private fun getSellingType(type : Int) : String {
        return when(type)  {
            SellingType.MONTHLY.type ->{
                SellingType.MONTHLY.result
            }

            SellingType.CHARTER.type ->{
                SellingType.CHARTER.result
            }

            SellingType.TRADING.type ->{
                SellingType.TRADING.result
            }

            else ->
                SellingType.MONTHLY.result
        }
    }

    private fun getRoomType(type : Int) : String {
        return when(type)  {
            RoomType.ONE.type ->{
                RoomType.ONE.result
            }

            RoomType.TWO.type ->{
                RoomType.TWO.result
            }

            RoomType.OP.type ->{
                RoomType.OP.result
            }

            RoomType.APT.type ->{
                RoomType.APT.result
            }

            else ->
                RoomType.ONE.result
        }
    }

    private fun parsingJsonToObject(json : String) : ParsingData {
        val type = object : TypeToken<ParsingData>() {}.type
        val data : ParsingData = Gson().fromJson(json, type )
        return data
    }
}