package com.codience.prioritization;

import java.util.List;

public record PullRequest(
        String id,
        String title,
        List<String> labels,
        int businessImpact,
        int riskScore,
        int changedFiles,
        int approvals,
        boolean draft,
        int ageHours
) {
}
