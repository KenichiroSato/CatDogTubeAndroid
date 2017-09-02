package com.capken.catdogtubedomain.video.domain.search

import com.capken.catdogtubedomain.video.domain.model.Video

/**
 * Created by ken on 2017/02/11..
 */

class VideoExcluder {

    companion object {
        val NG_WORDS: List<String> = listOf(
                "シュレディンガー",
                "ワルツ",
                "おまわりさん",
                "オッパイ",
                "織田裕二",
                "マーチ",
                "Greeeen",
                "力士",
                "Lineage",
                "プルー",
                "食糞",
                "ヨンヨン",
                "ソフトバンク",
                "アンドロメダ",
                "えっち",
                "メイク",
                "Mmd",
                "なめこ",
                "迷子",
                "Mimosa",
                "難波",
                "ピアノ",
                "ツチグモ",
                "3D",
                "MGS",
                "クモ",
                "アニメ",
                "CM",
                "ひまわり",
                "はねられ",
                "マツコ",
                "nikon",
                "東方",
                "性",
                "虐待",
                "合唱",
                "ダンス",
                "物語",
                "死",
                "エロ",
                "嵐",
                "マイクラ",
                "運動会",
                "閲覧注意",
                "リンパ",
                "初音",
                "Episode",
                "プリキュア",
                "ミケランジェロ",
                "メガネ",
                "MAD",
                "やさぐれ",
                "ミスキャンパス",
                "踏み潰す",
                "日本橋",
                "りゅりゅ",
                "うんこ",
                "リンゴの森",
                "禁欲",
                "やんちゃな",
                "ペットショップ",
                "きくちペット",
                "歌ってみた",
                "ショッキング"
        )

        fun excludeInappropriateVideos(videos: List<Video>?): List<Video>? {
            return videos?.filter { !VideoExcluder.isInappropriateVideo(it) }
        }

        private fun isInappropriateVideo(video: Video): Boolean {
            for (ngWord in VideoExcluder.NG_WORDS) {
                if (video.title.toLowerCase().contains(ngWord.toLowerCase())) {
                    return true
                }
            }
            return false
        }

    }
}