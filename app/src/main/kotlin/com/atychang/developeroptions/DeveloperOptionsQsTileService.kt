package com.atychang.developeroptions

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.service.quicksettings.TileService
import android.widget.Toast

class DeveloperOptionsQsTileService : TileService() {

    override fun onClick() {
        super.onClick()

        val isDeveloperOptionsEnabled = Settings.Secure.getInt(
            contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
            0
        ) == 1

        if (isDeveloperOptionsEnabled) {
            openSettings(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
        } else {
            openSettings(Settings.ACTION_SETTINGS)
            Toast.makeText(
                this,
                R.string.developer_options_is_disabled,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun openSettings(settingsAction: String) {
        val intent = getIntent(settingsAction)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startActivityAndCollapseLegacy(intent)
        } else {
            val pendingIntent: PendingIntent = getPendingIntent(intent)
            startActivityAndCollapse(pendingIntent)
        }
    }

    @Suppress("DEPRECATION")
    @SuppressLint("StartActivityAndCollapseDeprecated")
    private fun startActivityAndCollapseLegacy(intent: Intent) {
        startActivityAndCollapse(intent)
    }

    private fun getIntent(action: String): Intent {
        return Intent(action).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }

    private fun getPendingIntent(intent: Intent): PendingIntent {
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}
