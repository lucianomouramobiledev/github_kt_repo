package com.lucianommoura.githubktrepo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucianommoura.githubktrepo.adapter.GitHubListAdapter
import com.lucianommoura.githubktrepo.databinding.ActivityMainBinding
import com.lucianommoura.githubktrepo.model.Item
import com.lucianommoura.githubktrepo.repository.GitHubRespository


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
        initObservers()
    }

    private fun initObservers() {
        viewModel.errorData.observe(this, Observer { errorMessage ->
            showErrorMessage(errorMessage)
        })

        viewModel.liveData.observe(this, Observer { gitHubItemList ->
            binding.mainGithubRecyclerView.adapter =
                GitHubListAdapter(this, gitHubItemList as MutableList<Item>)
            val mLayoutManager = LinearLayoutManager(this)
            binding.mainGithubRecyclerView.setLayoutManager(mLayoutManager)

            var loading = true
            var pastVisiblesItems: Int
            var visibleItemCount: Int
            var totalItemCount: Int

            binding.mainGithubRecyclerView.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        visibleItemCount = mLayoutManager.childCount
                        totalItemCount = mLayoutManager.itemCount
                        pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                        if (loading) {
                            if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                                viewModel.loadMoreData()
                            }
                        }
                    }
                }
            })

        })
    }

    private fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.mainGithubRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

}