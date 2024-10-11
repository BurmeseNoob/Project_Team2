#### **USE CASE 27: Retrieving the Population of a Continent**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can retrieve a continent’s total population to analyze demographic density by continent.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system has access to population data for the specified continent.

#### **Success End Condition**
The system displays the population total for the continent.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst needs an overview of the continent’s demographic size.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves population data for the continent.
The system displays the total population for the selected continent.
#### **EXTENSIONS**
If some countries in the continent lack data, the system estimates the total and flags missing entries.
The system prompts if multiple continents are selected.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0