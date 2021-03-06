# Recursion Tree
## Introduction: 
There are several ways to solve recurrences. Those include substitution method, recursion-tree method and master method. In here, I designed an algorithm to generate a recursion tree. The recursion-tree method converts the recurrence into a tree whose nodes
represent the costs incurred at various levels of the recursion and then use this technique for bounding summations to solve the recurrence (Cormen et al., 2009, p. 66).
<br><b>My source code Link: https://github.com/wei06159git/WeiShan-Portfolio/tree/master/Recursion_Tree/src</b></br> 
<br><b>My algorithm accepts the following recurrence forms as shown below: </b></br>
- Divide-and-Conquer: T(n) = aT(n/b) + f(n), where constants
  - a is an integer such that a >= 1
  - b > 1, and b is a rational number

- Chip-and-Be-Conquered: T(n) = aT(n-b) + f(n), where constants
  - a is an integer such that a >= 1
  - b > 0, and b is an integer
  
<br><b>My algorithm accpets the f(n) forms as shown below: </b></br>
  - polynomial - c(n^d) where c & d are rational numbers
  - logarithmic - c(log(base e)^(d)(n)), where c,d and e are rational numbers

<b>My analysis of Big-O asymptotic running time for Divide-and-Conquer Recursion Tree & Chip-and-Be-Conquer Recursion Tree: </b>
<img src="Divide-and-Conquer Recursion Tree.png" width="854" height="421">
<img src="Chip-and-Be-Conquer Recursion Tree.png" width="854" height="421">

### Reference:
Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). Introduction to algorithms. Retrieved from https://ebookcentral.proquest.com
