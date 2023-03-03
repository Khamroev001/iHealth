package khamroev001.myvita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khamroev001.myvita.model.User

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
        var sh = getSharedPreferences("login", MODE_PRIVATE)
        var edit = sh.edit()
        setContentView(R.layout.activity_sign_up)
        loadElements()


        createAcc.setOnClickListener {
            var str = sh.getString("user", "")
            usersList = if (str == "") {
                mutableListOf<User>()
            } else {
                gson.fromJson(str, type)
            }
            for (i in usersList) {
                if (username.text.toString() == i.name) {
                    println("AAAAAAAAAAAAAAAAAAAA")
                    println(username.text.toString())
                    println(i.name)
                    Toast.makeText(
                        applicationContext, "this username is not available", Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
            }
            if (password.text.toString().length < 8) {
                Toast.makeText(
                    applicationContext,
                    "password should include more than 8 characters",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
                    Toast.makeText(applicationContext, "Email is not valid", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    usersList.add(
                        User(
                            username.text.toString(),
                            email.text.toString(),
                            password.text.toString()
                        )
                    )
                    var s = gson.toJson(usersList)
                    edit.putString("user", s).apply()
                    finish()
                    var intent=Intent(this,PIN::class.java)
                    startActivity(intent)
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
