#### **USE CASE 13: Retrieving Top N Populated Cities in a Continent**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the top N populated cities in a continent, where N is defined by the user, for strategic planning.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses city population data for the continent.

#### **Success End Condition**
The system returns the top N populated cities on the continent.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst sets N to examine major cities in the continent.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system selects continent-specific data.
The system displays the top N populated cities.
#### **EXTENSIONS**
If N exceeds the number of cities available in the continent, the system returns the available total.
If specific cities lack population data, the system skips them.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0

