package com.capken.catdogtubedomain.video.presentation.segmented

/**
 * Created by ken on 2017/01/21..
 */

interface SegmentProtocol {

    fun iconResourceId(): Int

    fun view(): SegmentContract.View

}