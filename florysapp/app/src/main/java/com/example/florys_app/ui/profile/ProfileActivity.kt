package com.example.florys_app.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.florys_app.R
import com.example.florys_app.data.response.LoginResponse
import com.example.florys_app.databinding.ActivityProfileBinding
import com.example.florys_app.remote.NetworkClient
import com.example.florys_app.ui.account.AuthViewModel
import com.example.florys_app.ui.account.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("session", MODE_PRIVATE)

        // Ambil data dari SharedPreferences
        val username = sharedPreferences.getString("username", "Name not found")
        val email = sharedPreferences.getString("email", "Email not found")
        val userId = sharedPreferences.getString("userId", "UsernId not found")
        val password = sharedPreferences.getString("password", "Password not found")
        //val token = sharedPreferences.getString("token", "Password not found")

        // Tampilkan data di TextView
        binding.nameProfile.text = username
        binding.emailProfile.text = email
        //binding.userIdProfile.text = userId
        binding.passwordProfile.text = password
        //binding.passwordProfile.text = token


        openGallery()
        loadImageFromStorage()
        deleteProfileImage()
    }


        private fun openGallery() {
        binding.btnEdit.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
        }
    }
    private fun saveImage(bitmap: Bitmap) {
        val filename = "profile_image.jpg"
        val directory = File(filesDir, "images")

        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File(directory, filename)

        try {
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
            Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show()

            saveImageFileName(filename)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveImageFileName(fileName: String) {
        val sharedPreferences = getSharedPreferences(NAME_KEY, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("imageFileName", fileName)
        editor.apply()
    }

    private fun loadImageFromStorage() {
        val sharedPreferences = getSharedPreferences(NAME_KEY, MODE_PRIVATE)
        val fileName = sharedPreferences.getString("imageFileName", "")

        if (!fileName.isNullOrEmpty()) {
            val directory = File(filesDir, "images")
            val file = File(directory, fileName)

            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                binding.ivAvatar.setImageBitmap(bitmap)
            } else {
                binding.ivAvatar.setImageResource(R.drawable.profile)
            }
        }
    }

    private fun deleteProfileImage() {
        val filename = "profile_image.jpg"
        val directory = File(filesDir, "images")

        val file = File(directory, filename)
        if (file.exists()) {
            file.delete()
            Toast.makeText(this, "Profile image deleted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Profile image not found", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
        private const val NAME_KEY = "MyPrefs"
    }
}