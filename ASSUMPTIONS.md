# Assumptions
- JDK 1.8 is used
- Time complexity: O(N), where N is the number of triangle rows (Every row is touched once)
- Space complexity: O(N), where N is the number of triangle rows (N stacks are created due to recursive invocations + a list of N elements is maintained 
  for storing the minimum path)