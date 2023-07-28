package fastcampus.projects.githubactivity.network

import fastcampus.projects.githubactivity.model.Repo
import fastcampus.projects.githubactivity.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @Headers("Authorization: Bearer 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")
    @GET("users/{username}/repos")
    fun listRepos(
        @Path("username") username: String
    ): Call<ArrayList<Repo>>

    @Headers("Authorization: Bearer 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")
    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String
    ): Call<UserDto>
}