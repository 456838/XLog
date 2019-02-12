package com.salton123.plugin.util

import org.apache.commons.io.FileUtils
import org.gradle.api.Project

class Utils {

    /**
     * 获取root Project对象
     * @param project
     * @return
     */
    static Project getRootProject(Project project) {
        Project parentProject, tmpProject = project
        while (tmpProject != null) {
            parentProject = tmpProject
            tmpProject = tmpProject.parent
        }
        parentProject
    }

    /**
     * 根据文件路径删除目录或者文件
     * @param path
     */
    static void deleteFile(String path) {
        try {
            File file = new File(path)
            if (file.exists()) {
                if (file.isDirectory()) {
                    FileUtils.deleteDirectory(file)
                } else {
                    FileUtils.deleteQuietly(file)
                }
            }
        } catch (IOException e) {
            println "sniper plugin delete file " + path + " fail, ----->the reason is " + e.message
        }
    }

}
