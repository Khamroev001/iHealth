package khamroev001.myvita

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khamroev001.myvita.databinding.ActivityLanguagePrivacyBinding
import khamroev001.myvita.model.User
import java.util.*

class LanguagePrivacy : AppCompatActivity() {
    lateinit var binding: ActivityLanguagePrivacyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLanguagePrivacyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var type = object : TypeToken<List<User>>() {}.type
        var gson = Gson()
        var sh = getSharedPreferences("login", MODE_PRIVATE)
        var edit = sh.edit()
        var status: Boolean
        var str = sh.getString("user", "")
        status = (str == "")

        ArrayAdapter.createFromResource(
            this,
            R.array.languages,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    1 -> {
                        setLocale("en")
                    }
                    2 -> {
                        setLocale("uz")
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        binding.next.setOnClickListener {
            if (binding.checkbox.isChecked && status) {
                finish()
                startActivity(Intent(this, SignUpActivity::class.java))
            }
            if (binding.checkbox.isChecked && !status) {
                finish()
                startActivity(Intent(this, PIN::class.java))

            } else {
                Toast.makeText(this, "Check the box", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        resources.configuration.setLocale(locale)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
        var refresh = Intent(this, LanguagePrivacy::class.java)
        startActivity(refresh)
    }
}


