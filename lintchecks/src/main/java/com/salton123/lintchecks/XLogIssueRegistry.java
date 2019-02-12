package com.salton123.lintchecks;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.Arrays;
import java.util.List;

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/15 2:30 PM
 * ModifyTime: 2:30 PM
 * Description:
 */
public class XLogIssueRegistry extends IssueRegistry {
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(ISSUE_LOG);
    }

    @Override
    public int getApi() {
        return ApiKt.CURRENT_API;
    }

    public static final Issue ISSUE_LOG =
            Issue.create("LogNotTimber", "Logging call to Log instead of Timber",
                    "Since Timber is included in the project, it is likely that calls to Log should instead"
                            + " be going to Timber.", Category.CORRECTNESS, 5, Severity.WARNING,
                    new Implementation(LogDetector.class, Scope.JAVA_FILE_SCOPE));
}
