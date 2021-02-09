package dev.estudos.calculadoraimc

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dev.estudos.calculadoraimc.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        val view = binding.root
        setContentView(view)

        initComponents()
    }

    private fun initComponents() {
        log("initComponents")
        val text = intent.getStringExtra(MainActivity.TAG)
        binding.btActivityResultRedo.setOnClickListener {
            finish()
        }

        if (text != null) {
            showMessage(text)
        } else {
            Toast.makeText(this, "Failure to calc IMC, please, try again.", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
    }

    private fun showMessage(text: String) {
        log("showMessage starting...")
        val messages = resources.getStringArray(R.array.messages).toList()
        messages.forEach {
            log(it)
        }
        val imc = text.toBigDecimal()
        when {
            imc < 17.0.toBigDecimal() -> {
                binding.tvActivityResultImc.setTextColor(getColor(R.color.red))
                binding.tvActivityResultLongMessage.background =
                    ContextCompat.getDrawable(this, R.drawable.border_alert)
                messages[0].split(":").apply {
                    setFields(text, shortMsg = this.first().trim(), longMsg = this.last().trim())
                }
            }
            imc < 18.5.toBigDecimal() -> {
                binding.tvActivityResultImc.setTextColor(getColor(R.color.yellow_500))
                binding.tvActivityResultLongMessage.background =
                    ContextCompat.getDrawable(this, R.drawable.border_urgent)
                messages[1].split(":").apply {
                    setFields(text, shortMsg = this.first().trim(), longMsg = this.last().trim())
                }

            }
            imc < 25.toBigDecimal() -> {

                messages[2].split(":").apply {
                    setFields(text, shortMsg = this.first().trim(), longMsg = this.last().trim())
                }
            }
            imc < 30.toBigDecimal() -> {
                binding.tvActivityResultLongMessage.background =
                    ContextCompat.getDrawable(this, R.drawable.border_alert)
                binding.tvActivityResultImc.setTextColor(getColor(R.color.yellow_500))
                messages[3].split(":").apply {
                    setFields(text, shortMsg = this.first().trim(), longMsg = this.last().trim())
                }
            }
            else -> {
                binding.tvActivityResultLongMessage.background =
                    ContextCompat.getDrawable(this, R.drawable.border_urgent)
                binding.tvActivityResultImc.setTextColor(getColor(R.color.red))
                messages[4].split(":").apply {
                    setFields(text, shortMsg = this.first().trim(), longMsg = this.last().trim())
                }
            }
        }
        log("showMessage finishing...")
    }

    private fun setFields(imc: String, shortMsg: String, longMsg: String) {
        binding.tvActivityResultImc.text = imc
        binding.tvActivityResultShortMessage.text = shortMsg
        binding.tvActivityResultLongMessage.text = longMsg

    }

    private fun log(msg: String) {
        Log.d(TAG, msg)
    }

    companion object {
        const val TAG = "ResultActivity"
    }
}