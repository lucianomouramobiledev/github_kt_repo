package com.lucianommoura.githubktrepo.services

import com.lucianommoura.githubktrepo.model.GitHubItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubAPI {

    @GET("/search/repositories")
    suspend fun getGitHubRepositories(
        @Query("q") searchQuery : String = "language:kotlin",
        @Query("sort") sortOption : String = "stars",
        @Query("page") pageNumber : Int = 0
        ) : Response<GitHubItemResponse>

}