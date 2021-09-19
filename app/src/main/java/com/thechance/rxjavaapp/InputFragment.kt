package com.thechance.rxjavaapp


import android.os.Bundle
import android.view.LayoutInflater

import androidx.core.widget.doOnTextChanged
import com.thechance.rxjavaapp.databinding.FragmentInputBinding
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.observables.ConnectableObservable

import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class InputFragment :BaseFragment<FragmentInputBinding>() {
    val outputFragment=OutputFragment()
    override val LOG_TAG: String
            get() = javaClass.simpleName
        override val bindingInflater: (LayoutInflater) -> FragmentInputBinding = FragmentInputBinding::inflate

        override fun setup() {
            getData()

        }

        override fun addCallBack() {

        }

    private fun getData() {
        val observable = PublishSubject.create<String> { emmiter ->
            binding?.inputText?.doOnTextChanged { text, start, before, count ->
                emmiter.onNext(text.toString())

            }.toString()
        }.debounce(1500, TimeUnit.MILLISECONDS).publish()
        connectObservable(observable)

    }
    private fun connectObservable(observable: @NonNull ConnectableObservable<String>){
        observable.connect()
        observable.subscribe(::nextValue, ::onError)
    }
    private fun nextValue(next: String) {
        val bundle = Bundle()
        bundle.putString(Constant.KEY,next)
        outputFragment.arguments= bundle
        sendData()
       // Log.v(LOG_TAG, "~~~~~~~~~~~~~~~~~~\nDATA PASSED TO FRAGMENT :$next\n~~~~~~~~~~~~~~~~~~")

    }

    private fun sendData() {
        activity?.supportFragmentManager!!.beginTransaction()
            .add(R.id.fragment_output,outputFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun onError(e:Throwable){
        println("ERROR")
    }
}