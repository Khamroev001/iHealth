package khamroev001.myvita

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.core.os.postDelayed
import khamroev001.myvita.databinding.ActivityPinBinding

class PIN : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityPinBinding
    var clickcount = 0
    var pin = ""
    lateinit var sh: SharedPreferences
    lateinit var edit: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sh = getSharedPreferences("login", MODE_PRIVATE)
        edit = sh.edit()



        binding.dot1.setOnClickListener(this)
        binding.dot3.setOnClickListener(this)
        binding.dot4.setOnClickListener(this)
        binding.dot5.setOnClickListener(this)
        binding.dot6.setOnClickListener(this)
        binding.dot7.setOnClickListener(this)
        binding.dot8.setOnClickListener(this)
        binding.dot9.setOnClickListener(this)
        binding.dot0.setOnClickListener(this)

        binding.delete.setOnClickListener {
            pin.dropLast(0)
            if (clickcount == 1) {
                binding.plot1.setBackgroundResource(R.drawable.cube_border)
                clickcount--
            }
            if (clickcount == 2) {
                binding.plot2.setBackgroundResource(R.drawable.cube_border)
                clickcount--
            }
            if (clickcount == 3) {
                binding.plot3.setBackgroundResource(R.drawable.cube_border)
                clickcount--
            }
            if (clickcount == 4) {
                binding.plot4.setBackgroundResource(R.drawable.cube_border)
                clickcount--
            }
        }

    }

    override fun onClick(p0: View?) {
        var number = findViewById<Button>(p0!!.id)
        clickcount++
        if (clickcount == 1) {
            binding.plot1.setBackgroundResource(R.drawable.cube_border_filled)
            pin += number.text
        }
        if (clickcount == 2) {
            binding.plot2.setBackgroundResource(R.drawable.cube_border_filled)
            pin += number.text
        }
        if (clickcount == 3) {
            binding.plot3.setBackgroundResource(R.drawable.cube_border_filled)
            pin += number.text
        }
        if (clickcount == 4) {
            binding.plot4.setBackgroundResource(R.drawable.cube_border_filled)
            pin += number.text
            stop()

        }
        if (clickcount >= 4) {
            if (sh.getString("pin", "") == "") {
                Handler(Looper.getMainLooper()).postDelayed({
                    edit.putString("pin", pin).apply()
                    finish()
                    startActivity(Intent(this, HelloActivity::class.java))
                }, 500)
            } else {
                if (pin == sh.getString("pin", "")) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        finish()
                        startActivity(Intent(this, HelloActivity::class.java))
                    }, 500)
                } else {
                    pin = ""
                    clickcount = 0
                    restart()
                }
            }

        }
    }

    fun stop() {
        binding.dot1.isClickable = false
        binding.dot2.isClickable = false
        binding.dot3.isClickable = false
        binding.dot4.isClickable = false
        binding.dot5.isClickable = false
        binding.dot6.isClickable = false
        binding.dot7.isClickable = false
        binding.dot8.isClickable = false
        binding.dot9.isClickable = false
        binding.dot0.isClickable = false
    }

    fun restart() {
        binding.dot1.isClickable = true
        binding.dot2.isClickable = true
        binding.dot3.isClickable = true
        binding.dot4.isClickable = true
        binding.dot5.isClickable = true
        binding.dot6.isClickable = true
        binding.dot7.isClickable = true
        binding.dot8.isClickable = true
        binding.dot9.isClickable = true
        binding.dot0.isClickable = true

        binding.plot1.setBackgroundResource(R.drawable.cube_red)
        binding.plot2.setBackgroundResource(R.drawable.cube_red)
        binding.plot3.setBackgroundResource(R.drawable.cube_red)
        binding.plot4.setBackgroundResource(R.drawable.cube_red)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.plot1.setBackgroundResource(R.drawable.cube_border)
            binding.plot2.setBackgroundResource(R.drawable.cube_border)
            binding.plot3.setBackgroundResource(R.drawable.cube_border)
            binding.plot4.setBackgroundResource(R.drawable.cube_border)
        }, 500)
    }

}