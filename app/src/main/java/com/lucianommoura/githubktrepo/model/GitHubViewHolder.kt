package com.lucianommoura.githubktrepo.model

import androidx.recyclerview.widget.RecyclerView
import com.lucianommoura.githubktrepo.databinding.CustomGithubBinding
import com.squareup.picasso.Picasso

class GitHubViewHolder(val binding: CustomGithubBinding): RecyclerView.ViewHolder(binding.root) {

    private lateinit var item: Item

    internal fun bind(
        item: Item
    ) {
        this.item = item
        prepareOnClickListener()
        onBind()
    }

    private fun onBind() {
    }

    private fun prepareOnClickListener() {
        /*binding.imgEdit.setOnClickListener {'
            editListener(item)
        }

        binding.imgDelete.setOnClickListener {
            deleteListener(item)
        }*/
    }

}