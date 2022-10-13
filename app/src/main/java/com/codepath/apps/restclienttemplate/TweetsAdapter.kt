package com.codepath.apps.restclienttemplate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.apps.restclienttemplate.models.TimelineActivity
import com.codepath.apps.restclienttemplate.models.Tweet

class TweetsAdapter(var context : Context, var tweets: ArrayList<Tweet>) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {
    //pass in context + list of tweets


    //define viewholder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivProfileImage: ImageView = itemView.findViewById<ImageView>(R.id.ivProfileImage)
        var tvBody: TextView = itemView.findViewById<TextView>(R.id.tvBody)
        var tvScreenName : TextView = itemView.findViewById<TextView>(R.id.tvScreenName)

        fun bind(tweet: Tweet, context: Context) {
            tvBody.text = tweet.body
            tvScreenName.text = tweet.user.screenName
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage)
        }


    }

    //for each row, inflate layout,
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false)
        return ViewHolder(view)
    }
    //bind vals based on position of element
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var tweet = tweets.get(position)
        holder.bind(tweet, context)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }
}