package com.example.gameapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gameapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {


    private lateinit var binding:ActivityLoginBinding
    private lateinit var firbaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firbaseAuth=FirebaseAuth.getInstance()

        binding.LoginBtn.setOnClickListener{
           val email=binding.editloginemail.text.toString()
           val password=binding.editPasswordemail.text.toString()
            if(email.isNotEmpty()&&password.isNotEmpty()){
                firbaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent=Intent(this,HomePage::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this@Login,"Fields cannot be empty",Toast.LENGTH_LONG).show()
            }
        }
        binding.RedirectSinup.setOnClickListener {
            val signupIntent=Intent(this, Sign_Up::class.java)
            startActivity(signupIntent)
        }
    }
}