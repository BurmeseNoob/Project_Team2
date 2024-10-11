#### **USE CASE 21: Retrieving Top N Populated Capital Cities in a Continent**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated capital cities in a continent, where N is provided by the user, to analyze the largest capitals on the continent.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses population data for capital cities in the specified continent.

#### **Success End Condition**
The system displays the top N populated capital cities in the continent.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst defines N to study major capitals in the continent.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system selects data for continent-specific capital cities.
The system sorts and displays the top N capitals.

#### **EXTENSIONS**
If N exceeds the number of capital cities in the continent, the system returns the total available.
If some cities are missing data, they are skipped.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0