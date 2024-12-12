package com.example.florys_app.ui.flower

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.florys_app.R
import com.example.florys_app.databinding.ActivityFlowerBinding

class FlowerActivity : AppCompatActivity() {
    // Assuming vBinding is initialized properly
    private lateinit var vBinding: ActivityFlowerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityFlowerBinding.inflate(layoutInflater)
        setContentView(vBinding.root)

        // Set up the topAppBar with a navigation icon
        vBinding.topAppBar.setNavigationIcon(R.drawable.round_keyboard_arrow_left_24)
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


/*
class FlowerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlowerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setupToolbar()

        //val event = intent.getParcelableExtra<FlowerEntity>("EXTRA_EVENT")
        //event?.let { setupEventDetails(it) }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    /*
    private fun setupEventDetails(event: FlowerEntity) {
        binding.apply {
            updateFavoriteIcon(event.isFavorite)

            titleTextView.text = event.name
            subtitleTextView.text = getString
            descriptionTextView.apply {
                text = Html.fromHtml(event.description, Html.FROM_HTML_MODE_COMPACT)
                movementMethod = LinkMovementMethod.getInstance()
            }
            Glide.with(this@FlowerActivity).load(event.mediaCover).into(imageView)

            //favoriteFab.setOnClickListener { toggleFavorite(event) }
            //webpageFab.setOnClickListener { openWebPage(event.link) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

     */

}

 */