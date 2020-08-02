# Virus Trace
### Background: 
There is a need to help security analysts monitor a collection of networked computers, tracking the spread of an online virus. 
The security analysts would like to know "if the virus was inserted into computer C1 at time x, could it possibly have infected computer C2 by time y?"
### Solutions: 
I designed an algorithm that it will decide and output which computer will get infected during the time interval. There are 2 source files. 
One is brute-force and has running time O(mn) or O(n^2). The other one is using disjoint set algorithm and has running time O(nlogn).

### Assumption:
There are n computers in the system, labeled C1,...,Cn,and as input, I am given a collection of trace date indicating the times at which pairs of computers communicated.
The data is a sequence of ordered triples (Ci,Cj,tk). Such a triple indicates that Ci and Cj communicated at time tk.
Let's assume computer C1 got infected at time 2. 

### Brute-Force Code:

### Optimized Code (Disjoint Set Algorithm):