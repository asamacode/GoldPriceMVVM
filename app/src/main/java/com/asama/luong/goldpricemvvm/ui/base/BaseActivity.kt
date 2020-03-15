package com.asama.luong.goldpricemvvm.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    private var progressDialog: ProgressDialog? = null

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        job = Job()
        super.onCreate(savedInstanceState, persistentState)
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