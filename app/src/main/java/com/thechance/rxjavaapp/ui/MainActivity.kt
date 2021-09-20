package com.thechance.rxjavaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.thechance.rxjavaapp.R
import com.thechance.rxjavaapp.databinding.ActivityMainBinding
import com.thechance.rxjavaapp.databinding.FragmentInputBinding
import com.thechance.rxjavaapp.ui.fragment.InputFragment
import com.thechance.rxjavaapp.ui.fragment.OutputFragment
import com.thechance.rxjavaapp.util.Communicator
import com.thechance.rxjavaapp.util.Constant

class MainActivity : AppCompatActivity(), Communicator {
    lateinit var binding: ActivityMainBinding
    private val fragmentInput = InputFragment()
    private val LOG_TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun transferData(text: String) {
        val outputFragment = OutputFragment()

        val bundle = Bundle()
        bundle.putString(Constant.KEY,text)
        outputFragment.arguments= bundle
        sendData(outputFragment)
    }

    //send the data to the output fragment
    private fun sendData(fragment: Fragment) {


        val transaction = this.supportFragmentManager.beginTransaction()
            .remove(fragment)
            .add(R.id.fragment_output, fragment)
            .commit()
        // Log.v(LOG_TAG, "~~~~~~~~~~~~~~~~~~\n FRAGMENT IS CHANGED\n~~~~~~~~~~~~~~~~~~")


    }
}
