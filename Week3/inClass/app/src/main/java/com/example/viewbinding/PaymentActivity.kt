package com.example.viewbinding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbinding.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding
    private var selectedPaymentMethod = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the binding
        binding = ActivityPaymentBinding.inflate(layoutInflater)

        // Set content view with root view from binding
        setContentView(binding.root)

        // Get data from Intent
        val username = intent.getStringExtra("username") ?: "tung"
        val courses = intent.getStringArrayListExtra("courses") ?: arrayListOf()

        binding.textViewTitle.text = "Xin chào $username\nHãy đăng ký môn học"

        // Handle RadioButton selection to change image
        binding.radioGroupPayment.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.radioBitcoin.id -> {
                    selectedPaymentMethod = "Bitcoin"
                    // Change image to Bitcoin (you need to add bitcoin.png to drawable folder)
                    // binding.imageViewCrypto.setImageResource(R.drawable.bitcoin)
                    binding.imageViewCrypto.setImageResource(R.drawable.ic_launcher_foreground)
                }
                binding.radioEthereum.id -> {
                    selectedPaymentMethod = "Ethereum"
                    // Change image to Ethereum (you need to add ethereum.png to drawable folder)
                    // binding.imageViewCrypto.setImageResource(R.drawable.ethereum)
                    binding.imageViewCrypto.setImageResource(R.drawable.ic_launcher_foreground)
                }
            }
        }

        // Handle ImageButton Exit click
        binding.imageButtonExit.setOnClickListener {
            if (selectedPaymentMethod.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Thanh toán thành công với $selectedPaymentMethod",
                    Toast.LENGTH_LONG
                ).show()

                // Finish activity and return to Course Registration
                finish()
            }
        }
    }
}
