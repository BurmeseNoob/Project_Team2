#### **USE CASE 28: Retrieving the Population of a Region**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve the total population of a specific region to understand its demographic density.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system has access to population data for the specified region.

#### **Success End Condition**
The system displays the total population for the region.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst needs information on the population size of a particular region.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves population data for the specified region.
The system displays the total population for the region.
#### **EXTENSIONS**
If population data for some countries within the region is missing, the system uses the available data and flags missing entries.
The system prompts for clarification if multiple regions are selected.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0
