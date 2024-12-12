package com.example.florys_app.ui.dashboard

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.florys_app.R
import com.example.florys_app.ui.account.LoginActivity
import com.example.florys_app.databinding.FragmentDashboardBinding
import com.example.florys_app.ui.about.AboutActivity
import com.example.florys_app.ui.profile.ProfileActivity
import com.example.florys_app.ui.welcome.WelcomePageActivity

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root

        sharedPreferences = requireContext().getSharedPreferences("session", android.content.Context.MODE_PRIVATE)

        binding.profileBtn.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        binding.aboutBtn.setOnClickListener {
            startActivity(Intent(requireContext(), AboutActivity::class.java))
        }

        binding.signOutBtn.setOnClickListener {
            logout()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun logout() {
        sharedPreferences.edit().clear().apply()
        startActivity(Intent(requireContext(), WelcomePageActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        requireActivity().finish()
    }
/*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboard_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

 */

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sign_out_btn -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
