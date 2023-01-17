/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.roomwordssample

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class UserListAdapter : ListAdapter<User, UserListAdapter.UserViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.firstName, current.lastName, Date(current.birthDate).toString())
        /*holder.itemView.setOnClickListener {
            this.notifyItemRemoved(position)
        }*/
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        @SuppressLint("SetTextI18n")
        fun bind(firstName: String?, lastName: String?, birthDate: String?) {
            wordItemView.text = "$firstName $lastName $birthDate"
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                /*val wordItemView: TextView = parent.findViewById(R.id.textView)
                view.setOnClickListener {
                    Toast.makeText(
                        parent.context,
                        "✔ ${wordItemView.text}",
                        Toast.LENGTH_SHORT
                    ).show()
                }*/
                return UserViewHolder(view)
            }
        }
    }



    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
