package com.example.searchphoto.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.searchphoto.R
import com.example.searchphoto.databinding.PhotoItemBinding
import com.squareup.picasso.Picasso
import main.PhotosItem.Photo as PhotosItem

class MainAdapter : RecyclerView.Adapter<MainAdapter.PhotoViewHolder>() {

    private val diffUtilItemCallback = object : DiffUtil.ItemCallback<PhotosItem>() {

        override fun areItemsTheSame(oldItem: PhotosItem, newItem: PhotosItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotosItem, newItem: PhotosItem): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(this, diffUtilItemCallback)

    private lateinit var binding: PhotoItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return listDiffer.currentList.size
    }

    fun submitList(list: List<PhotosItem>) {
        listDiffer.submitList(list)
    }

    class PhotoViewHolder
    constructor(
        private val binding: PhotoItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PhotosItem) {

            Picasso.get().load(item.src.medium)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.image)

            binding.imageText.text = item.id.toString()
        }
    }

}