package com.thechance.rxjavaapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thechance.rxjavaapp.databinding.FragmentOutputBinding


class OutputFragment :BaseFragment<FragmentOutputBinding>() {
    private var value =""

    override val LOG_TAG: String
        get() = javaClass.simpleName
    override val bindingInflater: (LayoutInflater) -> FragmentOutputBinding = FragmentOutputBinding::inflate
    override fun onCreate(savedInstanceState: Bundle?) {
        value= arguments?.getString(Constant.KEY,).toString()
        super.onCreate(savedInstanceState)
    }
    override fun setup() {
        binding.let {
          it?.showText?.text = value
        }

    }

    override fun addCallBack() {
    }


}