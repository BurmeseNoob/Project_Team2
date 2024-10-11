#### **USE CASE 24: Population Analysis of People, People in Cities, and People not in Cities in Each Region**
#### **CHARACTERISTIC INFORMATION**

#### **Goal in Context**
As a data analyst, I can analyze the distribution of people living in cities and those not living in cities within each region to understand urbanization trends and regional demographics.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system has access to data on populations for each region.

#### **Success End Condition**
The system generates a report detailing the total population for each region.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The analyst needs demographic information on the population by region.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system retrieves population data for each region.
The system calculates the total population per region.
The system generates a report displaying the total population for each region.
The analyst reviews the data to understand population trends within each region.
#### **EXTENSIONS**
If some regions lack complete data, the system proceeds with available information and flags missing entries.
If additional filters (e.g., by country) are needed, the system prompts for clarification.

If the data for any region is incomplete, the system notifies the analyst and proceeds with available data where possible.
#### **SUB-VARIATIONS**

The system may support exporting the report in various formats (e.g., CSV, PDF) for further analysis.
#### **SCHEDULE**
DUE DATE : Release 1.0