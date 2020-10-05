package com.example.happy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login_or_new_account.*

class LoginOrNewAccountActivity : AppCompatActivity() {

    private var btnGoToLogin: Button? = null;
    private var btnGoToCreateAccount: Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_or_new_account)

        initialize();
    }

    private fun initialize()
    {
        btnGoToLogin = findViewById(R.id.btn_go_to_login) as Button;
        btnGoToCreateAccount = findViewById(R.id.btn_go_to_new_account) as Button;

        btnGoToLogin!!.setOnClickListener{ changeToLogin() }
        btnGoToCreateAccount!!.setOnClickListener{ changeToCreateAccount() }
    }

    fun changeToLogin()
    {
        val intent = Intent(this, LoginActivity::class.java)

        Handler().postDelayed(
            {
                intent.change();
            }, 1000
        )
    }

    fun changeToCreateAccount()
    {
        val intent = Intent(this, CreateAccountActivity::class.java)

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
