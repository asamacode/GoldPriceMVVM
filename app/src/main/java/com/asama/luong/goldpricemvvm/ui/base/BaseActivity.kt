package com.asama.luong.goldpricemvvm.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {

    private var progressDialog: ProgressDialog? = null
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun showDialogLoading() {
        hideDialog()
        progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    fun hideDialog() {
        progressDialog?.let {
            if (it.isShowing)
                it.cancel()
        }
    }
}