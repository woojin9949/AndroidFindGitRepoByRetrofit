package fastcampus.projects.githubactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fastcampus.projects.githubactivity.adapter.RepoAdapter
import fastcampus.projects.githubactivity.databinding.ActivityRepoBinding
import fastcampus.projects.githubactivity.model.Repo
import fastcampus.projects.githubactivity.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoAdapter: RepoAdapter
    private var page = 0
    private var hasMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra("username") ?: return

        binding.usernameTextView.text = username

        repoAdapter = RepoAdapter {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.htmlUrl))
            startActivity(intent)
        }

        val linearLayoutManager = LinearLayoutManager(this@RepoActivity)
        binding.repoRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = repoAdapter
        }

        binding.repoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = linearLayoutManager.itemCount
                //리사이클러뷰 마지막 포지션의 몇번째인지 확인
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()
                if (lastVisiblePosition >= (totalCount - 1) && hasMore) {
                    page += 1
                    listRepo(username, page)
                }
            }
        })
        listRepo(username, 0)
    }

    private fun listRepo(username: String, page: Int) {
        val githubService = APIClient.retrofit.create(GithubService::class.java)

        githubService.listRepos(username, page).enqueue(object : Callback<ArrayList<Repo>> {
            override fun onResponse(
                call: Call<ArrayList<Repo>>, response: Response<ArrayList<Repo>>
            ) {
                if (response.isSuccessful) {
                    hasMore = response.body()?.count() == 30
                    //Log.e("MainActivity", "ListRepo : ${response.body().toString()}")
                    repoAdapter.submitList(repoAdapter.currentList + response.body().orEmpty())
                    //리스트가 들어와야 함
                }
            }

            override fun onFailure(call: Call<ArrayList<Repo>>, t: Throwable) {

            }
        })
    }
}