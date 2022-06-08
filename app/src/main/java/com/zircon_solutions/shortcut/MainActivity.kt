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
                    val uri = Uri.parse("https://api.whatsapp.com/send?phone=${phone}")
                    val waIntent = Intent(Intent.ACTION_VIEW).setData(uri)
                    startActivity(waIntent)
                    mBinding.phone.setText("")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }
}