package com.kaii.googlelensasassistant

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle

class LensAsAssistantActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finish()

        val intent = Intent(Intent.ACTION_MAIN)
        
        intent.setComponent(ComponentName.unflattenFromString("com.google.ar.lens/com.google.vr.apps.ornament.app.lens.LensLauncherActivity"))

        startActivity(intent)
    }
}
