#### **USE CASE 6: Retrieving Top N Populated Countries in a Region**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated countries in a region to strategize resource allocation.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses population data for countries within the specified region.

#### **Success End Condition**
The system returns the top N populated countries in a region.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst inputs N to filter top populated countries.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves data for the region.
The system sorts and shows the top N countries by population.

#### **EXTENSIONS**
If N exceeds the number of countries in the specified region, the system limits results to those available.
If the region contains countries with missing data, the system skips those entries.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0