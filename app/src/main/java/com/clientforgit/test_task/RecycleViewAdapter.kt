package com.clientforgit.test_task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clientforgit.test_task.shibe.Shibe
import com.squareup.picasso.Picasso


class RecycleViewAdapter(private var context: Context)
    : RecyclerView.Adapter<RecycleViewAdapter.ShibeViewHolder>(){

    class ShibeViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private var shibeList = mutableListOf<Shibe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShibeViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ShibeViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ShibeViewHolder, position: Int) {
        val nicknameTextView = holder.itemView.findViewById<TextView>(R.id.nickname)
        nicknameTextView.text = shibeList[position].nickname
        val ageTextView = holder.itemView.findViewById<TextView>(R.id.age)
        ageTextView.text = shibeList[position].age.toString()
        val imageView = holder.itemView.findViewById<ImageView>(R.id.image_view)
        Picasso.with(context).load(shibeList[position].image).into(imageView)
    }

    override fun getItemCount(): Int {
        return shibeList.size
    }

    fun addShibes(shibeList: List<Shibe>) {
        this.shibeList.addAll(shibeList)
        notifyDataSetChanged()
    }
}