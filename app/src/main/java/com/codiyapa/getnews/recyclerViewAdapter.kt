package com.codiyapa.getnews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(private val context: Context, private val contactList: List<Datas>) :
        RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recycler, parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contactList[position]
        holder.itemView.setOnClickListener {
            openWebPage(item.url)
        }
        holder.title.text = item.title
        holder.author.text = item.author
        Glide.with(holder.itemView.context).load(item.urlToImage).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
//    fun updateNews(updatedNews: ArrayList<Datas>) {
//        contactList.clear()
//        contactList.addAll(updatedNews)
//
//        notifyDataSetChanged()
//    }
    fun openWebPage(url: String) {
    val builder =  CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}

class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val title = itemView.findViewById<TextView>(R.id.title)
    val author = itemView.findViewById<TextView>(R.id.textView2)
    val imageView = itemView.findViewById<ImageView>(R.id.imageView2)
}