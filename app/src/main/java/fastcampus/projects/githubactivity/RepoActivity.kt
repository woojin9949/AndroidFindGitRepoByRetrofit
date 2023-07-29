package fastcampus.projects.githubactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra("username") ?: return

        binding.usernameTextView.text = username
        repoAdapter = RepoAdapter()

        binding.repoRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@RepoActivity)
            adapter = repoAdapter
        }

        listRepo(username)
    }

    private fun listRepo(username: String) {
        val githubService = retrofit.create(GithubService::class.java)

        githubService.listRepos(username).enqueue(object : Callback<ArrayList<Repo>> {
            override fun onResponse(
                call: Call<ArrayList<Repo>>,
                response: Response<ArrayList<Repo>>
            ) {
                if (response.isSuccessful) {
                    //Log.e("MainActivity", "ListRepo : ${response.body().toString()}")
                    repoAdapter.submitList(response.body())
                    //리스트가 들어와야 함
                }
            }

            override fun onFailure(call: Call<ArrayList<Repo>>, t: Throwable) {

            }
        })
    }
}