package com.sibelyiildz.darkmodesample

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    companion object {
        const val SELECTED_THEME = "selected_theme"
    }

    private lateinit var sharedPRef: SharedPreferences
    private lateinit var prefEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPRef = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        prefEditor = sharedPRef.edit()

        val currentSelectedTheme = sharedPRef.getInt(SELECTED_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(currentSelectedTheme)

        when (currentSelectedTheme) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                findViewById<RadioButton>(R.id.light).isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                findViewById<RadioButton>(R.id.night).isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
                findViewById<RadioButton>(R.id.systemDefault).isChecked = true
            }
        }

        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { _, radioButton ->
            when (radioButton) {
                findViewById<RadioButton>(R.id.light).id -> {
                    setThemes(AppCompatDelegate.MODE_NIGHT_NO)
                }
                findViewById<RadioButton>(R.id.night).id -> {
                    setThemes(AppCompatDelegate.MODE_NIGHT_YES)
                }
                findViewById<RadioButton>(R.id.systemDefault).id -> {
                    setThemes(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        }
    }

    private fun setThemes(theme: Int) {
        prefEditor.putInt(SELECTED_THEME, theme)
        AppCompatDelegate.setDefaultNightMode(theme)
        prefEditor.apply()
    }
}