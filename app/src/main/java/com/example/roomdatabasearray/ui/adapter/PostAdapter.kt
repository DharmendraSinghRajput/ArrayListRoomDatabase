package com.example.roomdatabasearray.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasearray.databinding.RowPostBinding
import com.example.roomdatabasearray.ui.models.PostApiResponseItem

class PostAdapter(val onClick: (position: Int) -> Unit) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var getAllTeamMemberDataResponse = listOf<PostApiResponseItem>()

    inner class PostViewHolder(var mBinding: RowPostBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder = PostViewHolder(RowPostBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.mBinding.apply {
            getAllTeamMemberDataResponse[position].apply {
                tvBody.text=title
                tvSubBody.text=body
            }
        }
    }
    override fun getItemCount(): Int = getAllTeamMemberDataResponse.size

    fun setData(chatTeamList: List<PostApiResponseItem>) {
        this.getAllTeamMemberDataResponse = chatTeamList
        notifyDataSetChanged()
    }
}