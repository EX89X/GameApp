package com.example.gameapp.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gameapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth


class Sign_Up : AppCompatActivity() {


    lateinit var binding: ActivitySignUpBinding
    private lateinit var firbaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firbaseAuth=FirebaseAuth.getInstance()
        binding.signBtn.setOnClickListener{
               val name=binding.editName.text.toString()
               val email=binding.editEmail.text.toString()
               val password=binding.editPassword.text.toString()
               val conFirmpass=binding.editConfirmpass.text.toString()

            if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && conFirmpass.isNotEmpty()){
                if(password==conFirmpass){
                    firbaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent= Intent(this, Login::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                        }
                    }
                  }else{
                    Toast.makeText(this@Sign_Up,"Password dose not matched",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@Sign_Up,"Fields cannot be empty",Toast.LENGTH_LONG).show()
            }
        }
          binding.Redirectlogin.setOnClickListener{
                 val loginIntent=Intent(this, Login::class.java)
                  startActivity(loginIntent)
          }
    }
}