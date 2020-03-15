package com.asama.luong.goldpricemvvm.ui.main.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.asama.luong.goldpricemvvm.R
import com.asama.luong.goldpricemvvm.ui.base.BaseActivity
import com.asama.luong.goldpricemvvm.ui.base.ViewModelFactory
import com.asama.luong.goldpricemvvm.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : BaseActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val viewModelFactory: ViewModelFactory by instance()

    private lateinit var  mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()
        bindUI()
    }

    private fun bindUI() = launch {
        showDialogLoading()
        val metalsData = mainViewModel.metalsData.await()

        metalsData.observe(this@MainActivity, Observer {
            textView.text = "EUR: ${it.EUR}"
            textView2.text = "GBP: ${it.GBP}"
            textView3.text = "JPY: ${it.JPY}"

            hideDialog()
        })
    }


    private fun setUpViewModel() {
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

}
