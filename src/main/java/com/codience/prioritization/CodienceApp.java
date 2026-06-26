package com.codience.prioritization;

import java.util.List;

public class CodienceApp {

    public static void main(String[] args) {
        PullRequestPrioritizer prioritizer = new PullRequestPrioritizer();

        List<PullRequest> pullRequests = List.of(
            new PullRequest("PR-101", "Fix auth bypass edge case", List.of("security", "bugfix"), 10, 9, 4, 0, false, 8),
            new PullRequest("PR-102", "Add onboarding banner", List.of("feature"), 5, 3, 2, 1, false, 36),
            new PullRequest("PR-103", "Refactor notification service", List.of("maintenance"), 4, 5, 15, 2, false, 48),
            new PullRequest("PR-104", "Draft: update docs", List.of("docs"), 2, 2, 1, 0, true, 12),
            new PullRequest("PR-105", "Patch payment callback null handling", List.of("bugfix", "customer-impact"), 9, 8, 6, 0, false, 30),
            new PullRequest("PR-106", "Hotfix cart checkout retry logic", List.of("hotfix"), 8, 7, 5, 0, false, 18),
            new PullRequest("PR-107", "Improve search index batching", List.of("feature"), 6, 4, 18, 1, false, 10),
            new PullRequest("PR-108", "Security hardening for file uploads", List.of("security"), 9, 10, 12, 0, false, 20),
            new PullRequest("PR-109", "Fix typo in public API docs", List.of("docs"), 1, 1, 1, 2, false, 72),
            new PullRequest("PR-110", "Migrate cache keys to namespaced format", List.of("maintenance"), 7, 6, 22, 1, false, 60),
            new PullRequest("PR-111", "Feature flag for enterprise export", List.of("feature", "customer-impact"), 8, 4, 14, 0, false, 6),
            new PullRequest("PR-112", "Fix race condition in webhook dispatcher", List.of("bugfix", "hotfix"), 9, 9, 9, 0, false, 40),
            new PullRequest("PR-113", "Rotate leaked integration token", List.of("security", "hotfix"), 10, 10, 3, 0, false, 2),
            new PullRequest("PR-114", "Draft: redesign notification settings", List.of("feature"), 7, 5, 11, 0, true, 14),
            new PullRequest("PR-115", "Backfill missing invoice events", List.of("customer-impact", "bugfix"), 8, 7, 7, 1, false, 84)
        );

        System.out.println("Codience PR priority queue");
        System.out.println("--------------------------");

        for (PrioritizedPullRequest item : prioritizer.rank(pullRequests)) {
            PullRequest pr = item.pullRequest();
            System.out.printf(
                    "%s | score=%d | impact=%d | risk=%d | %s%n",
                    pr.id(),
                    item.score(),
                    pr.businessImpact(),
                    pr.riskScore(),
                    pr.title()
            );
        }
    }
}
