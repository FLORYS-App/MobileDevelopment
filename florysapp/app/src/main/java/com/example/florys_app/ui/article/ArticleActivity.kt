package com.example.florys_app.ui.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.florys_app.R
import com.example.florys_app.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {
    private lateinit var vBinding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(vBinding.root)

        vBinding.topAppBar.setNavigationIcon(R.drawable.round_keyboard_arrow_left_24)
        vBinding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}