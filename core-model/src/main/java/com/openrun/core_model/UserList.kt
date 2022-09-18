package com.openrun.core_model;

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//"page": 2,
//"per_page": 6,
//"total": 12,
//"total_pages": 2,
//"data": [
//{
//    "id": 7,
//    "email": "michael.lawson@reqres.in",
//    "first_name": "Michael",
//    "last_name": "Lawson",
//    "avatar": "https://reqres.in/img/faces/7-image.jpg"
//},

@JsonClass(generateAdapter = true)
data class UserList(
    @Json(name = "page")
    val page: Int,
    @Json(name = "per_page")
    val per_page: Int,
    @Json(name = "total")
    val total: Int,
    @Json(name = "total_pages")
    val total_pages: Int,
    @Json(name = "data")
    val types: List<Data>,
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        val id: Int,
        @Json(name = "email")
        val email: String,
        @Json(name = "first_name")
        val first_name: String,
        @Json(name = "last_name")
        val last_name: String,
        @Json(name = "avatar")
        val avatar: String,
    )
}
