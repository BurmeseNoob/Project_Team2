#### **USE CASE 10: Organizing Cities in a Country by Largest Population to Smallest**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can organize cities in a country by population size to evaluate domestic urban population distribution.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses population data for cities within the country.

#### **Success End Condition**
The system generates a list of cities in descending order of population for a specific country.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst wants to evaluate the population of the country.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves data for cities in the country.
The system sorts cities by population size.
The analyst uses the data to understand population concentration.
#### **EXTENSIONS**
If certain cities in the specified country are missing data, the system proceeds with the available cities and flags missing entries.
If multiple countries are selected, the system requests clarification or applies all countries.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0
