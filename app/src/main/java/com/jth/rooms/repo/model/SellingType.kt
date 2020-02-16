package com.jth.rooms.repo.model

enum class SellingType(val type : Int, val result : String) {
    MONTHLY(0, "월세"),
    CHARTER(1, "전세"),
    TRADING(2, "매매")
}