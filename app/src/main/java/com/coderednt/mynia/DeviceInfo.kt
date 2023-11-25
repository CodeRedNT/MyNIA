package com.coderednt.mynia

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.PowerManager
import android.os.StatFs
import java.io.Serializable

object DeviceInfo {
    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context.applicationContext
    }

    fun getInfo(): Map<String, java.io.Serializable>? {
        val cpuArch = Build.SUPPORTED_ABIS[0]
        val deviceModel = Build.MODEL
        val osVersion = Build.VERSION.RELEASE

        val displayMetrics = context.resources.displayMetrics

        val density = displayMetrics.density
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        val freeSpace = getAvailableInternalMemorySize()

        val atts = HashMap<String, Serializable>()
        atts["arch"] = cpuArch
        atts["deviceModel"] = deviceModel
        atts["osVersion"] = osVersion
        atts["screenDensity"] = density
        atts["screenResolution"] = "$screenWidth x $screenHeight"
        atts["freeSpace"] = freeSpace
        atts["isPowerModeEnabled"] = isPowerModeEnabled()
        return atts
    }

    private fun getAvailableInternalMemorySize(): Long {
        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val availableBlocks = stat.availableBlocksLong
        return (availableBlocks * blockSize)
    }

    private fun isPowerModeEnabled(): Boolean {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return powerManager.isPowerSaveMode
    }
}