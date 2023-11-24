package com.example.billsmanagement.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.billsmanagement.R
import com.example.billsmanagement.databinding.ActivityLoginBinding
import com.example.billsmanagement.model.LoginRequest
import com.example.billsmanagement.model.LoginResponse
import com.example.billsmanagement.model.RegisterRequest
import com.example.billsmanagement.utils.Constants
import com.example.billsmanagement.viewmodel.BillsViewModel
import com.example.billsmanagement.viewmodel.LoginViewModel
import com.example.billsmanagement.viewmodel.UserViewModel

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val loginViewModel: LoginViewModel by viewModels()
    val billsViewModel:BillsViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
   override fun onResume() {
       super.onResume()
       setContentView(binding.root)
       binding.btnLogin.setOnClickListener {
           validateLogin()

       loginViewModel.logLiveData.observe(this, Observer { loginResponse ->
           persistLogin(loginResponse)
           //    triggering the downloads and the updates in the summary fragment fetching the remote bills
           billsViewModel.fetchRemoteBills()
           binding.pdLogin.visibility = View.GONE
           Toast.makeText(this, loginResponse.message, Toast.LENGTH_LONG).show()
           val intent = Intent(this, HomePage::class.java)
           startActivity(intent)
           finish()
       })
       }

       loginViewModel.errorLiveData.observe(this, Observer { err->
           Toast.makeText(this,err, Toast.LENGTH_LONG).show()
           binding.pdLogin.visibility= View.GONE
       })

   }

       fun validateLogin() {
//           get the texts of the text fields and stores then in the variables
           val email = binding.etEmail.text.toString()
           val password = binding.etPassword.text.toString()
           var error = false
           if (email.isBlank()) {
               binding.tilEmail.error = "Email required"
               error = true
           }
           if (password.isBlank()) {
               binding.tilPassword.error = "Password required"
               error = true
           }
           if (!error){
//            names parameters
               val loginRequest= LoginRequest(
                   email=email,
                   password=password,
               )
               binding.pdLogin.visibility= View.VISIBLE
               loginViewModel.loginUser(loginRequest)
           }
       }
    fun persistLogin(loginResponse: LoginResponse){
        val sharedPrefs=getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE)
        val editor=sharedPrefs.edit()
        editor.putString(Constants.USER_ID,loginResponse.user_id)
        editor.putString(Constants.ACCESS_TOKEN,loginResponse.access_token)
        editor.apply()
    }
   }

//essyj jessy@gmail.com