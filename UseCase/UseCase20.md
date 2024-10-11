#### **USE CASE 20: Retrieving Top N Populated Capital Cities in the World**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated capital cities in the world, where N is defined by the user, to analyze major global capitals.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses population data for all capital cities globally.

#### **Success End Condition**
The system displays the top N populated capital cities.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst sets N to analyze top populated capitals globally.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves data for global capital cities.
The system sorts and shows the top N capitals by population.
#### **EXTENSIONS**
If N exceeds the number of capital cities, the system limits results to the available total.
If some capital cities lack data, they are omitted from the results.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0

