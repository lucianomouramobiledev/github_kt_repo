package com.lucianommoura.githubktrepo.repository

import com.lucianommoura.githubktrepo.services.RetroFitInstance

class GitHubRespository {

    suspend fun getGitHubItens(pageNumber: Int) =
        RetroFitInstance.api.getGitHubRepositories()

}
