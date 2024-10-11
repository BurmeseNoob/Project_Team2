#### **USE CASE 3: Organizing Countries in a Region by Largest Population to Smallest**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can organize all countries in a region by population size to understand population concentration.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses population data for each country in the region.

#### **Success End Condition**
The system generates a list of countries within a region organized by population.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst evaluates the regional population dynamics.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system selects data for countries within the region.
The system organizes countries in descending population order.
The analyst uses data for regional analysis.

#### **EXTENSIONS**
If certain countries in the specified region lack data, the system proceeds with available data and flags missing entries.
If multiple regions are selected, the system requests clarification or allows sorting across regions, noting the regions alongside country names.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0

