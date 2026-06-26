package com.codience.prioritization;

public record PrioritizedPullRequest(
        PullRequest pullRequest,
        int score
) {
}
