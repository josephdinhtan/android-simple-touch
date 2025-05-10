package com.jddev.simpletouch.utils.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CoroutineScope.launchIo(block: suspend ()  -> Unit) {
    launch(Dispatchers.IO) {
        block()
    }
}

fun CoroutineScope.launchMain(block: suspend ()  -> Unit) {
    launch(Dispatchers.Main) {
        block()
    }
}
