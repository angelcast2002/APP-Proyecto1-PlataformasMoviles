package com.example.fordogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cargarVista()
    }


    private fun cargarVista() {
        setContentView(R.layout.activity_main)
    }
}