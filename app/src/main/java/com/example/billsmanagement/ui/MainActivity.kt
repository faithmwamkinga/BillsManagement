package com.example.billsmanagement.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.billsmanagement.databinding.ActivityMainBinding
import com.example.billsmanagement.utils.Constants

class MainActivity : AppCompatActivity() {
//    the binding property is used to access the views in the activity xml
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
//        Handler().postDelayed({
//            val intent = Intent(this@MainActivity, Login::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//            finish()
//        },3000)

        redirectUser()
    }
    fun redirectUser(){
        val sharedPrefs=getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE)
        val userId=sharedPrefs.getString(Constants.USER_ID,Constants.EMPTY_STRING)
        if (userId.isNullOrBlank()){
            startActivity(Intent(this,Login::class.java))

        }
        else{
            startActivity(Intent(this,HomePage::class.java))
        }
        finish()
    }
}
//        The onCreate() method for the MainActivity activity is called when the activity is first created. In this method,
//        the binding property is initialized, the content view is set, and the action bar is hidden.
//        Then, the Handler() object is used to delay the start of the SplashScreen activity for 6000 milliseconds (6 seconds).
//        The support action bar is a widget that is used to provide a navigation bar at the top of an Android activity.
//        The support action bar can be used to display the activity's title, a list of options, and a search bar.
//        the handler object is used to delay the start of the splash screen.It is a class that is used to schedule tasks to be executed for later.

