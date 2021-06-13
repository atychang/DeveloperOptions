package com.atychang.developeroptions

import android.content.Intent
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
            startActivityAndCollapse(getIntent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS))
        } else {
            startActivityAndCollapse(getIntent(Settings.ACTION_SETTINGS))
            Toast.makeText(
                this,
                R.string.developer_options_is_disabled,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getIntent(action: String): Intent {
        return Intent(action).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }
}
