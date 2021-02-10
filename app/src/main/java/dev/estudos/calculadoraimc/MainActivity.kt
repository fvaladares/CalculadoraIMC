package dev.estudos.calculadoraimc


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.estudos.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val TAG = "bmi_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        initComponents()
    }

    private fun initComponents() {
        title = "BMI Calculator"

        binding.btActivityMainCalc.setOnClickListener {
            actionOfBtActivityMainCalc()
        }

        binding.btActivityMainClear.setOnClickListener {
            actionOfBtActivityMainClear()
        }
    }

    private fun actionOfBtActivityMainClear() {
        binding.etActivityMainHeight.text.clear()
        binding.etActivityMainWeight.text.clear()
    }

    private fun actionOfBtActivityMainCalc() {
        when {
            binding.etActivityMainWeight.text.isBlank() -> {
                Toast.makeText(
                    this,
                    "Please, inform the weight.",
                    Toast.LENGTH_SHORT
                ).show()
                binding.etActivityMainWeight.requestFocus()
            }

            binding.etActivityMainHeight.text.isBlank() -> {
                Toast.makeText(
                    this,
                    "Please, inform the Height.",
                    Toast.LENGTH_SHORT
                ).show()
                binding.etActivityMainHeight.requestFocus()
            }
            else -> {
                val weight = binding.etActivityMainWeight.text.toString().toBigDecimal()
                val height = binding.etActivityMainHeight.text.toString().toBigDecimal()
                val result = BMI().calc(weight, height)
                Log.d("MainActivity", result.toString())
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra(TAG, result.toString())
                }
                startActivity(intent)

            }
        }
    }
}