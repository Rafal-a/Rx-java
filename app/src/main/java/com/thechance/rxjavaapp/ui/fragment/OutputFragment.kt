package com.thechance.rxjavaapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import com.thechance.rxjavaapp.util.Constant
import com.thechance.rxjavaapp.databinding.FragmentOutputBinding


class OutputFragment : BaseFragment<FragmentOutputBinding>() {
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