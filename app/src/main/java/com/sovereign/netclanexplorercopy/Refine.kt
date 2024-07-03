package com.sovereign.netclanexplorercopy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Refine : AppCompatActivity() {

    private lateinit var dropdown: Spinner
    private lateinit var back: ImageButton
    private lateinit var seekBar: SeekBar
    private lateinit var tv_seekbar_value: TextView
    private lateinit var explore: MaterialButton

    private lateinit var coffee: TextView
    private lateinit var business: TextView
    private lateinit var hobbies: TextView
    private lateinit var friendship: TextView
    private lateinit var movies: TextView
    private lateinit var dinning: TextView
    private lateinit var dating: TextView
    private lateinit var matrimony: TextView

    private val selectedButtons = mutableSetOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_refine)

        dropdown = findViewById(R.id.dropdown_menu)
        back = findViewById(R.id.back)
        seekBar = findViewById(R.id.seekBar)
        tv_seekbar_value = findViewById(R.id.tv_seekbar_value)
        explore = findViewById(R.id.save_and_explore)

        coffee = findViewById(R.id.btn_coffee)
        business = findViewById(R.id.btn_business)
        hobbies = findViewById(R.id.btn_hobbies)
        friendship = findViewById(R.id.btn_friendship)
        movies = findViewById(R.id.btn_movies)
        dinning = findViewById(R.id.btn_dinning)
        dating = findViewById(R.id.btn_dating)
        matrimony = findViewById(R.id.btn_matrimony)

        val items = listOf("Available | Hey Let Us Connect", "Away | Stay Discrete And Watch", "Busy | Do Not Disturb | Will Catch Up Later", "SOS  | Emergency! Need Assistance! HELP")

//        window.statusBarColor = ContextCompat.getColor(this, R.color.topbar)
//        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val adapter = ArrayAdapter<String>(
            this,
            R.layout.spinner_item,
            items
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        dropdown.adapter = adapter
        dropdown.setSelection(0)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tv_seekbar_value.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val buttons = listOf(coffee, business, hobbies, friendship, movies, dinning, dating, matrimony)

        initialSelection(coffee)
        initialSelection(business)
        initialSelection(friendship)

        for (button in buttons) {
            button.setOnClickListener {
                toggleSelection(button)
            }
        }

        explore.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initialSelection(button: TextView) {
        button.background = ContextCompat.getDrawable(this, R.drawable.filled_button)
        button.setTextColor(ContextCompat.getColor(this, R.color.white))
        selectedButtons.add(button)
    }

    private fun toggleSelection(button: TextView) {
        if (selectedButtons.contains(button)) {
            if (selectedButtons.size > 1) {
                button.background = ContextCompat.getDrawable(this, R.drawable.outline_button)
                button.setTextColor(ContextCompat.getColor(this, R.color.text))
                selectedButtons.remove(button)
            }
        } else {
            button.background = ContextCompat.getDrawable(this, R.drawable.filled_button)
            button.setTextColor(ContextCompat.getColor(this, R.color.white))
            selectedButtons.add(button)
        }
    }
}
