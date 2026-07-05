# Jira Website Guide: Create Normal Assigned Tickets for Codience

## 1) Create the project

1. In Jira, click Projects -> Create project.
2. Choose Company-managed software project.
3. Select Scrum template.
4. Name: Codience Reviewer Signals
5. Key: CRS

## 2) Prepare users

Ensure these users exist in Jira and have access to the CRS project:

- sama.ashraf
- malak.hesham
- nada.ahmed
- shahd.moahmed
- salma.yasser

## 3) Use contributors as the ownership map

Use `contributors.csv` as your internal reference sheet for who should normally own each ticket.

Current contributor-domain mapping:

- sama.ashraf: Frontend, UI-UX
- malak.hesham: Backend, Payments, AI
- nada.ahmed: Backend, Integrations
- shahd.moahmed: DevOps, Documentation, Security
- salma.yasser: Design, Frontend support

Suggested assignment policy for normal Jira tickets:

- Security and DevOps tickets -> shahd.moahmed
- Payments and backend fixes -> malak.hesham
- Frontend and UI/UX tickets -> sama.ashraf
- Documentation tickets -> shahd.moahmed
- Design-heavy tickets -> salma.yasser
- Integration/backend support tickets -> nada.ahmed

## 4) Create tickets normally in Jira

For each ticket:

1. Click Create in Jira.
2. Choose the issue type: Story, Bug, Task, Incident, or Spike.
3. Enter the summary.
4. Assign the ticket to the correct person.
5. Add the description.
6. If you want Codience tracking, add the risk score, business impact, and linked PR ID in the description or as labels.

## 5) Import tickets from CSV if you want bulk creation

1. Go to Settings -> System -> External system import -> CSV.
2. Upload `issues.csv`.
3. Select destination project: CRS.
4. Map CSV columns to Jira fields:
   - IssueKey -> Issue Key (or ignore if auto-generated keys required)
   - Summary -> Summary
   - IssueType -> Issue Type
   - RiskScore -> put into description or a custom field if available
   - BusinessImpact -> put into description or a custom field if available
   - Assignee -> Assignee
   - LinkedPRID -> put into description or a custom field if available
5. Run import and review the import report.

## 6) Validate sample issues

Open CRS-201, CRS-205, CRS-214 and verify:

- Correct assignee is set
- Linked PR ID matches PR-201, PR-205, PR-214

## 7) Optional Jira automation

If you want Codience-style signals later, create rules like these:

1. Trigger: Issue created
   Condition: Risk Score >= 9
   Actions: Add label high-risk

2. Trigger: Issue created
   Condition: Business Impact >= 8
   Action: Add label high-impact

3. Trigger: Issue transitioned to In Review
   Action: Add comment "Ready for Codience reviewer recommendation."

## 8) Connect to Codience recommendation engine

Export or query the following per issue:

- Risk Score
- Business Impact
- Assignee
- Linked PR ID
- Issue status transitions if available

These signals can be joined with GitHub PR metadata using Linked PR ID, while keeping ticket ownership simple.
