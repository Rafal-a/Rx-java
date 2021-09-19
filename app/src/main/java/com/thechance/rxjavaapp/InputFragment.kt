package com.thechance.rxjavaapp


import android.os.Bundle
import android.view.LayoutInflater

import androidx.core.widget.doOnTextChanged
import com.thechance.rxjavaapp.databinding.ActivityMainBinding
import com.thechance.rxjavaapp.databinding.FragmentInputBinding

import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class InputFragment :BaseFragment<FragmentInputBinding>() {
        override val LOG_TAG: String
            get() = javaClass.simpleName
        override val bindingInflater: (LayoutInflater) -> FragmentInputBinding = FragmentInputBinding::inflate

        override fun setup() {
            foo()

        }

        override fun addCallBack() {

        }

    private fun foo() {
        val observable = PublishSubject.create<String> { emmiter ->
            binding?.inputText?.doOnTextChanged { text, start, before, count ->
                emmiter.onNext(text.toString())

            }.toString()
        }.debounce(1, TimeUnit.SECONDS).publish()


        observable.connect()
        observable.subscribe(::nextValue, ::onError)
    }
    private fun nextValue(next: String) {
        val outputFragment=OutputFragment()
        val bundle = Bundle()
        bundle.putString("key",next)
        outputFragment.arguments= bundle
        outputFragment.binding?.showText?.text = next
        println("~~~~~~~~~~~~~~~~~~\nDATA PASSED TO ACTIVITY :$next\n~~~~~~~~~~~~~~~~~~")

    }
    private fun onError(e:Throwable){
        println("ERROR")
    }
}