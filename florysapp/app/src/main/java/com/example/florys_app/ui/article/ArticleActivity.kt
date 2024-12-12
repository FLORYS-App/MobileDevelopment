package com.example.florys_app.ui.article

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.florys_app.R
import com.example.florys_app.databinding.ActivityArticleBinding
import com.example.florys_app.databinding.ActivityFlowerBinding

class ArticleActivity : AppCompatActivity() {
    private lateinit var vBinding: ActivityArticleBinding // Make sure this matches your layout binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(vBinding.root)

        // Set up the topAppBar with a navigation icon
        vBinding.topAppBar.setNavigationIcon(R.drawable.round_keyboard_arrow_left_24) // Set your back icon here
        vBinding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
            onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}