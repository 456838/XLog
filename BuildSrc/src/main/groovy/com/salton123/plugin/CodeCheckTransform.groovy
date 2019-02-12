package com.salton123.plugin

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.google.common.collect.Sets
import org.gradle.api.Project

class CodeCheckTransform extends Transform {
    private static final String TAG = "CodeCheckTransfrom"
    private Project mProject

    CodeCheckTransform(Project project) {
        mProject = project
    }

    @Override
    String getName() {
        return TAG
    }

    /**
     * 指定输入的类型，通过这里的设定，可以指定我们要处理的文件类型
     * @return
     */
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return Sets.immutableEnumSet(QualifiedContent.DefaultContentType.CLASSES)
    }

    /**
     * 指定Transform的作用范围
     * @return
     */
    @Override
    Set<QualifiedContent.Scope> getScopes() {
//        return TransformManager.SCOPE_FULL_PROJECT
        return Sets.immutableEnumSet(
                QualifiedContent.Scope.PROJECT,
                QualifiedContent.Scope.SUB_PROJECTS)
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        project.logger.error("-------------->CodeCheckTransform inject start<--------------")

    }
}
