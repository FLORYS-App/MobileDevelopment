package com.example.florys_app.ui.result

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.florys_app.R
import com.example.florys_app.databinding.ActivityResultBinding
import com.example.florys_app.utils.ImageClassifierHelper
import org.tensorflow.lite.task.vision.classifier.Classifications

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            percentage <= 30 -> "Tanaman sedang sakit"
            percentage <= 60 -> "Tanaman perlu perhatian"
            percentage <= 90 -> "Tanaman anda sehat"
            else -> "Tanaman sangat sehat"
        }
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
    }
}
