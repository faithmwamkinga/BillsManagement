package com.example.billsmanagement.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.billsmanagement.BuildConfig
import com.example.billsmanagement.databinding.FragmentSettingsBinding
import com.example.billsmanagement.utils.Constants
import com.example.billsmanagement.utils.Utilis.Companion.hide
import com.example.billsmanagement.utils.Utilis.Companion.show
import com.example.billsmanagement.viewmodel.BillsViewModel
import com.google.android.material.snackbar.Snackbar

class SettingsFragment : Fragment() {
    var binding:FragmentSettingsBinding?=null
    val billsViewModel:BillsViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding!!.tvLOgout.setOnClickListener {
            performLogout()
        }

        return binding!!.root
    }
    override fun onResume() {
        super.onResume()
        binding?.tvAppVersion?.text="APP VERSION: ${BuildConfig.VERSION_NAME}"
        binding?.tvLOgout?.setOnClickListener{performLogout()}
        binding?.tvSycData?.setOnClickListener {syncData() }

    }

    private fun performLogout() {
        val sharedPrefs = requireActivity().getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString(Constants.USER_ID,Constants.EMPTY_STRING)
        editor.putString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)
        editor.clear().apply()

        val intent = Intent(requireContext(), Login::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
    }
    fun syncData(){
//        TODO detect network connection
        binding?.pbSync?.show()
        binding?.pbSync?.visibility= View.VISIBLE
        billsViewModel.fetchRemoteBills()
        val timer = object : CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                binding?.pbSync?.hide()
                Snackbar.make(binding!!.settingsRoot,"Sync completed",Snackbar.LENGTH_SHORT).show()
            }
        }
        timer.start()
    }
    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}






