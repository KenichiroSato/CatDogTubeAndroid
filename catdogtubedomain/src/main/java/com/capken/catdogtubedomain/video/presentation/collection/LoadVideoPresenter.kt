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

    //Trigger the additional data load when RecyclerView scroll position is near to bottom
    private val LOAD_TRIGGER = 20

    private var pageToken: String = ""

    private var isLoading = false

    private var videoList: MutableList<Video> = mutableListOf()

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
            videoList.addAll(videos)
            this.view.show(videoList)
            this.view.hideErrorUI()
            isLoading = false
        }
    }

    private fun onLoadFail() {
        executor.runOnMain {
            this.view.show(emptyList())
            this.view.showErrorUI()
            isLoading = false
        }
    }

    // MARK: VideoCollectionContract_Presenter
    override fun setPlayer(player: PlayerContract.Presenter) {
        playerPresenter = player
    }

    override fun loadVideo(withFullScreenIndicator: Boolean) {
        print("loadVideo")
        executor.runOnMain {
            if (isLoading) { return@runOnMain }
            isLoading = true
            if (withFullScreenIndicator) {
                view.showLoadingIndicator()
            }

            executor.runOnBackground {
                this.useCase.loadVideos(pageToken) { videos, token ->
                    val nonNilVideos = videos?.let { it } ?: run {
                        this.onLoadFail()
                        return@loadVideos
                    }
                    this.pageToken = token
                    this.onLoadSuccess(nonNilVideos)
                }
            }
        }
    }

    override fun markAsPrimal() {
        isPrimal = true
    }

    override fun onVideoTapped(video: Video) {
        playerPresenter?.onVideoTapped(video)
    }

    override fun onScrolled(visiblePosition: Int) {
        if (visiblePosition > videoList.size - LOAD_TRIGGER) {
            loadVideo(false)
        }
    }
}
