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

        var type = object : TypeToken<List<User>>() {}.type
        var gson = Gson()
        var sh = getSharedPreferences("login", MODE_PRIVATE)
        var edit = sh.edit()
        var status:Boolean
        var str = sh.getString("user", "")

       status=(str=="")

        if (status){
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
                startActivity(Intent(this,SignUpActivity::class.java))
            }, 3000)
        }else {
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
                startActivity(Intent(this,PIN::class.java))
            }, 3000)
        }

    }
}