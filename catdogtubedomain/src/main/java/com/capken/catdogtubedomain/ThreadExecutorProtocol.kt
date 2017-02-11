package com.capken.catdogtubedomain

/**
 * Created by ken on 2017/01/14..
 */

interface ThreadExecutorProtocol {

    fun runOnMain(block:() -> Unit)

    fun runOnBackground(block: () -> Unit)

}