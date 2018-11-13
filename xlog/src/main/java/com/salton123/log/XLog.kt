package com.salton123.log

import android.util.Log
import com.salton123.log.printer.FilePrinter

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/9 1:47 PM
 * ModifyTime: 1:47 PM
 * Description:a simple usage of log with io
 */
object XLog {
    private var sConfig: XLogConfig = XLogConfig.sConfig
    private var mFilePrinter: FilePrinter

    init {
        mFilePrinter = FilePrinter(this.sConfig)
    }

    @JvmStatic
    fun v(tag: Any, msg: String) {
        var line = Utils.getCallerLineNumber()
        var fileName = Utils.getCallerFilename()
        var methodName = Utils.getCallerMethodName()
        var logText = Utils.msgForTextLog(tag, fileName, line, msg, methodName)
        if (sConfig.isDebugable) {
            saveLog(logText)
            Log.v(Utils.objClassName(tag), logText)
        }
    }

    @JvmStatic
    fun d(tag: Any, msg: String) {
        var line = Utils.getCallerLineNumber()
        var fileName = Utils.getCallerFilename()
        var methodName = Utils.getCallerMethodName()
        var logText = Utils.msgForTextLog(tag, fileName, line, msg, methodName)
        if (sConfig.isDebugable) {
            saveLog(logText)
            Log.d(Utils.objClassName(tag), logText)
        }
    }

    @JvmStatic
    fun i(tag: Any, msg: String) {
        var line = Utils.getCallerLineNumber()
        var fileName = Utils.getCallerFilename()
        var methodName = Utils.getCallerMethodName()
        var logText = Utils.msgForTextLog(tag, fileName, line, msg, methodName)
        if (sConfig.isDebugable) {
            saveLog(logText)
            Log.i(Utils.objClassName(tag), logText)
        }
    }

    @JvmStatic
    fun w(tag: Any, msg: String) {
        var line = Utils.getCallerLineNumber()
        var fileName = Utils.getCallerFilename()
        var methodName = Utils.getCallerMethodName()
        var logText = Utils.msgForTextLog(tag, fileName, line, msg, methodName)
        if (sConfig.isDebugable) {
            saveLog(logText)
            Log.w(Utils.objClassName(tag), logText)
        }
    }

    @JvmStatic
    fun e(tag: Any, msg: String) {
        var line = Utils.getCallerLineNumber()
        var fileName = Utils.getCallerFilename()
        var methodName = Utils.getCallerMethodName()
        var logText = Utils.msgForTextLog(tag, fileName, line, msg, methodName)
        if (sConfig.isDebugable) {
            saveLog(logText)
            Log.e(Utils.objClassName(tag), logText)
        }
    }

    private fun saveLog(msg: String) {
        if (sConfig.whetherToSaveLog) {
            mFilePrinter.println(0, "", msg)
        }
    }

}