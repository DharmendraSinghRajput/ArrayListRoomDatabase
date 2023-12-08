package com.example.roomdatabasearray.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasearray.databinding.RowImageBinding
import com.example.roomdatabasearray.ui.models.PostApiPhotoResponse
import com.example.roomdatabasearray.utils.GeneralFunctions

class PostPhotoAdapter (val onClick: (position: Int) -> Unit) : RecyclerView.Adapter<PostPhotoAdapter.PostViewHolder>() {

    private var getPostApiPhotoResponse = listOf<PostApiPhotoResponse>()

    inner class PostViewHolder(var mBinding: RowImageBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder = PostViewHolder(
        RowImageBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.mBinding.apply {
            getPostApiPhotoResponse[position].apply {
                tvTitle.text=title
                GeneralFunctions.loadImage(root.context,getPostApiPhotoResponse[position].url ?: "",ivFollowers)
            }
        }
    }
    override fun getItemCount(): Int = getPostApiPhotoResponse.size

    fun setData(chatTeamList: List<PostApiPhotoResponse>) {
        this.getPostApiPhotoResponse = chatTeamList
        notifyDataSetChanged()
    }
}