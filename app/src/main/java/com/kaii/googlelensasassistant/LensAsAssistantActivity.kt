package com.kaii.googlelensasassistant

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream

class LensAsAssistantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        takeScreenshotAndLaunchLens()
    }

    private fun takeScreenshotAndLaunchLens() {
        val bitmap = takeScreenshot()
        launchGoogleLens(bitmap)
    }

    private fun takeScreenshot(): Bitmap {
        return window.decorView.rootView.drawToBitmap()
    }

    private fun launchGoogleLens(bitmap: Bitmap) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.setComponent(ComponentName.unflattenFromString("com.google.ar.lens/com.google.vr.apps.ornament.app.lens.LensLauncherActivity"))
        
        val uri = createUriFromBitmap(bitmap)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(intent)

        // Clean up after launching Google Lens
        finish()
    }

    private fun createUriFromBitmap(bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream().use { bos ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
            bos.toByteArray()
        }
        return Uri.parse("data:image/png;base64," + android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT))
    }
}
