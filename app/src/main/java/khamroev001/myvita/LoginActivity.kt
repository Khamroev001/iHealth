package khamroev001.myvita

import android.widget.TextView
import android.view.View
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoginActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var btn: MaterialButton
    var usersList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sign = findViewById<TextView>(R.id.textView6)
        val signIntent = Intent(this, SignUpActivity::class.java)
        sign.setOnClickListener { v: View? -> startActivity(signIntent) }
        btn=findViewById(R.id.signin)
        email=findViewById(R.id.email)
        pass=findViewById(R.id.password)

        var type = object : TypeToken<List<User>>() {}.type
        var gson = Gson()
        var sh = getSharedPreferences("register", MODE_PRIVATE)
        var edit = sh.edit()

        btn.setOnClickListener {
            println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
            var str = sh.getString("register", "")
            usersList = if (str == "") {
                mutableListOf<User>()
            } else {
                gson.fromJson(str, type)
            }

            for (i in usersList) {
                if (email.text.toString() == i.gmail) {
                    if (i.password == pass.text.toString()) {
                        startActivity(Intent(this, HelloActivity::class.java))
                    }
                } else {
                    Toast.makeText(applicationContext, "There is no one with such ", Toast.LENGTH_LONG).show()
                }
            }


    }
}}