package com.codepath.apps.restclienttemplate.models

import org.json.JSONArray
import org.json.JSONObject

class Tweet {
    lateinit var body : String
    lateinit var createdAt : String
    lateinit var user :User
    companion object{
        fun fromJson(jsonObject: JSONObject) : Tweet {
            val tweet = Tweet()
            tweet.body =  jsonObject.getString("text")
            tweet.createdAt = jsonObject.getString("created_at")
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"))
            return tweet
        }
        fun fromJsonArray(jsonArray: JSONArray): List<Tweet> {
            val tweets  = ArrayList<Tweet>()
            for(i in 0 until jsonArray.length()){
                tweets.add(fromJson(jsonArray.getJSONObject(i)))
            }
            return tweets
        }
    }
}
