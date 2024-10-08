#### **USE CASE 14: Retrieving Top N Populated Cities in a Region**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated cities in a region, where N is provided by the user, for regional planning.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses population data for cities within the specified region.

#### **Success End Condition**
The system displays the top N populated cities in the region.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst sets N to focus on top urban centers in the region.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves region-specific data.
The system sorts and displays the top N cities by population.
#### **EXTENSIONS**
If N exceeds the number of cities in the region, the system limits results accordingly.
If data for some cities is missing, they are skipped, and the system proceeds with the remaining entries.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0

