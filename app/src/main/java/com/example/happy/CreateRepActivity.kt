package com.example.happy

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateRepActivity : AppCompatActivity() {

    private var etName: EditText? = null;
    private var btnCreateRep: Button? = null;
    private var mProgressBar: ProgressDialog? = null;

    private var mDatabaseReference: DatabaseReference? = null;
    private var mDatabase: FirebaseDatabase? = null;
    private var mAuth: FirebaseAuth? = null;

    private var TAG = "CreateRepActivity";

    private var name: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_rep)
        initialize();
    }

    private fun initialize()
    {
        etName = findViewById(R.id.et_name) as EditText;
        btnCreateRep = findViewById(R.id.btn_save_new_rep) as Button;

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase!!.reference!!.child("Users");
        mAuth = FirebaseAuth.getInstance();

        //btnCreateRep!!.setOnClickListener{ createNewRep() }
    }
}
