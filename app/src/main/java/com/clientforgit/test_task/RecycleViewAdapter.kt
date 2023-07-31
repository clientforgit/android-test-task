package com.clientforgit.test_task

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clientforgit.test_task.user.User


class RecycleViewAdapter(private val onClickListener: (User) -> (Unit))
    : RecyclerView.Adapter<RecycleViewAdapter.UserViewHolder>(){

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private var userList = emptyList<User>()

    var changedUsersPosition = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val nameTextView = holder.itemView.findViewById<TextView>(R.id.name)
        nameTextView.text = userList[position].name
        val ageTextView = holder.itemView.findViewById<TextView>(R.id.age)
        ageTextView.text = userList[position].age.toString()

       spinnerInit(holder)

        holder.itemView.setOnClickListener {
            Log.i("test for item", userList[position].isStudent.toString())
            onClickListener(userList[position])
        }
    }

    private fun spinnerInit(holder: UserViewHolder) {
        val spinner = holder.itemView.findViewById<Spinner>(R.id.spinner)
        val spinnerAdapter = ArrayAdapter.createFromResource(holder.itemView.context, R.array.is_student_choices, R.layout.spinner_item)

        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        fun Boolean.toInt() = if (this) 1 else 0
        fun Int.toBoolean() = this == 1
        spinner.setSelection(userList[holder.adapterPosition].isStudent.toInt())
        spinner.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, spinnerPosition: Int, id: Long
            ) {
                userList[holder.adapterPosition].isStudent = spinnerPosition.toBoolean()
                changedUsersPosition.add(holder.adapterPosition)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setUsers(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    fun getUsers(): List<User> {
        return this.userList
    }
}