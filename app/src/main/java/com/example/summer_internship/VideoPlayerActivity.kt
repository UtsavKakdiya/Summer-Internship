package com.example.summer_internship

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var playPauseButton: ImageButton
    private lateinit var seekBar: SeekBar
    private lateinit var handler1: Handler
    private lateinit var currentDuration: TextView
    private lateinit var mediaControlsLayout: ConstraintLayout
    private lateinit var maxDuration: TextView
    var videoViewClicked = false
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_video_player)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        videoView = findViewById(R.id.videoView)
        playPauseButton = findViewById(R.id.play_pause_btn)
        seekBar = findViewById(R.id.seekBar_video)
        currentDuration = findViewById(R.id.current_duration)
        maxDuration = findViewById(R.id.max_duration)
        handler1 = Handler()
        handler = Handler(Looper.getMainLooper())
        mediaControlsLayout = findViewById(R.id.mediaControlsLayout)

        // Path to your video file (local or URL)
        val videoPath = "android.resource://$packageName/${R.raw.sample_video1}"

        val uri: Uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        // Adding media controls
//        val mediaController = MediaController(this)
//        videoView.setMediaController(mediaController)
//        mediaController.setAnchorView(videoView)

        // Start playing the video
        videoView.start()

        playPauseButton.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
            else if (videoView.isPlaying) {
                videoView.pause()
            }
        }

        videoView.setOnPreparedListener { mediaPlayer ->
            seekBar.max = mediaPlayer.duration
//            maxDuration.text = mediaPlayer.duration.toString()
            updateSeekBar()
            maxDuration.text = formatDuration(mediaPlayer.duration)
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    videoView.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Optional: Implement if needed
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Optional: Implement if needed
            }
        })

        videoView.setOnClickListener {
            if (mediaControlsLayout.visibility != View.VISIBLE) {
                mediaControlsLayout.visibility = View.VISIBLE
                // Hide media controls after 5 seconds
                handler.postDelayed({
                    mediaControlsLayout.visibility = View.GONE
                }, 5000)
            }
            else if (mediaControlsLayout.visibility == View.VISIBLE) {
                mediaControlsLayout.visibility = View.GONE
            }
        }

    }

    private fun formatDuration(duration: Int): String {
        val hours = duration / (1000 * 60 * 60)
        val minutes = (duration / (1000 * 60)) % 60
        val seconds = (duration / 1000) % 60

        if (hours == 0) {
            return String.format("%02d:%02d", minutes, seconds)
        }
        else {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
    }

    private fun updateSeekBar() {
        handler1.postDelayed(object : Runnable {
            override fun run() {
                seekBar.progress = videoView.currentPosition
                currentDuration.text = formatDuration(videoView.currentPosition)
                handler1.postDelayed(this, 100)
            }
        }, 100)
    }

    fun goToDetailsOfCourseActivity(view: View) {val intent = Intent(this, DetailsOfCourse::class.java)
        startActivity(intent)
        finish()
    }
}