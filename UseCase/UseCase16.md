#### **USE CASE 16: Retrieving Top N Populated Cities in a District**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated cities in a district to analyze localized population trends.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses population data for cities within the specified district.

#### **Success End Condition**
The system displays the top N populated cities in the district.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst sets N to focus on local population analysis.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves district-specific data.
The system sorts and displays the top N cities by population.
#### **EXTENSIONS**
If N exceeds the number of cities in the district, the system returns results up to the available number.
If population data for certain cities is missing, the system skips them.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0
