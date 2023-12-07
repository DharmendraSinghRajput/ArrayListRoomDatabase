package com.example.roomdatabasearray.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasearray.databinding.RowPostBinding

class PostAdapter(val onClick: (position: Int, viewId: Int) -> Unit) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var getAllTeamMemberDataResponse = listOf<PostApiResponseItem>()

    inner class PostViewHolder(var mBinding: RowPostBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder = PostViewHolder(RowPostBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.mBinding.apply {
            val followData = getAllTeamMemberDataResponse[position]

            getAllTeamMemberDataResponse[position].apply {
              /*  tvName.text="${user.vFirstName ?: ""} ${user.vLastName ?: ""}"
                GeneralFunctions.loadImage(root.context, followData.user.vImage,ivPost)
                root.setOnClickListener { onClick(position, -1) }*/
            }


        }
    }

    override fun getItemCount(): Int = getAllTeamMemberDataResponse.size

    fun setData(chatTeamList: List<PostApiResponseItem>) {
        this.getAllTeamMemberDataResponse = chatTeamList
        notifyDataSetChanged()
    }
}