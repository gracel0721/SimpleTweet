package com.codepath.apps.restclienttemplate.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.apps.restclienttemplate.R
import com.codepath.apps.restclienttemplate.TweetsAdapter
import com.codepath.apps.restclienttemplate.TwitterApp
import com.codepath.apps.restclienttemplate.TwitterClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import org.json.JSONException


class TimelineActivity : AppCompatActivity() {
    lateinit var client: TwitterClient;
    lateinit var rvTweets: RecyclerView
    lateinit var tweets : ArrayList<Tweet>
    lateinit var adapter :TweetsAdapter
    val TAG: String = "TimelineActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        client = TwitterApp.getRestClient(this)
        populateHomeTimeline()
        rvTweets = findViewById(R.id.rvTweets)
        tweets = ArrayList<Tweet>()
        adapter= TweetsAdapter(this, tweets)

        rvTweets.layoutManager = LinearLayoutManager(this)
        rvTweets.adapter = adapter

    }

    private fun populateHomeTimeline() {
        client.getHomeTimeline(object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: okhttp3.Headers?, json: JSON) {
                Log.i(TAG, "onSuccess" + json.toString())
                var jsonArray = json.jsonArray
                try {
                    tweets = Tweet.fromJsonArray(jsonArray) as ArrayList<Tweet>
                    adapter.notifyDataSetChanged()
                }catch (e: JSONException){
                    Log.e(TAG, "JSON exception", e)
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: okhttp3.Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "OnFailure", throwable)
            }

        })
    }
}

