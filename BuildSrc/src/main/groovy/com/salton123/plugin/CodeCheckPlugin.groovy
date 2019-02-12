package com.salton123.plugin

import com.android.build.gradle.AppPlugin
import com.salton123.plugin.util.ImportReplaceTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

class CodeCheckPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.logger.error("start CodeCheck")
        Project rootProject = getRootProject(project)
//        rootProject.subprojects.each {
//            if(it.name != project.name){
//                it.afterEvaluate {
//                    it.apply plugin : "com.salton123.plugin.CodeCheckPlugin"
//                }
//            }
//        }
        /* 注册SniperTransform */
//        project.android.registerTransform(CodeCheckTransform(project))
        def isApp = project.plugins.hasPlugin(AppPlugin)
        if (isApp) {
            project.android.registerTransform(new ImportReplaceTransform())
        }
    }

    static Project getRootProject(Project project) {
        Project parentProject, tmpProject = project
        while (tmpProject != null) {
            parentProject = tmpProject
            tmpProject = tmpProject.parent
        }
        parentProject
    }
}
