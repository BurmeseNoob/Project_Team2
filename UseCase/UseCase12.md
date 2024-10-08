#### **USE CASE 12: Retrieving Top N Populated Cities in the World**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated cities in the world, where N is set by the user, to analyze global urban centers.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses population data for cities worldwide.

#### **Success End Condition**
The system returns the top N populated cities globally.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst needs to focus on the largest cities worldwide.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves global city data.
The system sorts cities and displays the top N by population.
#### **EXTENSIONS**
If N exceeds the number of cities available, the system displays the available total.
If certain cities lack population data, they are skipped, and the analyst is notified.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0
