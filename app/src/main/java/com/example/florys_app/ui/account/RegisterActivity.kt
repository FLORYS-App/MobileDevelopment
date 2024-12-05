package com.example.florys_app.ui.account

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
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
/*
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
        binding.edRegisterName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.edRegisterEmail.requestFocus()
                true
            } else false
        }

        binding.edRegisterEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.edRegisterPassword.requestFocus()
                true
            } else false
        }

        binding.edRegisterPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                registerUser()
                true
            } else false
        }

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun observeViewModel() {
        viewModel.email.observe(this) {
            binding.edRegisterEmail.setText(it)
        }
        viewModel.password.observe(this) {
            binding.edRegisterPassword.setText(it)
        }
        viewModel.name.observe(this) {
            binding.edRegisterName.setText(it)
        }
    }

    private fun registerUser() {
        val name = binding.edRegisterName.text.toString()
        val email = binding.edRegisterEmail.text.toString()
        val password = binding.edRegisterPassword.text.toString()

        when {
            name.isEmpty() -> {
                binding.edRegisterName.error = "Name is required"
                binding.edRegisterName.requestFocus()
            }
            email.isEmpty() -> {
                binding.edRegisterEmail.error = "Email is required"
                binding.edRegisterEmail.requestFocus()
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.edRegisterEmail.error = "Invalid email format"
                binding.edRegisterEmail.requestFocus()
            }
            password.isEmpty() -> {
                binding.edRegisterPassword.error = "Password is required"
                binding.edRegisterPassword.requestFocus()
            }
            password.length < 8 -> {
                binding.edRegisterPassword.error = "Password must be at least 8 characters"
                binding.edRegisterPassword.requestFocus()
            }
            else -> {
                val registerRequest = RegisterRequest(name, email, password)
                val call = NetworkClient.apiInterface.registerUser(registerRequest)

                binding.btnRegister.isEnabled = false

                call.enqueue(object : Callback<RegisterResponse> {
                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        binding.btnRegister.isEnabled = true
                        Log.e("API Error", "Failure: ${t.message}")
                        Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                        binding.btnRegister.isEnabled = true
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


 */
}
