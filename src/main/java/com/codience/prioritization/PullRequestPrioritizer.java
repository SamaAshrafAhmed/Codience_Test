package com.codience.prioritization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class PullRequestPrioritizer {

    public List<PrioritizedPullRequest> rank(List<PullRequest> pullRequests) {
        List<PrioritizedPullRequest> ranked = new ArrayList<>();

        for (PullRequest pullRequest : pullRequests) {
            ranked.add(new PrioritizedPullRequest(pullRequest, score(pullRequest)));
        }

        ranked.sort(Comparator
                .comparingInt(PrioritizedPullRequest::score).reversed()
                .thenComparing(item -> item.pullRequest().id()));

        return ranked;
    }

    int score(PullRequest pullRequest) {
        if (pullRequest.draft()) {
            return 0;
        }

        int score = 20;

        // Impact and risk are normalized to a 1-10 scale.
        score += boundedScale(pullRequest.businessImpact(), 1, 10) * 5;
        score += boundedScale(pullRequest.riskScore(), 1, 10) * 4;

        for (String label : pullRequest.labels()) {
            score += labelWeight(label);
        }

        if (pullRequest.approvals() == 0) {
            score += 15;
        }

        score += Math.min(pullRequest.ageHours() / 12, 20);
        score += Math.max(0, 10 - pullRequest.changedFiles() / 5);

        return score;
    }

    private int boundedScale(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private int labelWeight(String label) {
        String normalized = label.toLowerCase(Locale.ROOT);
        return switch (normalized) {
            case "security" -> 50;
            case "bugfix" -> 30;
            case "hotfix" -> 25;
            case "customer-impact" -> 20;
            case "feature" -> 10;
            default -> 0;
        };
    }
}
