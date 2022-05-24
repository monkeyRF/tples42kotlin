package com.bignerdranch.android.tples42kotlin

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var customReceiver: CustomReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customReceiver = CustomReceiver()

        // Подписываемся на питание
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_POWER_CONNECTED)
        }
        registerReceiver(customReceiver, filter)

        // Пробуем собственное локальное событие
        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver,  IntentFilter("ACTION_CUSTOM_BROADCAST"))
        findViewById<Button>(R.id.sendBroadcast).setOnClickListener {
            val intent = Intent("ACTION_CUSTOM_BROADCAST")
            // Homework
            intent.putExtra("data", Random.nextInt(0,20))
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Отпишемся от прослушки
        this.unregisterReceiver(customReceiver)
    }
}