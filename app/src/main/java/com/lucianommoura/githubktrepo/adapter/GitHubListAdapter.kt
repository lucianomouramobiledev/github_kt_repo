package com.lucianommoura.githubktrepo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lucianommoura.githubktrepo.R
import com.lucianommoura.githubktrepo.databinding.CustomGithubBinding
import com.lucianommoura.githubktrepo.model.GitHubItemResponse
import com.lucianommoura.githubktrepo.model.GitHubViewHolder
import com.lucianommoura.githubktrepo.model.Item
import com.squareup.picasso.Picasso

class GitHubListAdapter (var context: Context, var items: MutableList<Item>): RecyclerView.Adapter<GitHubViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubViewHolder {
        val binding: CustomGithubBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                R.layout.custom_github, parent, false)
        return GitHubViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GitHubViewHolder, position: Int) {
        var item = items[position]
        holder.bind(item)

        holder.binding.githubItemName.setText(item.full_name)
        holder.binding.githubItemAuthorName.setText(item.owner.login)
        holder.binding.gitHubItemStars.setText(item.stargazers_count.toString())
        holder.binding.githubItemForkName.setText(item.forks.toString())

        Picasso.with(context).load(item.owner.avatar_url).into(holder.binding.githubItemImage)
    }

}