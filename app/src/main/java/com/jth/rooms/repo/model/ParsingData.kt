package com.jth.rooms.repo.model

import com.google.gson.annotations.SerializedName

class ParsingData(@SerializedName("rooms")
                  val rooms: ArrayList<ParsingRoom> = arrayListOf(),

                  @SerializedName("average")
                  val average: ArrayList<Average> = arrayListOf())