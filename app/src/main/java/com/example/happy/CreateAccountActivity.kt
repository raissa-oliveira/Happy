package com.example.happy

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateAccountActivity : AppCompatActivity() {

    private var etName: EditText? = null;
    private var etEmail: EditText? = null;
    private var etTel: EditText? = null;
    private var etPassword: EditText? = null;
    private var etConfirmPassword: EditText? = null;
    private var btnCreateAccount: Button? = null;
    private var mProgressBar: ProgressDialog? = null;

    private var mDatabaseReference: DatabaseReference? = null;
    private var mDatabase: FirebaseDatabase? = null;
    private var mAuth: FirebaseAuth? = null;

    private var TAG = "CreateAccountActivity";

    private var name: String? = null;
    private var email: String? = null;
    private var tel: String? = null;
    private var password: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initialize();
    }

    private fun initialize()
    {
        etName = findViewById(R.id.et_name) as EditText;
        etEmail = findViewById(R.id.et_email) as EditText;
        etTel = findViewById(R.id.et_tel) as EditText;
        etPassword = findViewById(R.id.et_password) as EditText;
        etConfirmPassword = findViewById(R.id.et_confirm_password) as EditText;
        btnCreateAccount = findViewById(R.id.btn_save_new_account) as Button;

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase!!.reference!!.child("Users");
        mAuth = FirebaseAuth.getInstance();

        btnCreateAccount!!.setOnClickListener{ createNewAccount() }


    }

    private fun createNewAccount()
    {
        name = etName?.text.toString();
        email = etEmail?.text.toString();
        tel = etTel?.text.toString();
        password = etPassword?.text.toString();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(tel) && !TextUtils.isEmpty(password))
        {
            if(password != etConfirmPassword?.text.toString())
            {
                Toast.makeText(this, "As senhas não estão iguais", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Os Campos foram preenchidos corretamente", Toast.LENGTH_SHORT).show()
            }


        }
        else
        {
            Toast.makeText(this, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
        }

        mProgressBar!!.setMessage("Registrando Usuário");
        mProgressBar!!.show();

        mAuth!!.createUserWithEmailAndPassword(email!!, password!!).addOnCompleteListener(this)
        {
            task -> mProgressBar!!.hide();
            if(task.isSuccessful())
            {
                Log.d(TAG, "CreateUserWithEmailAndPassword:Sucess")
                val userId = mAuth!!.currentUser!!.uid;

                verifyEmail();

                val currentUserDb = mDatabaseReference!!.child(userId);
                currentUserDb.child("name").setValue(name);
                currentUserDb.child("tel").setValue(tel);

                updateUserInfoAndUi();
            }
            else
            {
                Log.w(TAG, "CreateUserWithEmail", task.exception)
                Toast.makeText(this@CreateAccountActivity, "A autenticação falhou.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun updateUserInfoAndUi()
    {
        val intent = Intent(this@CreateAccountActivity, MainActivity::class.java);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private fun verifyEmail()
    {
        val mUser = mAuth!!.currentUser;
        mUser!!.sendEmailVerification().addOnCompleteListener(this)
        {
            task ->

            if(task.isSuccessful)
            {
                Toast.makeText(this@CreateAccountActivity, "Um e-mail de verificação foi enviado para "+ mUser.getEmail(), Toast.LENGTH_SHORT).show();
            }
            else
            {
                Log.e(TAG, "SendEmailVerification", task.exception)
                Toast.makeText(this@CreateAccountActivity, "Falha ao enviar o e-mail de verificação", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
