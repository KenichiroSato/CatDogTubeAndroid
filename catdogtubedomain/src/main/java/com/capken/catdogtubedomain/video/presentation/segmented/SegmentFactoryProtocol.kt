package com.capken.catdogtubedomain.video.presentation.segmented

import com.capken.catdogtubedomain.player.PlayerContract

/**
 * Created by ken on 2017/01/21..
 */

interface SegmentFactoryProtocol {
    fun createSegments(playerPresenter:PlayerContract.Presenter): Array<SegmentProtocol>
}

