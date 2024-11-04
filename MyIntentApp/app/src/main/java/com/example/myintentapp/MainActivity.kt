package com.example.myintentapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.provider.MediaStore
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle insets for edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMoveActivity = findViewById<Button>(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener {
            onClick()
        }
        val btnDialNumber = findViewById<Button>(R.id.btn_dial_number)
        btnDialNumber.setOnClickListener {
            onDial()
        }

        val btnOpenWebsite = findViewById<Button>(R.id.btn_open_website)
        btnOpenWebsite.setOnClickListener {
            openWebsite()
        }

        val btnOpenCamera = findViewById<Button>(R.id.btn_open_camera)
        btnOpenCamera.setOnClickListener {
            openCamera()
        }
    }


    private fun onClick() {
        val intent = Intent(this, MoveActivity::class.java)
        startActivity(intent)
    }
    private fun onDial(){
        val dialNumber = "08123456789"
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + dialNumber))
        startActivity(intent)
    }
    private fun openWebsite() {
        val url = "https://www.google.com/search?q=pinterest"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show()
        }
    }
}
