#### **USE CASE 4: Retrieving Top N Populated Countries in the World**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated countries in the world, where N is defined by the user, to focus on high-density populations.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses global population data for each country.

#### **Success End Condition**
The system returns the top N populated countries globally.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst sets N to filter top populated countries.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves global country data.
The system sorts and displays the top N populated countries.
#### **EXTENSIONS**
If N exceeds the number of available countries, the system limits the results to the total number of countries and informs the analyst.
If no data is available for a specific country, it is skipped, and the system proceeds with available data.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0