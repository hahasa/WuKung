package io.mountx.common.rom

import android.os.Build
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.lang.reflect.Method
import java.util.*

private const val BUILD_PROP_FILE_NAME = "build.prop"

internal class MxProperties {

    private val properties: Properties?
    private val methodGetSysProp: Method?

    init {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            properties = loadBuildPropFile()
            methodGetSysProp = null
        } else {//android 8+,read build.prop,throw permission denied
            properties = null
            methodGetSysProp = obtainMethodGetSysProp()
        }
    }

    private fun loadBuildPropFile(): Properties? {
        var fileInputStream: FileInputStream? = null
        return try {
            val buildPropFile = File(Environment.getRootDirectory(), BUILD_PROP_FILE_NAME)
            fileInputStream = FileInputStream(buildPropFile)
            val properties = Properties()
            properties.load(fileInputStream)
            properties
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            fileInputStream?.close()
        }
    }

    private fun obtainMethodGetSysProp(): Method? =
        try {
            Class.forName("android.os.SystemProperties")
                .getDeclaredMethod("get", String::class.java)
        } catch (e: ClassNotFoundException) {
            null
        } catch (e: NoSuchFieldException) {
            null
        }

    fun getSysPropValue(propKey: String): String? {
        return when {
            properties != null -> properties.getProperty(propKey)
            methodGetSysProp != null -> {
                try {
                    methodGetSysProp.invoke(null, propKey) as? String
                } catch (e: IllegalArgumentException) {
                    null
                } catch (e: IllegalAccessException) {
                    null
                }
            }
            else -> null
        }?.toLowerCase(Locale.ROOT)
    }
}