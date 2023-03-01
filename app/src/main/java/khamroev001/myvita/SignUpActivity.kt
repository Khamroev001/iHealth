package khamroev001.myvita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
class SignUpActivity : AppCompatActivity() {

    lateinit var username: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var createAcc: MaterialButton

    var usersList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var type = object : TypeToken<List<User>>() {}.type
        var gson = Gson()
        var sh = getSharedPreferences("register", MODE_PRIVATE)
        var edit = sh.edit()
        setContentView(R.layout.activity_sign_up)
        loadElements()

        val login = findViewById<TextView>(R.id.textView6)
        val logIntent = Intent(this, LoginActivity::class.java)
        login.setOnClickListener { v: View? -> startActivity(logIntent) }

        createAcc.setOnClickListener {
            var str = sh.getString("register", "")
            usersList = if (str == "") {
                mutableListOf<User>()
            } else {
                gson.fromJson(str, type)
            }
            for (i in usersList) {
                if (username.text.toString() == i.name) {
                    Toast.makeText(
                        applicationContext,
                        "this username is not available",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
            }
            if (password.text.toString().length < 8) {
                Toast.makeText(
                    applicationContext,
                    "password should include more than 8 characters",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
                    Toast.makeText(applicationContext, "your email is not valid", Toast.LENGTH_LONG)
                        .show()
                } else {
                    usersList.add(User(username.text.toString(), email.text.toString(), password.text.toString()))
                    var s = gson.toJson(usersList)
                    edit.putString("register", s).apply()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }

    private fun loadElements() {
        username = findViewById(R.id.name_up)
        email = findViewById(R.id.email_up)
        password = findViewById(R.id.password_up)
        createAcc = findViewById(R.id.create_acc)
    }

    }
