package fastcampus.projects.githubactivity.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id") //mapping해주는 개념 실질적인 id값으로 안쓰고 예를 들어 repoId라고 설정해도 매핑해줌
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("language")
    val language: String?,
    @SerializedName("stargazers_count")
    val starCount: Int,
    @SerializedName("forks_count")
    val forkCount: Int,
    @SerializedName("html_url")
    val htmlUrl: String
)