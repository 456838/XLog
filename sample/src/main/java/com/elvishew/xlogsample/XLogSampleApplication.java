/*
 * Copyright 2016 Elvis Hew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elvishew.xlogsample;

import android.app.Application;

import com.salton123.log.Utils;
import com.salton123.log.XLogConfig;
import com.salton123.log.printer.Printer;

import java.io.File;

public class XLogSampleApplication extends Application {

    public static Printer globalFilePrinter;

    private static final long MAX_TIME = 1000 * 60 * 60 * 24 * 2; // two days

    @Override
    public void onCreate() {
        super.onCreate();
        initXlog();
    }

    /**
     * Initialize XLog.
     */
    private void initXlog() {
        XLogConfig.init(new XLogConfig.Builder()
                .setSavePath(Utils.INSTANCE.getDefaultPath() + File.separator + "xlog" + File.separator + this.getPackageName())
                .build());
    }
}
