package com.example.myapplication.ui.search

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.RxSearchWidget
import com.example.myapplication.data.remote.response.GitMember
import com.example.myapplication.databinding.ActivitySearchBinding
import com.example.myapplication.ui.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding
    var adapter: GitMembersAdapter? = null

    lateinit var activity: Activity

    val viewModel: SearchViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.searchVm = viewModel

        binding.lifecycleOwner = this
        activity = this

        adapter = GitMembersAdapter(onItemClickListener)
        binding.list.adapter = adapter
        adapter?.apply { submitList(ArrayList()) }

        viewModel.keyword.observe(this, keywordObserver);
        viewModel.list.observe(this, listObserver)
        RxSearchWidget(binding.searchET, listener)
        viewModel.gitMembers()
    }

    private val listener = RxSearchWidget.RxSearchListener { viewModel.gitMembers() }
    private val keywordObserver = Observer<String> { Log.e("search key", it) }

    private val listObserver = Observer<List<GitMember>> {
        Log.e("", "-----------------------" + it.size)
        adapter!!.submitList(it)
    }

    private val onItemClickListener = object : OnItemClickListener<GitMember> {
        override fun onItemClick(item: GitMember, position: Int) {
            Toast.makeText(activity, item.login, Toast.LENGTH_SHORT).show()
        }
    }
}
