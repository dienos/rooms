package com.jth.rooms.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jth.rooms.repo.MainRepository
import com.jth.rooms.repo.NotNullMutableLiveData
import com.jth.rooms.repo.model.*
import com.jth.rooms.usecase.MainActivityUseCase
import com.jth.rooms.repo.RoomDataFactory

class MainViewModel(private val useCase: MainActivityUseCase, val repo: MainRepository) :
    ViewModel() {
    companion object {
        const val fileName = "json.txt"
        const val VIEW_TYPE_LEFT = 0
        const val VIEW_TYPE_RIGHT = 1
        const val VIEW_TYPE_AVERAGE = 2
    }

    var roomDataFactory: RoomDataFactory? = null
    private var filerList : List<Room> = arrayListOf()
    var liveFilerList : NotNullMutableLiveData<List<Room>> = NotNullMutableLiveData(arrayListOf())
    var roomListData : NotNullMutableLiveData<RoomListData> =  NotNullMutableLiveData(RoomListData())
    var roomTypeFilter: ArrayList<Int> = arrayListOf(0, 1, 2, 3)
    var sellingTypeFilter: ArrayList<Int> = arrayListOf(0, 1, 2)

    private val parsingData: ParsingData? = null

    private fun getJsonString(): ParsingData? {
        useCase.fromAssets(fileName)?.let {
            return parsingJsonToObject(it.trim())
        } ?: return null
    }

    private fun getSellingType(type: Int): String {
        return when (type) {
            SellingType.MONTHLY.type -> {
                SellingType.MONTHLY.result
            }

            SellingType.CHARTER.type -> {
                SellingType.CHARTER.result
            }

            SellingType.TRADING.type -> {
                SellingType.TRADING.result
            }

            else ->
                SellingType.MONTHLY.result
        }
    }

    private fun getRoomType(type: Int): String {
        return when (type) {
            RoomType.ONE.type -> {
                RoomType.ONE.result
            }

            RoomType.TWO.type -> {
                RoomType.TWO.result
            }

            RoomType.OP.type -> {
                RoomType.OP.result
            }

            RoomType.APT.type -> {
                RoomType.APT.result
            }

            else ->
                RoomType.ONE.result
        }
    }

    private fun getViewType(type: Int): Int {
        if (type == 0 || type == 1) {
            return VIEW_TYPE_RIGHT
        } else {
            return VIEW_TYPE_LEFT
        }
    }

    private fun parsingJsonToObject(json: String): ParsingData {
        val type = object : TypeToken<ParsingData>() {}.type
        val data: ParsingData = Gson().fromJson(json, type)
        return data
    }

    fun getPagedItem(): LiveData<PagedList<Room>> {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(100)
            .setPageSize(12)
            .setPrefetchDistance(5)
            .setEnablePlaceholders(false)
            .build()

        filerList = roomListData.value.rooms.filter { it.id <= 11 }
        val factory = RoomDataFactory(liveFilerList, roomListData.value.rooms)
        roomDataFactory = factory
        liveFilerList.value = filerList

        return LivePagedListBuilder(factory, config).build()
    }

    fun makePagedList() {
        val data: ParsingData?

        if (parsingData == null) {
            data = getJsonString()
        } else {
            data = parsingData
        }

        val filterRoom: ArrayList<ParsingRoom> = arrayListOf()

        repo.average = data?.average?.first()

        data?.rooms?.forEach {
            roomTypeFilter.forEach { roomType ->
                sellingTypeFilter.forEach { sellingType ->
                    if (it.room_type == roomType && it.selling_type == sellingType) {
                        filterRoom.add(it)
                    }
                }
            }
        }

        if (filterRoom.isNotEmpty()) {
            data?.rooms?.clear()
            data?.rooms?.addAll(filterRoom)
        }

        data?.apply {
            val list: ArrayList<Room> = arrayListOf()
            roomListData.value.average = average

            rooms.forEachIndexed { i, it ->
                val item = Room()
                item.id = i

                if (item.id == 11) {
                    item.viewType = VIEW_TYPE_AVERAGE
                } else {
                    item.viewType = getViewType(it.room_type)
                }

                item.room_type = getRoomType(it.room_type)
                item.img_url = it.img_url
                item.hash_tags = it.hash_tags
                item.price_title = it.price_title
                item.selling_type = getSellingType(it.selling_type)
                item.is_check = it.is_check
                item.desc = it.desc
                list.add(item)
            }

            roomListData.value.rooms = list
        }
    }
}