package com.example.florys_app.ui.account

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.florys_app.remote.NetworkClient
import com.example.florys_app.data.response.RegisterResponse
import com.example.florys_app.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        sharedPreferences = getSharedPreferences("session", MODE_PRIVATE)

        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding.registerName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.registerEmail.requestFocus()
                true
            } else false
        }

        binding.registerEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.registerPass.requestFocus()
                true
            } else false
        }

        binding.registerPass.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                registerUser()
                true
            } else false
        }

        binding.buttonSignup.setOnClickListener {
            registerUser()
        }

        binding.haveSignup.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun observeViewModel() {
        viewModel.email.observe(this) {
            binding.registerEmail.setText(it)
        }
        viewModel.password.observe(this) {
            binding.registerPass.setText(it)
        }
        viewModel.username.observe(this) {
            binding.registerName.setText(it)
        }
    }

    private fun registerUser() {
        val username = binding.registerName.text.toString()
        val email = binding.registerEmail.text.toString()
        val password = binding.registerPass.text.toString()
        val confirmPassword = binding.confirmPass.text.toString()

        when {
            username.isEmpty() -> {
                binding.registerName.error = "Name is required"
                binding.registerName.requestFocus()
            }
            email.isEmpty() -> {
                binding.registerEmail.error = "Email is required"
                binding.registerEmail.requestFocus()
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.registerEmail.error = "Invalid email format"
                binding.registerEmail.requestFocus()
            }
            password.isEmpty() -> {
                binding.registerPass.error = "Password is required"
                binding.registerPass.requestFocus()
            }
            password.length < 8 -> {
                binding.registerPass.error = "Password must be at least 8 characters"
                binding.registerPass.requestFocus()
            }
            confirmPassword.isEmpty() -> {
                binding.confirmPass.error = "Confirm password is required"
                binding.confirmPass.requestFocus()
            }
            confirmPassword != password -> {
                binding.confirmPass.error = "Passwords do not match"
                binding.confirmPass.requestFocus()
            }
            else -> {
                val registerRequest = RegisterRequest(username, email, password, confirmPassword)
                val call = NetworkClient.apiInterface.registerUser(registerRequest)

                binding.buttonSignup.isEnabled = false

                call.enqueue(object : Callback<RegisterResponse> {
                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        binding.buttonSignup.isEnabled = true
                        Log.e("API Error", "Failure: ${t.message}")
                        Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                        binding.buttonSignup.isEnabled = true
                        showLoading(false)
                        if (response.isSuccessful) {
                            response.body()?.let { registerResponse ->
                                if (!registerResponse.error) {
                                    Toast.makeText(this@RegisterActivity, registerResponse.message, Toast.LENGTH_SHORT).show()
                                    val editor = sharedPreferences.edit()
                                    editor.putBoolean("isRegistered", true)
                                    editor.apply()

                                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(this@RegisterActivity, registerResponse.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Log.e("API Error", "Code: ${response.code()}, Message: ${response.errorBody()?.string()}")
                            Toast.makeText(this@RegisterActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                } )
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}