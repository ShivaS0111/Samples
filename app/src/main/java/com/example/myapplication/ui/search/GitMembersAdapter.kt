package com.example.myapplication.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.response.GitMember
import com.example.myapplication.databinding.ItemGitSearchBinding
import com.example.myapplication.ui.BaseAdapter
import com.example.myapplication.ui.OnItemClickListener

/**
 * Adapter for the [RecyclerView] in [].
 */
class GitMembersAdapter(var listener: OnItemClickListener<GitMember>) :
    BaseAdapter<GitMember, BaseAdapter.Holder<GitMember>>(GitCallBack()) {

    override fun onBindViewHolder(holder: Holder<GitMember>, position: Int) {
        val item = getItem(position)
        holder.bind(listener, item, position)
    }

    private class SearchViewHolder(val binding: ItemGitSearchBinding) :
        BaseAdapter.Holder<GitMember>(binding) {
        override fun bind(
            listener: OnItemClickListener<GitMember>, item: GitMember,
            adapterPosition: Int
        ) {
            binding.gitMember = item
            binding.listener = listener
            binding.position = adapterPosition
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<GitMember> {
        return SearchViewHolder(
            ItemGitSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class GitCallBack : DiffUtil.ItemCallback<GitMember>() {
        override fun areItemsTheSame(oldItem: GitMember, newItem: GitMember): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GitMember, newItem: GitMember): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
}