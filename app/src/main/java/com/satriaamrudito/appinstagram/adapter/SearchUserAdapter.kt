package com.satriaamrudito.appinstagram.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.satriaamrudito.appinstagram.R
import com.satriaamrudito.appinstagram.model.UserSearch
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_search.view.*

class SearchUserAdapter(private var mContext: Context?, private val mUser: List<UserSearch>, private var isFragment: Boolean = false) : RecyclerView.Adapter<SearchUserAdapter.SearchUserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_item_search,parent,false)
        return SearchUserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUser.size
    }

    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) {
        val user = mUser[position]
        holder.username.text = user.getUsername()
        holder.fullname.text = user.getFullname()

        Picasso.get()
            .load(user.getImage())
            .into(holder.image)
    }
    inner class SearchUserViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val username : TextView = itemView.findViewById(R.id.txt_username_search)
        val fullname : TextView = itemView.findViewById(R.id.txt_fullname_search)
        val buttonFollow : Button = itemView.findViewById(R.id.follow_button)
        val image : ImageView = itemView.findViewById(R.id.img_search_profile)

    }

}
