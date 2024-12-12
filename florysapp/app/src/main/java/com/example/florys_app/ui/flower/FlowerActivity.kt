package com.example.florys_app.ui.flower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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