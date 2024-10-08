#### **USE CASE 22: Retrieving Top N Populated Capital Cities in a Region**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated capital cities in a region, where N is defined by the user, to understand the region’s largest capitals.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses population data for capital cities in the region.

#### **Success End Condition**
The system displays the top N populated capital cities in the region.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst specifies N to focus on top capital cities in a region.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves data for the region’s capital cities.
The system sorts and shows the top N capitals by population.
#### **EXTENSIONS**
If N exceeds the available capital cities in the region, the system limits results to the total available.
If certain cities lack data, they are omitted from the list.
#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0

