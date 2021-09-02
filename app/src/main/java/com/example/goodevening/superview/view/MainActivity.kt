package com.example.goodevening.superview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.goodevening.R
import com.example.goodevening.databinding.ActivityMainBinding
import com.example.goodevening.superview.view.mainview.MainFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult

class MainActivity : AppCompatActivity() {
    lateinit var  binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState.let { supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance()).commit() }

        getClientID()
    }

    private fun getClientID() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener<InstanceIdResult> { task ->
                if(!task.isSuccessful){
                    Log.d("mylogs", task.exception.toString())
                    return@OnCompleteListener
                } else{
                    val token : String = task.result!!.token
                    Log.d("mylogs", "$token")
                }
            })
    }
}