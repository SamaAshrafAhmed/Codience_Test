# Codience Test

This repository is a test repo for **Codience**, our AI-powered tool for pull request prioritization.

## What is in this repo?

A small Java system that scores and ranks pull requests using simple prioritization rules:

- security work is ranked highest
- bug fixes and hotfixes are prioritized above features
- draft pull requests are pushed to the bottom
- older pull requests get a small boost
- smaller pull requests are slightly favored
- business impact (1-10) increases priority
- risk score (1-10) increases priority

The demo now includes 15 pull requests with diverse labels, risk scores, and business impact values.

## Project structure

- `src/main/java/...` - Java source code
- `src/test/java/...` - JUnit tests
- `pom.xml` - Maven build configuration

## Run the app

```bash
mvn test
mvn exec:java
```

## Example output

```text
Codience PR priority queue
--------------------------
PR-101 | score=... | Fix auth bypass edge case
...
```