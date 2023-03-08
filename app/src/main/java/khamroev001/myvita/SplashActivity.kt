package khamroev001.myvita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khamroev001.myvita.model.User

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       Handler(Looper.getMainLooper()).postDelayed({
           var intent=Intent(this,LanguagePrivacy::class.java)
           startActivity(intent)
           finish()

       },3000)

    }
}