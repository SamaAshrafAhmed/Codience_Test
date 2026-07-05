# Codience Jira Project Blueprint

## Project definition

- Project name: Codience Reviewer Signals
- Project key: CRS
- Project type: Company-managed software project (Scrum)
- Goal: Collect high-quality contributor delivery signals so Codience can recommend the best reviewers for each incoming PR.

## Contributors (5)

1. Sama Ashraf (`sama.ashraf`) - Security & Compliance specialist
2. Malak Hesham (`malak.hesham`) - Payments & Data Integrity specialist
3. Nada Ahmed (`nada.ahmed`) - Frontend & UX specialist
4. Shahd Moahmed (`shahd.moahmed`) - Platform Reliability specialist
5. Salma Yasser (`salma.yasser`) - Backend Architecture specialist

## Jira components

Components are optional for this workflow. If Jira makes them hard to manage, skip them and use only assignee + labels.

## Issue types

- Story
- Bug
- Task
- Incident
- Spike

## Custom fields required for Codience recommendation engine

1. Risk Score (Number 1-10)
2. Business Impact (Number 1-10)
3. Affected Domain (Single select: Security, Payments, API, Frontend, Platform, Data)
4. Change Type (Single select: Hotfix, Feature, Refactor, Docs, Maintenance)
5. Reviewer Candidate Pool (User picker, multiple)
6. Linked PR ID (Text, e.g. PR-214)
7. PR Urgency (Single select: Low, Medium, High, Critical)

## Workflow

1. Backlog
2. Selected for Development
3. In Progress
4. In Review
5. Ready for QA
6. Done

## Automation rules

1. If Risk Score >= 9, add label `high-risk`.
2. If Business Impact >= 8, add label `high-impact`.
3. If Change Type = Hotfix, notify the assignee and one backup reviewer.
4. If issue moves to In Review, add a comment reminding the reviewer to check the linked PR and risk notes.

## Reviewer recommendation scoring design

Codience score for candidate reviewer $r$ on PR $p$:

$$
score(r, p) = 0.35 \cdot DomainMatch + 0.20 \cdot RiskExperience + 0.20 \cdot ImpactExperience + 0.15 \cdot RecentThroughput + 0.10 \cdot ReviewLoadBalance
$$

Where each signal is normalized to [0, 1] from Jira history:

- DomainMatch: overlap between issue component/domain and contributor historical closed issues.
- RiskExperience: contributor success on tickets with similar risk buckets.
- ImpactExperience: contributor success on tickets with similar business impact.
- RecentThroughput: last-30-day completed tickets in matching domain.
- ReviewLoadBalance: inverse of current active review load.

## Mapping rule from your simulated PRs

Each simulated PR (PR-201 to PR-215) is represented as one Jira issue with:

- Linked PR ID = PR-xxx
- Risk Score and Business Impact copied from PR title
- Assignee set directly to one of the five contributors
- Optional labels for Codience risk and impact tracking

Use `issues.csv` for import.
