package com.example.florys_app.ui.result

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.florys_app.MainActivity
import com.example.florys_app.R
import com.example.florys_app.databinding.ActivityResultBinding
import com.example.florys_app.ui.scan.ScanActivity
import com.example.florys_app.utils.ImageClassifierHelper
import org.tensorflow.lite.task.vision.classifier.Classifications

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backActivity()

        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        imageUri?.let {
            Log.d("photoPicker", "showImage: $it")
            binding.resultImage.setImageURI(it)
        }

        val imageClassifierHelper = ImageClassifierHelper(
            context = this,
            classifierListener = object : ImageClassifierHelper.ClassifierListener {
                override fun onError(error: String) {
                    Log.d("photoPicker", "Error analyzing image: $error")
                    showToast("Error: $error")
                }

                @SuppressLint("SetTextI18n")
                override fun onResults(results: List<Classifications>?, inferenceTime: Long) {
                    results?.let {
                        val topResult = it[0]
                        val label = topResult.categories[0].label
                        val score = topResult.categories[0].score

                        binding.resultText.text = "Health: ${String.format("%.2f", score * 100)}% \n${score.evaluateHealthStatus()}"
                    }
                }
            }
        )
        imageClassifierHelper.classifyStaticImage(imageUri)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun Float.evaluateHealthStatus(): String {
        val percentage = this * 100
        return when {
            percentage <= 100 && percentage >= 90 -> "Tanaman Anda sangat sehat dan berada dalam kondisi optimal! Pertahankan pola perawatan seperti penyiraman, pencahayaan, dan pemberian nutrisi. Anda sudah melakukan perawatan yang luar biasa!"
            percentage <= 89 && percentage >= 80 -> "Tanaman Anda sehat dan berkembang baik. Pastikan untuk tetap memantau kelembapan tanah dan memastikan tanaman mendapatkan cukup cahaya. Jangan lupa untuk rutin membersihkan daun dari debu."
            percentage <= 79 && percentage >= 70 -> "Tanaman Anda kurang sehat. Periksa media tanam, tingkat kelembapan, dan kemungkinan hama."
            percentage <= 69 && percentage >= 60 -> "Tanaman Anda mulai menunjukkan tanda-tanda stres. Periksa media tanam dan pastikan drainase baik. Tingkatkan pemantauan kelembapan tanah dan perhatikan suhu lingkungan."
            percentage <= 59 && percentage >= 50 -> "Kesehatan tanaman Anda menurun. Periksa akar untuk memastikan tidak ada yang membusuk. Berikan pupuk cair sesuai dosis dan pastikan tidak ada hama yang menyerang."
            percentage <= 49 && percentage >= 40 -> "Tanaman Anda dalam kondisi kurang sehat. Periksa seluruh bagian tanaman untuk menemukan masalah, seperti daun menguning, hama, atau tanah yang terlalu kering atau basah. Segera lakukan langkah perbaikan seperti mengganti media tanam atau menyesuaikan penyiraman."
            percentage <= 39 && percentage >= 30 -> "Tanaman Anda berada dalam kondisi kritis. Segera periksa akarnya, ganti media tanam jika perlu, dan pangkas bagian yang rusak. Berikan pupuk dengan hati-hati dan jauhkan dari sinar matahari langsung."
            percentage <= 29 && percentage >= 20 -> "Tanaman Anda sangat lemah. Pastikan untuk memangkas bagian tanaman yang mati atau rusak. Rendam akar dalam larutan perangsang akar sebelum mengganti media tanam baru. Tempatkan di area yang terlindung dari angin dan cahaya terlalu terang."
            percentage <= 19 && percentage >= 10 -> "Tanaman Anda hampir mati. Coba lakukan langkah penyelamatan, seperti mengganti media tanam sepenuhnya, memeriksa kondisi akar, dan memberikan pupuk khusus perangsang tumbuh. Letakkan di tempat yang lembap dengan cahaya tidak langsung."
            percentage <= 9 && percentage >= 0 -> "Tanaman Anda dalam kondisi sangat buruk dan hampir tidak dapat diselamatkan. Anda bisa mencoba teknik propagasi dengan menyelamatkan bagian tanaman yang masih sehat untuk ditanam kembali. Jika akar dan batang sudah membusuk sepenuhnya, pertimbangkan untuk memulai dengan tanaman baru."
            else -> "Tanaman Anda dalam kondisi buruk. Segera ganti media tanam, periksa akarnya, dan berikan pupuk yang sesuai."
        }
    }
    private fun backActivity() {
        binding.waterme.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
    }
}