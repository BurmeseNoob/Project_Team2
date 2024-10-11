#### **USE CASE 15: Retrieving Top N Populated Cities in a Country**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated cities in a country, where N is specified by the user, to focus on highly populated urban centers in the country.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system has access to population data for cities within the specified country.

#### **Success End Condition**
The system displays the top N populated cities in the country.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst sets N to explore top cities in a country.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves city population data for the country.
The system sorts and shows the top N cities by population.
#### **EXTENSIONS**
If N exceeds the number of cities in the country, the system returns results up to the total available.
If some cities lack data, the system proceeds with the remaining cities.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0
