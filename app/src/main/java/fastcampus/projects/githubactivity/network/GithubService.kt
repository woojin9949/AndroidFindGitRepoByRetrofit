package fastcampus.projects.githubactivity.network

import fastcampus.projects.githubactivity.model.Repo
import fastcampus.projects.githubactivity.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("users/{username}/repos")
    fun listRepos(
        @Path("username") username: String,
        @Query("page") page: Int
    ): Call<ArrayList<Repo>>

    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String
    ): Call<UserDto>
}