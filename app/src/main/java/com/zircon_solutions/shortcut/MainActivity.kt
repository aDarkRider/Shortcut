package com.zircon_solutions.shortcut

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.zircon_solutions.shortcut.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.business.setOnClickListener {
            mBinding.business.isChecked = true
            mBinding.whatsapp.isChecked = false
        }

        mBinding.whatsapp.setOnClickListener {
            mBinding.business.isChecked = false
            mBinding.whatsapp.isChecked = true
        }

        mBinding.ccp.registerCarrierNumberEditText(mBinding.phone)

        mBinding.submit.setOnClickListener {
            val phone = mBinding.phone.text.toString().trim()
            if (phone.isEmpty()) {
                mBinding.phone.error = "Enter Phone Number"
                mBinding.phone.requestFocus()
            } else {
                val phoneNumber = mBinding.ccp.fullNumber
                try {
                    val uri = Uri.parse("https://api.whatsapp.com/send?phone=${phoneNumber}")
                    if (mBinding.business.isChecked) {
                        Toast.makeText(this, "Business", Toast.LENGTH_SHORT).show()
                        val waIntent = Intent(Intent.ACTION_VIEW).setData(uri)
                        waIntent.setPackage("com.whatsapp.w4b")
                        startActivity(waIntent)
                        mBinding.phone.setText("")
                    } else {
                        Toast.makeText(this, "Whatsapp", Toast.LENGTH_SHORT).show()
                        val waIntent = Intent(Intent.ACTION_VIEW).setData(uri)
                        waIntent.setPackage("com.whatsapp")
                        startActivity(waIntent)
                        mBinding.phone.setText("")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }
}