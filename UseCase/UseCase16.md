### USE CASE 16: **Top N Populated Cities in a District**

#### **Goal in Context**:
Provide detailed local population data on the largest cities in a district for decision-making in urban planning and resource allocation.

#### **Scope**:
Analyze population data for cities within a district to produce a report on the top **N** cities.

#### **Level**:
User-goal level for localized planning and governance.

#### **Preconditions**:
- Access to district-level population data.
- Tools for data analysis.
- Defined value of **N** and district of interest.

#### **Success End Condition**:
A report on the top **N** district cities is generated and accepted.

#### **Failed End Condition**:
The report is incomplete, inaccurate, or delayed.

#### **Primary Actor**:
Data Analyst.

#### **Trigger**:
Request from local government or planners.

#### **Main Success Scenario**:
1. Request for top **N** cities in a district initiated.
2. Analyst clarifies requirements (N, district, additional metrics).
3. Data is sourced, validated, and sorted.
4. Report generated and submitted for review.
5. Feedback incorporated if necessary.
6. Final report accepted.

#### **Extensions**:
- Data for some cities is missing or outdated.
- Inconsistencies found in data.

#### **Sub-Variations**:
- Request for multiple districts.
- Additional metrics requested (e.g., economic activity).

#### **Schedule**:
- **Day 1**: Define **N** and district, gather data.
- **Day 2-3**: Validate and sort data.
- **Day 4**: Generate and review report.
- **Day 5**: Incorporate feedback (if needed).
