package com.capken.catdogtubedomain.video.presentation.segmented

import com.capken.catdogtubedomain.player.PlayerContract

/**
 * Created by ken on 2017/01/28..
 */

class SegmentsPresenter(private var view: SegmentedContract.View,
                        private val segmentFactory: SegmentFactoryProtocol) : SegmentedContract.Presenter {

    init {
        view.setPresenter(this)
    }

    private var segments: List<SegmentProtocol> = emptyList()

    //private var notificationAdopter: TeamNotificationContract_ReceiveAdopter?


    //MARK: SegmentedContract_Presenter
    override fun loadSegments() {
        segments = segmentFactory.createSegments()
        view.show(segments)
    }

    /*
    //MARK: TeamNotificationContract_Receiver
    public fun set(adopter: TeamNotificationContract_ReceiveAdopter) {
        notificationAdopter = adopter
    }

    public fun onSelected(team: Team) {
        guard let firstSegment = segments.first as? SearchSegment,
        team.contentType != firstSegment.contentType else {
            return
        }
        segments.reverse()
        view.reorder(segments: segments)
    }
*/
}
