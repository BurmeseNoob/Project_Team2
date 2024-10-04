### 21. USE CASE: **Generate Report on the Top N Populated Non-Capital Cities in a Region**

---

#### **Goal in Context**:
The organization requires a report on the top **N** most populated non-capital cities in a region to support regional planning and market research.

#### **Scope**:
Collect population data for non-capital cities in the specified region, rank the top **N**, and generate a report.

#### **Level**:
User-goal level (regional market analysis).

#### **Preconditions**:
- User-defined **N**.

#### **Success End Condition**:
The report successfully ranks the top **N** non-capital cities and is accepted by stakeholders.

#### **Failed End Condition**:
The report is inaccurate, incomplete, or delayed.

#### **Primary Actor**:
Data Analyst.

#### **Trigger**:
A request from senior management for a report on top **N** non-capital cities in a region.

---

#### **MAIN SUCCESS SCENARIO**:
1. Request is initiated.
2. Analyst clarifies **N** and requirements.
3. Access to data is ensured.
4. Data is extracted.
5. Data is validated.
6. Cities are ranked.
7. Report is generated and reviewed.
8. Report is submitted.
9. Feedback is incorporated if necessary.
10. The report is accepted.

---

#### **EXTENSIONS**:
- **3a**: Data is unavailable for some cities.
- **5a**: Data inconsistencies.

#### **SUB-VARIATIONS**:
- **None**

#### **SCHEDULE**:
- **DUE DATE: Release 0.1.0.5**