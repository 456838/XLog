package com.salton123.lintchecks;

import com.android.tools.lint.client.api.JavaEvaluator;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.JavaContext;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;

import java.util.Arrays;
import java.util.List;

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/15 2:01 PM
 * ModifyTime: 2:01 PM
 * Description:
 */
public class LogDetector extends Detector implements Detector.UastScanner {
    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("tag", "format", "v", "d", "i", "w", "e", "wtf");
    }

    @Override
    public void visitMethod(JavaContext context, UCallExpression call, PsiMethod method) {
        super.visitMethod(context, call, method);
        JavaEvaluator evaluator = context.getEvaluator();

        if (evaluator.isMemberInClass(method, "android.util.Log")) {
            // LintFix fix = quickFixIssueLog(call);
            System.out.println("find log");
            context.report(XLogIssueRegistry.ISSUE_LOG, call, context.getLocation(call), "Using 'Log' instead of 'Timber'");
            return;
        }
    }

}
