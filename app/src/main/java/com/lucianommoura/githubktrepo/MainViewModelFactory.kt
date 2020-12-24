package com.lucianommoura.githubktrepo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucianommoura.githubktrepo.repository.GitHubRespository

class MainViewModelFactory(
    val repository: GitHubRespository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}