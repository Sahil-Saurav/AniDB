package com.example.anidb.Utils

import com.example.anidb.viewModels.UserDetails
import com.example.anidb.viewModels.WatchList

fun Map<String,Any>.toUserDetails():UserDetails{
    return UserDetails(
        name = this["name"] as String,
        emailID = this["emailID"] as String,
        watchList = (this["watchList"] as? List<Map<String,String>>)?.map {
            WatchList(
                animeName = it["animeName"],
                imageUrl = it["imageUrl"]
            )
        }
    )
}