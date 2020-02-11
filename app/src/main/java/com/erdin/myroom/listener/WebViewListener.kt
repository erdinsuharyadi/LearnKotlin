package com.erdin.myroom.listener

interface WebViewListener {
    fun onPageStarted()
    fun onPageFinished()
    fun onShouldOverrideUrl(redirectUrl: String)
    fun onProgressChanged(progress: Int)
}