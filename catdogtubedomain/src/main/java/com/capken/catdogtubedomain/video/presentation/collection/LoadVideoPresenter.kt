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

    private var pageToken: String? = null

    private var isLoading = false

    private var isPaginationFinished = false

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
            if (videos.isEmpty()) {
                isPaginationFinished = true
            } else {
                videoList.addAll(videos)
                view.show(videoList)
            }
            view.hideErrorUI()
            isLoading = false
        }
    }

    private fun onLoadFail() {
        executor.runOnMain {
            this.view.show(emptyList())
            this.view.showErrorUI()
            clearVideos()
            isLoading = false
        }
    }

    private fun clearVideos() {
        videoList.clear()
        pageToken = null
    }

    private fun isLoadSuccess(opVideos: List<Video>?): Boolean {
        val videos = opVideos?.let { it } ?: run {
            return false
        }
        if (videos.isEmpty() && pageToken == null) {
            return false
        }
        return true
    }

    private fun loadVideo(withIndocator: Boolean) {
        print("loadVideo")
        executor.runOnMain {
            if (isLoading) {
                return@runOnMain
            }
            isLoading = true
            if (withIndocator) {
                view.showLoadingIndicator()
            }
            executor.runOnBackground {
                this.useCase.loadVideos(pageToken) { videos, token ->
                    if (!isLoadSuccess(videos)) {
                        this.onLoadFail()
                        return@loadVideos
                    }
                    this.pageToken = token
                    this.onLoadSuccess(videos!!) // videos is not null
                }
            }
        }
    }

    override fun loadVideo() {
        val needIndicator = videoList.size == 0
        loadVideo(needIndicator)
    }

    override fun refreshVideos() {
        clearVideos()
        isPaginationFinished = false
        loadVideo(false)
    }

    override fun markAsPrimal() {
        isPrimal = true
    }

    override fun onVideoTapped(video: Video) {
        playerPresenter?.onVideoTapped(video)
    }

    override fun onScrolled(visiblePosition: Int) {
        if (isPaginationFinished) {
            return
        }
        if (visiblePosition > videoList.size - LOAD_TRIGGER) {
            loadVideo(false)
        }
    }
}
