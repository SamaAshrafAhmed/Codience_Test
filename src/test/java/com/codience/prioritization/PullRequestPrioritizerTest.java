package com.codience.prioritization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class PullRequestPrioritizerTest {

    private final PullRequestPrioritizer prioritizer = new PullRequestPrioritizer();

    @Test
    void securityPullRequestsRankAboveFeaturePullRequests() {
        PullRequest securityPr = new PullRequest("PR-1", "Security fix", List.of("security"), 7, 7, 3, 0, false, 4);
        PullRequest featurePr = new PullRequest("PR-2", "New feature", List.of("feature"), 7, 7, 3, 0, false, 4);

        List<PrioritizedPullRequest> ranked = prioritizer.rank(List.of(featurePr, securityPr));

        assertEquals("PR-1", ranked.get(0).pullRequest().id());
    }

    @Test
    void draftPullRequestsAlwaysRankLast() {
        PullRequest readyPr = new PullRequest("PR-1", "Ready PR", List.of("bugfix"), 6, 5, 2, 1, false, 10);
        PullRequest draftPr = new PullRequest("PR-2", "Draft PR", List.of("security"), 10, 10, 1, 0, true, 100);

        List<PrioritizedPullRequest> ranked = prioritizer.rank(List.of(draftPr, readyPr));

        assertEquals("PR-2", ranked.get(ranked.size() - 1).pullRequest().id());
        assertTrue(ranked.get(ranked.size() - 1).score() == 0);
    }

    @Test
    void olderPullRequestsGetAHigherScoreWhenOtherInputsMatch() {
        PullRequest newer = new PullRequest("PR-1", "Newer", List.of("bugfix"), 5, 5, 5, 1, false, 2);
        PullRequest older = new PullRequest("PR-2", "Older", List.of("bugfix"), 5, 5, 5, 1, false, 24);

        List<PrioritizedPullRequest> ranked = prioritizer.rank(List.of(newer, older));

        assertEquals("PR-2", ranked.get(0).pullRequest().id());
        assertTrue(ranked.get(0).score() > ranked.get(1).score());
    }

    @Test
    void higherBusinessImpactRanksAboveLowerWhenOtherInputsMatch() {
        PullRequest lowImpact = new PullRequest("PR-1", "Low impact", List.of("bugfix"), 2, 4, 5, 1, false, 12);
        PullRequest highImpact = new PullRequest("PR-2", "High impact", List.of("bugfix"), 9, 4, 5, 1, false, 12);

        List<PrioritizedPullRequest> ranked = prioritizer.rank(List.of(lowImpact, highImpact));

        assertEquals("PR-2", ranked.get(0).pullRequest().id());
    }

    @Test
    void higherRiskRanksAboveLowerWhenOtherInputsMatch() {
        PullRequest lowRisk = new PullRequest("PR-1", "Low risk", List.of("feature"), 6, 2, 5, 1, false, 12);
        PullRequest highRisk = new PullRequest("PR-2", "High risk", List.of("feature"), 6, 9, 5, 1, false, 12);

        List<PrioritizedPullRequest> ranked = prioritizer.rank(List.of(lowRisk, highRisk));

        assertEquals("PR-2", ranked.get(0).pullRequest().id());
    }
}
