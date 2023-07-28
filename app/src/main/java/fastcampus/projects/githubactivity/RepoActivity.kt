package fastcampus.projects.githubactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fastcampus.projects.githubactivity.databinding.ActivityRepoBinding

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val username = intent.getStringExtra("username")
        if (username != null) {
            Log.e("testt",username)
        }
    }
}