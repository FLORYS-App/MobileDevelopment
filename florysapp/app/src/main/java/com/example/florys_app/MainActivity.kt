package com.example.florys_app

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.florys_app.data.response.CheckInCountResponse
import com.example.florys_app.databinding.ActivityMainBinding
import com.example.florys_app.remote.NetworkClient
import com.example.florys_app.ui.account.LoginActivity
import com.example.florys_app.ui.scan.ScanActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("session", MODE_PRIVATE)

        // Validasi sesi sebelum melanjutkan
        val username = sharedPreferences.getString("username", null)
        if (username.isNullOrEmpty()) {
            redirectToLogin()
            return
        } else {
            validateSession(username)
        }

        // Inisialisasi tampilan
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atur Bottom Navigation
        setupBottomNavigation()
    }

    private fun validateSession(username: String) {
        val call = NetworkClient.apiInterface.getCheckInCount(username)
        call.enqueue(object : Callback<CheckInCountResponse> {
            override fun onResponse(
                call: Call<CheckInCountResponse>,
                response: Response<CheckInCountResponse>
            ) {
                if (response.isSuccessful) {
                    val count = response.body()?.checkInCount ?: 0
                    Toast.makeText(this@MainActivity, "Check-in count: $count", Toast.LENGTH_SHORT).show()
                } else {
                    // Jika sesi tidak valid, arahkan ke LoginActivity
                    Toast.makeText(this@MainActivity, "Session expired. Please log in again.", Toast.LENGTH_SHORT).show()
                    redirectToLogin()
                }
            }

            override fun onFailure(call: Call<CheckInCountResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                redirectToLogin()
            }
        })
    }

    private fun redirectToLogin() {
        startActivity(Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }

    private fun setupBottomNavigation() {
        val bottomNav: BottomNavigationView = binding.bottomNav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_water, R.id.nav_chat, R.id.nav_more
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNav.setupWithNavController(navController)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_scan -> {
                    // Buka ScanActivity
                    val intent = Intent(this, ScanActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    // Biarkan navController menangani lainnya
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }
            }
        }
    }
}
