package com.salton123.log

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/9 6:16 PM
 * ModifyTime: 6:16 PM
 * Description:builder sConfig
 */

class XLogConfig private constructor(){

    var isDebugable = true  //日志开关
        private set
    var savePath = Utils.getDefaultPath()   //保存位置
        private set
    var whetherToSaveLog = true //是否保存日志
        private set
    var logDeleteDelayDay = 10   //默认10天后删除
        private set
    var logDefaultSplitSize = 1  //日志拆分大小阈值，每1M拆分
        private set

    companion object {
        @JvmStatic
        fun init(config: XLogConfig) {
            this.sConfig = config
        }

        var sConfig: XLogConfig = XLogConfig()
            private set
    }


    class Builder {
        private var isDebugable = true
        private var savePath = Utils.getDefaultPath()
        private var whetherToSaveLog = true
        var logDeleteDelayDay = 10
        var logDefaultSplitSize = 1

        fun setDebugable(isDebugable: Boolean): Builder {
            this.isDebugable = isDebugable
            return this@Builder
        }

        fun setSavePath(savePath: String): Builder {
            this.savePath = savePath
            return this@Builder
        }

        fun whetherToSaveLog(whetherToSaveLog: Boolean): Builder {
            this.whetherToSaveLog = whetherToSaveLog
            return this@Builder
        }

        fun logDeleteDelayDay(logDeleteDelayDay: Int): Builder {
            this.logDeleteDelayDay = logDeleteDelayDay
            return this@Builder
        }


        fun logDefaultSplitSize(logDefaultSplitSize: Int): Builder {
            this.logDefaultSplitSize = logDeleteDelayDay
            return this@Builder
        }

        fun build(): XLogConfig {
            var config = XLogConfig()
            config.isDebugable = isDebugable
            config.savePath = savePath
            config.whetherToSaveLog = whetherToSaveLog
            config.logDefaultSplitSize = logDefaultSplitSize
            config.logDeleteDelayDay = logDeleteDelayDay
            return config
        }
    }
}