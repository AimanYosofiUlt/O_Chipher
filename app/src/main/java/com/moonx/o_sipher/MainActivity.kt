package com.moonx.o_sipher

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.text.method.KeyListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.moonx.o_sipher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var _bd: ActivityMainBinding? = null
    val bd get() = _bd!!

    var itsInfoMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bd = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bd.root)
        supportActionBar?.hide()

        initEvent()
    }

    @SuppressLint("NewApi")
    private fun initEvent() {
        bd.clearPlBtn.setOnClickListener {
            bd.plainTextEd.setText("")
        }

        bd.clearCiBtn.setOnClickListener {
            bd.cipherTextTv.text = ""
        }

        bd.copyPlBtn.setOnClickListener {
            copyText("PlainText", bd.plainTextEd.text.toString())
        }

        bd.copyCiBtn.setOnClickListener {
            copyText("CipherText", bd.cipherTextTv.text.toString())
        }

        bd.pasetCiBtn.setOnClickListener {
            val str = (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
                .primaryClip?.getItemAt(0)?.text.toString()

            var ciStr = ""
            for (c in str) {
                if (c.equals('O') || c.equals('Ο') || c.equals('О'))
                    ciStr += c
            }

            bd.cipherTextTv.text = ciStr
        }
        bd.pastePlBtn.setOnClickListener {
            bd.plainTextEd.setText(
                (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
                    .primaryClip?.getItemAt(0)?.text
            )
        }

        bd.encryptBtn.setOnClickListener {
            val cipher = Cipher()
            val str = bd.plainTextEd.text.toString()
            bd.cipherTextTv.text = cipher.encrypt(str)
        }

        bd.decryptBtn.setOnClickListener {
            val cipher = Cipher()
            val str = bd.cipherTextTv.text.toString()
            bd.plainTextEd.setText(cipher.decrypt(str))
        }
        bd.exitBtn.setOnClickListener {
            this.finish()
        }

        bd.view3.setOnClickListener {
            if (Settings.canDrawOverlays(this)) {
                showFloatingBubble()
            } else {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                forResultLauncher.launch(intent)
            }
        }
    }



    private fun showFloatingBubble() {
        startService(Intent(this, FloatingService::class.java))
    }


    private fun copyText(title: String, str: String) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(title, str)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "$title Copied", Toast.LENGTH_SHORT).show();
    }

    override fun onDestroy() {
        super.onDestroy()
        _bd = null
    }

    override fun onBackPressed() {
        if (itsInfoMode.not()) {
            bd.plainTextEd.tag = bd.plainTextEd.keyListener
            bd.plainTextEd.keyListener = null
            bd.motionLayout.transitionToEnd()
        } else {
            bd.plainTextEd.keyListener = bd.plainTextEd.tag as KeyListener
            bd.motionLayout.transitionToStart()
        }
        itsInfoMode = itsInfoMode.not()
    }

    private val forResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            showFloatingBubble()
        }
    }
}