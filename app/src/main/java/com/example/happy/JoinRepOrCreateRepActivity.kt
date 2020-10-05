package com.example.happy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

class JoinRepOrCreateRepActivity : AppCompatActivity() {

    private var btnGoToExistingRep: Button? = null;
    private var btnGoToCreateRep: Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_rep_or_create_rep)

        initialize();
    }

    private fun initialize()
    {
        btnGoToExistingRep = findViewById(R.id.btn_go_to_login) as Button;
        btnGoToCreateRep = findViewById(R.id.btn_go_to_new_account) as Button;

        btnGoToExistingRep!!.setOnClickListener{ changeToExistingRep() }
        btnGoToCreateRep!!.setOnClickListener{ changeToCreateRep() }
    }

    fun changeToExistingRep()
    {
        val intent = Intent(this, LoginActivity::class.java)

        Handler().postDelayed(
            {
                intent.change();
            }, 1000
        )
    }

    fun changeToCreateRep()
    {
        val intent = Intent(this, CreateAccountActivity::class.java)

        Handler().postDelayed(
            {
                intent.change();
            }, 1000
        )
    }

    fun Intent.change() {
        startActivity(this)
        finish();
    }
}
