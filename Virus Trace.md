# Virus Trace
### Background: 
There is a need to help security analysts monitor a collection of networked computers, tracking the spread of an online virus. 
The security analysts would like to know "if the virus was inserted into computer C1 at time x, could it possibly have infected computer C2 by time y?"
### Solutions: 
I designed an algorithm that it will decide and output which computer will get infected during the time interval. There are 2 source files. 
One is brute-force and has running time O(mn). The other one is using disjoint set algorithm and has running time O(nlogn).
