package com.capken.catdogtube

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.capken.catdogtubedomain.video.domain.model.ContentType
import com.capken.catdogtubedomain.video.domain.model.Video
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val video:Video = Video("videoId", "title", URL("http:www.yahoo.co.jp"), ContentType.cat)

        val textView = findViewById(R.id.text_view) as TextView
        textView.text = video.describe()

    }
}
