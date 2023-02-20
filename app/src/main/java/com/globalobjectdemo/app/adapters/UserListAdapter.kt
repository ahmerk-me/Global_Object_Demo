package com.globalobjectdemo.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globalobjectdemo.app.views.activity.MainActivity
import com.globalobjectdemo.app.databinding.RowUserListBinding
import com.globalobjectdemo.app.models.UserModel

class UserListAdapter (val act: MainActivity, private val itemsData: ArrayList<UserModel>,  private val listener: OnItemClickListener?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            RowUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemView)
    }

    inner class MyViewHolder(b: RowUserListBinding) : RecyclerView.ViewHolder(b.root) {

        var binding: RowUserListBinding = b

        fun bind(listener: OnItemClickListener, position: Int) {

//            act.setTextFonts(binding.root)

            binding.linearParent.setOnClickListener { listener.onItemClick(position, itemsData) }

        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MyViewHolder).bind(listener!!, holder.adapterPosition)

        itemsData[holder.adapterPosition].let {

            with(holder.binding) {

                with(it) {

                    tvAge.text = itemsData[position].age.toString()
                    tvName.text = itemsData[position].name
                    tvCountry.text = itemsData[position].country

                }

            }

        }

    }


    override fun getItemCount(): Int {

        return itemsData.size

    }


    fun updateList(newList: ArrayList<UserModel>) {

        var list = newList.clone() as ArrayList<UserModel>

        with(itemsData) {

            clear()

            addAll(list)
        }

        notifyDataSetChanged()

    }


    interface OnItemClickListener {

        fun onItemClick(position: Int, list: ArrayList<UserModel>)

    }


}