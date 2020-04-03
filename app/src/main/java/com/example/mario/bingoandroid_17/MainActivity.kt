package com.example.mario.bingoandroid_17

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var jugar: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jugar = findViewById(R.id.jugar)
        jugar!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.jugar -> {
                val intent = Intent(v.context, Juego::class.java)
                startActivity(intent)
            }
        }
    }
}
