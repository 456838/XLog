package com.salton123.log

import android.os.Environment
import android.os.Process

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/9 1:54 PM
 * ModifyTime: 1:54 PM
 * Description:
 */
object Utils {
    fun getDefaultPath(): String {
        return Environment.getExternalStorageDirectory().path
    }

    fun checkSDCardAvailable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun getCallerLineNumber(): Int {
        return Thread.currentThread().stackTrace[4].lineNumber
    }

    fun getCallerFilename(): String {
        return Thread.currentThread().stackTrace[4].fileName
    }

    fun getCallerMethodName(): String {
        return Thread.currentThread().stackTrace[4].methodName
    }

    fun msgForTextLog(obj: Any, filename: String, line: Int, msg: String, methodName: String): String {
        val sb = StringBuilder()
        sb.append(msg)
        sb.append("(P:")
        sb.append(Process.myPid())
        sb.append(")")
        sb.append("(T:")
        sb.append(Thread.currentThread().id)
        sb.append(")")
        sb.append("(C:")
        sb.append(objClassName(obj))
        sb.append(")")
        sb.append("(M:")
        sb.append(methodName)
        sb.append(")")
        sb.append(" at (")
        sb.append(filename)
        sb.append(":")
        sb.append(line)
        sb.append(")")
        return sb.toString()
    }

    fun objClassName(obj: Any): String {
        return obj as? String ?: obj.javaClass.simpleName
    }

}