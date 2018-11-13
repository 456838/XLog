package com.salton123.log.printer

import android.util.Log
import com.salton123.io.FlushWriter
import com.salton123.log.Utils
import com.salton123.log.XLogConfig
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/13 11:28 AM
 * ModifyTime: 11:28 AM
 * Description:
 */

class FilePrinter(var config: XLogConfig) : Printer {
    private val fileName = "zlog.txt"
    private val bakExt = ".bak"
    var saveLogFilePath = config.savePath + File.separator + fileName
    private var mWriter: Writer = Writer()
    private var path: String = config.savePath
    private var logDefaultSplitSize = config.logDefaultSplitSize * 24 * 60 * 60 * 1000

    init {
        mWriter.open(path, fileName)
    }

    override fun println(logLevel: Int, tag: String?, msg: String?) {
        if (!Utils.checkSDCardAvailable()) {
            Log.e("FilePrinter", "SDCard Not Available")
            return
        }
        val date = Date()
        val dirFile = File(path)
        if (!dirFile.exists()) {
            dirFile.mkdirs()
        }
        val logFile = File(saveLogFilePath)
        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: Exception) {
                e.printStackTrace()
                return
            }
        } else {
            val fileSize = logFile.length().ushr(20).toFloat()    //转为m
            if (fileSize > logDefaultSplitSize) {
                //删除大于10天的文件
                deleteOldLogs()
                val simpleDateFormate = SimpleDateFormat("-MM-dd-kk-mm-ss")
                val fileExt = simpleDateFormate.format(date)
                val sb = StringBuilder(path)
                sb.append(File.separator).append(fileName).append(fileExt)
                    .append(bakExt)
                val fileNameTo = File(sb.toString())
                logFile.renameTo(fileNameTo)
                if (mWriter.isOpened) {
                    mWriter.close()
                }
                mWriter.open(path, fileName)
            }
        }
        var strLog = SimpleDateFormat("yyyy:MM:dd kk:mm:ss.SSS").format(date)
        val sb = StringBuffer(strLog)
        sb.append(' ')
        sb.append(msg)
        sb.append('\n')
        strLog = sb.toString()
        if (!mWriter.isOpened) {
            mWriter.open(path, fileName)
        }
        mWriter.appendLog(strLog)
    }

    private fun deleteOldLogs() {
        val dirFile = File(path)
        if (!dirFile.exists()) {
            return
        }
        val now = System.currentTimeMillis()
        val files = dirFile.listFiles() ?: return
        for (file in files) {
            if (file.name.endsWith(bakExt)) {
                val lastModifiedTime = file.lastModified()
                if (now - lastModifiedTime > config.logDeleteDelayDay) {
                    file.delete()
                }
            }
        }
    }

    private inner class Writer {
        internal var lastFileName: String? = null
            private set
        internal var file: File? = null
            private set
        private var logBuffer: FlushWriter? = null
        internal val isOpened: Boolean
            get() = logBuffer != null

        internal fun open(folderPath: String, newFileName: String): Boolean {
            lastFileName = newFileName
            file = File(folderPath, newFileName)
            if (!file!!.exists()) {
                try {
                    val parent = file!!.parentFile
                    if (!parent.exists()) {
                        parent.mkdirs()
                    }
                    file!!.createNewFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                    lastFileName = null
                    file = null
                    return false
                }
            }
            try {
                logBuffer = FlushWriter(file!!.absolutePath + "_buf",
                    8192 * 2, file!!.absolutePath, false)
            } catch (e: Exception) {
                e.printStackTrace()
                lastFileName = null
                file = null
                return false
            }
            return true
        }

        internal fun close(): Boolean {
            if (logBuffer != null) {
                try {
                    logBuffer!!.release()
                } catch (e: Exception) {
                    e.printStackTrace()
                    return false
                } finally {
                    logBuffer = null
                    lastFileName = null
                    file = null
                }
            }
            return true
        }

        internal fun appendLog(flattenedLog: String) {
            try {
                logBuffer!!.write(flattenedLog)
                logBuffer!!.flushAsync()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}