package com.example.florys_app.ui.scan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class NavScanFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(requireContext(), ScanActivity::class.java)
        startActivity(intent)
        requireActivity().finish() // Hapus fragment dari stack navigasi
    }
}
