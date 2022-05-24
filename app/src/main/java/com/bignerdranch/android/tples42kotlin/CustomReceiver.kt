package com.bignerdranch.android.tples42kotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action
        var toastMessage = context.getString(R.string.unknown_intent_action)

        when (intentAction) {
                Intent.ACTION_POWER_CONNECTED -> {
                    toastMessage = context.getString(R.string.action_power_connected)
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    toastMessage = context.getString(R.string.action_power_disconnected)
                }
                // Coding challenge
                Intent.ACTION_HEADSET_PLUG -> {
                    toastMessage = if (intent.extras?.getInt("state", 0) == 0) {
                        context.getString(R.string.action_headset_plug_off)
                    }
                    else context.getString(R.string.action_headset_plug_on)
                }
                "ACTION_CUSTOM_BROADCAST" -> {
                    val date = intent.extras?.getInt("data") ?: 0
                    toastMessage = "${date * date}"
                }
            }

        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }
}

