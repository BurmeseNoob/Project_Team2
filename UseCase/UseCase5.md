#### **USE CASE 5: Retrieving Top N Populated Countries in a Continent**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated countries in a continent to target high-population regions.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses population data for countries in the specified continent.

#### **Success End Condition**
The system returns the top N populated countries in a continent.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst sets N for top populated countries.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves continent-specific data.
The system sorts countries by population and displays the top N.
#### **EXTENSIONS**
If N exceeds the number of countries in the continent, the system returns results up to the total available.
If population data for a specific country is missing, the system will skip it and proceed with the remaining countries.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0