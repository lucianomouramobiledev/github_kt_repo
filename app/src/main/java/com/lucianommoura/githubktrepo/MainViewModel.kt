package com.lucianommoura.githubktrepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianommoura.githubktrepo.model.Item
import com.lucianommoura.githubktrepo.repository.GitHubRespository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: GitHubRespository) : ViewModel() {

    private val itensLiveData = MutableLiveData<List<Item>>()
    val liveData: LiveData<List<Item>>
        get() = itensLiveData

    private val errorLiveData = MutableLiveData<String>().apply { "" }
    val errorData: MutableLiveData<String>
        get() = errorLiveData

    var gitHubPage = 0

    init {
        getGithubItens(gitHubPage)
    }

    fun getGithubItens(page: Int) = viewModelScope.launch {
        getGitHubsApi(page)
    }

    private suspend fun getGitHubsApi(page: Int) {
        val response = repository.getGitHubItens(page)
        if (response.isSuccessful) {
            gitHubPage++
            response.body()?.let { responseItens ->
                itensLiveData.postValue(responseItens.items)
            }
        } else {
            errorLiveData.postValue(response.message())
        }
    }

    fun loadMoreData() {
        getGithubItens(gitHubPage++)
    }

}