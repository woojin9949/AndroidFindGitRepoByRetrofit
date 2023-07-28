package fastcampus.projects.githubactivity.model

import com.google.gson.annotations.SerializedName

class UserDto(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("items")
    val items: List<User>
)


