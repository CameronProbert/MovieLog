package com.example.movielogger

import android.app.Application
import com.example.movielogger.data.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MovieApplication constructor() : Application() {
//    lateinit var appModule: AppModule
}