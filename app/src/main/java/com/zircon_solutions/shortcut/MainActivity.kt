package com.zircon_solutions.shortcut

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.zircon_solutions.shortcut.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.submit.setOnClickListener {
            val phone = mBinding.phone.text.toString().trim()
            if (phone.isEmpty()) {
                mBinding.phone.error = "Enter Phone Number"
                mBinding.phone.requestFocus()
            } else {
                try {
                    val waIntent = Intent(Intent.ACTION_SENDTO)
                    waIntent.type = "text/plain"
//                    waIntent.setPackage("com.whatsapp")
                    waIntent.setPackage("com.whatsapp.w4b")
                    waIntent.data = Uri.parse("smsto: $phone")
                    startActivity(waIntent)
                    mBinding.phone.setText("")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }
}