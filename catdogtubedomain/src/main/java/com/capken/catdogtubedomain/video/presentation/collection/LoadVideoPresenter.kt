package com.capken.catdogtubedomain.video.presentation.collection

import com.capken.catdogtubedomain.ThreadExecutorProtocol
import com.capken.catdogtubedomain.player.PlayerContract
import com.capken.catdogtubedomain.video.domain.LoadVideoUseCase
import com.capken.catdogtubedomain.video.domain.model.Video

/**
 * Created by ken on 2017/01/28..
 * Presenter for Video list UI.
 */
class LoadVideoPresenter(private val view: VideoCollectionContract.View,
                         private val useCase: LoadVideoUseCase,
                         private val executor: ThreadExecutorProtocol,
                         var playerPresenter: PlayerContract.Presenter?)
    : VideoCollectionContract.Presenter {

    init {
        view.setPresenter(this)
    }

        // If true, top contents of this presenter' view will played when app is launched.
    private var isPrimal = false

    private fun onLoadSuccess(videos: List<Video>) {
        executor.runOnMain {
            if (this.isPrimal) {
                this.playerPresenter?.onVideoLoaded(videos)
            }
            this.view.show(videos)
            this.view.hideErrorUI()
        }
    }

    private fun onLoadFail() {
        executor.runOnMain {
            this.view.show(emptyList())
            this.view.showErrorUI()
        }
    }

    // MARK: VideoCollectionContract_Presenter
    override fun setPlayer(player: PlayerContract.Presenter) {
        playerPresenter = player
    }

    override fun loadVideo(withFullScreenIndicator: Boolean) {
        if (withFullScreenIndicator) {
            view.showLoadingIndicator()
        }

        executor.runOnBackground {
            this.useCase.loadVideos { videos ->
                val nonNilVideos = videos?.let { it } ?: run {
                    this.onLoadFail()
                    return@loadVideos
                }
                /*
                guard let nonNilVideos = videos , !nonNilVideos.isEmpty else {
                this.onLoadFail()
                return
                */
                this.onLoadSuccess(nonNilVideos)
            }
        }
    }

    override fun markAsPrimal() {
        isPrimal = true
    }

    override fun onVideoTapped(video: Video) {
        playerPresenter?.onVideoTapped(video)
    }
}
