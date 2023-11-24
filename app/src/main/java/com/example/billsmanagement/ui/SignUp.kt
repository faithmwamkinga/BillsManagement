package com.example.billsmanagement.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.billsmanagement.R
import com.example.billsmanagement.databinding.ActivitySignUpBinding
import com.example.billsmanagement.model.RegisterRequest
import com.example.billsmanagement.viewmodel.UserViewModel

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels()

    fun isLoggedIn(context: Context):Boolean {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("is_logged_in", false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnSignUp.setOnClickListener {
                    validateRegistration()
                }

        }

    override fun onResume() {
        super.onResume()
        setContentView(binding.root)
        binding.btnSignUp.setOnClickListener {
            validateRegistration()
        }
        userViewModel.errorLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbRegister.visibility = View.GONE
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        })

        userViewModel.regLiveData.observe(this, Observer { regResponse ->
            binding.pbRegister.visibility = View.GONE
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        })
    }

    fun validateRegistration() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirm = binding.etConfirm.text.toString()
        var error = false
        if (firstName.isBlank()) {
            binding.tilFirstName.error = (getString(R.string.first_name_required))
            error = true
        }
        if (lastName.isBlank()) {
            binding.tilLastName.error = getString(R.string.last_name_required)
            error = true
        }
        if (phoneNumber.isBlank()) {
            binding.tilPhoneNumber.error = getString(R.string.phonenumber_required)
            error = true
        }
        if (email.isBlank()) {
            binding.tilEmail.error = getString(R.string.email_required)
            error = true
        }

        if (password.isBlank()) {
            binding.tilPassword.error = getString(R.string.password_required)
            error = true
        }
        if (confirm != password) {
            binding.tilConfirm.error = getString(R.string.try_again)
            error = true
        }
        if (!error) {
//            names parameters
            val registerRequest = RegisterRequest(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password,
                phoneNumber = phoneNumber
            )

            binding.pbRegister.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)

        }
    }
}


//Fix date formatting











