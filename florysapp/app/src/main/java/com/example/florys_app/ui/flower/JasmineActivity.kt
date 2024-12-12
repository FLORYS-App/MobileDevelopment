package com.example.florys_app.ui.flower

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.florys_app.R
import com.example.florys_app.databinding.ActivityJasmineBinding
import com.example.florys_app.databinding.ActivityOrchidBinding

class JasmineActivity : AppCompatActivity() {
    private lateinit var vBinding: ActivityJasmineBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityJasmineBinding.inflate(layoutInflater)
        setContentView(vBinding.root)

        // Set up the topAppBar with a navigation icon
        vBinding.topAppBar.setNavigationIcon(R.drawable.round_keyboard_arrow_left_24)
        vBinding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
            onBackPressed()
        }
    }
}