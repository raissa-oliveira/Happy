package com.example.happy

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import android.view.Window
import android.view.WindowManager
import com.example.happy.R.layout.activity_login
import androidx.core.content.ContextCompat

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            window.setStatusBarColorTo(R.color.colorPrimary)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun Window.setStatusBarColorTo(color: Int)
    {
        this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.statusBarColor = ContextCompat.getColor(baseContext, color)
    }

    fun changeToForgotPassword()
    {
        val intent = Intent(this, ForgotPasswordActivity::class.java)

        Handler().postDelayed(
            {
                intent.change();
            }, 1000
        )
    }

    fun Intent.change()
    {
        startActivity(this)
        finish();
    }
}
