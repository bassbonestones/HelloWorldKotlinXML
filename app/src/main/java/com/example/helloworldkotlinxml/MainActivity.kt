package com.example.helloworldkotlinxml

import android.graphics.Color.BLUE
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val CYAN = 0xFF00FFFF.toInt()
        const val DARK_GRAY = 0xFF333333.toInt()
        const val BLACK = 0xFF000000.toInt()
        const val LIGHT_GRAY = 0xFFCCCCCC.toInt()
        const val WHITE = 0xFFFFFFFF.toInt()
    }

    var isDarkMode = false
    private lateinit var mainLayout: LinearLayout
    private lateinit var editTextGreeting: EditText
    private lateinit var editTextName: EditText
    private lateinit var textViewHelloWorld: TextView
    private lateinit var switchToggleDarkMode: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainLayout = findViewById<LinearLayout>(R.id.main)
        editTextGreeting = findViewById<EditText>(R.id.editTextGreeting)
        editTextName = findViewById<EditText>(R.id.editTextName)
        textViewHelloWorld = findViewById<TextView>(R.id.textViewHelloWorld)
        val btnDefaultGreeting = findViewById<Button>(R.id.btnDefaultGreeting)
        val btnClearGreeting = findViewById<Button>(R.id.btnClearGreeting)
        switchToggleDarkMode = findViewById<SwitchCompat>(R.id.switchToggleDarkMode)


        // Initialize the switch based on the saved state
        isDarkMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        switchToggleDarkMode.isChecked = isDarkMode

        val updateTextView = {
            val greeting = editTextGreeting.text.toString()
            val name = editTextName.text.toString()
            var fullGreetingText = if (greeting.isNotBlank()) "$greeting, " else ""
            fullGreetingText += if (name.isNotBlank()) "$name!" else ""
            textViewHelloWorld.text = fullGreetingText;
        }

        // Toggle dark mode
        switchToggleDarkMode.setOnCheckedChangeListener { _, isChecked ->
            isDarkMode = isChecked
            toggleDarkMode()
        }

        editTextGreeting.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateTextView()
            }

            override fun afterTextChanged(p0: Editable?) {
                // do nothing
            }
        })

        editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateTextView()
            }

            override fun afterTextChanged(p0: Editable?) {
                // do nothing
            }

        })

        btnClearGreeting.setOnClickListener {
            editTextGreeting.setText("")
            editTextName.setText("")
        }

        btnDefaultGreeting.setOnClickListener {
            editTextGreeting.setText(getString(R.string.hello))
            editTextName.setText(getString(R.string.world))
        }
    }

    override fun onResume() {
        super.onResume()
        applyCustomTheme()
    }

    private fun toggleDarkMode() {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }



    private fun applyCustomTheme() {
        if (isDarkMode) {
            val darkGradient = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(CYAN, DARK_GRAY) // Cyan to Dark Gray
            )
            mainLayout.background = darkGradient
            editTextGreeting.setBackgroundColor(BLACK)
            editTextGreeting.setTextColor(WHITE)
            editTextName.setBackgroundColor(BLACK)
            editTextName.setTextColor(WHITE)
            textViewHelloWorld.setTextColor(WHITE)
        } else {
            val lightGradient = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(LIGHT_GRAY, CYAN) // Light Gray to Cyan
            )
            mainLayout.background = lightGradient
            editTextGreeting.setBackgroundColor(LIGHT_GRAY)
            editTextGreeting.setTextColor(BLACK)
            editTextName.setBackgroundColor(LIGHT_GRAY)
            editTextName.setTextColor(BLACK)
            textViewHelloWorld.setTextColor(BLUE)
        }
    }
}

