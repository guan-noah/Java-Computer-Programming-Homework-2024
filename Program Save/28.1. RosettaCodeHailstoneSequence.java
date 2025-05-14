//RosettaCodeHailstoneSequence.java
/* Noah Guan
 * 11-13-2024
 * Per. 6 Java w/ Mr. Yu
 * RosettaCodeHailstoneSequence.java
 * Program #28 
 * Link: https://rosettacode.org/wiki/Hailstone_sequence#Java 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RosettaCodeHailstoneSequence {

  public static List<Long> getHailstoneSequence(long n) {
    if (n <= 0)
      throw new IllegalArgumentException("Invalid starting sequence number");
    List<Long> list = new ArrayList<Long>();
    list.add(Long.valueOf(n));
    while (n != 1) {
      if ((n & 1) == 0)
        n = n / 2;
      else
        n = 3 * n + 1;
      list.add(Long.valueOf(n));
    }
    return list;
  }
  
  public static void main(String[] args) {
    List<Long> sequence27 = getHailstoneSequence(27);
    System.out.println("Sequence for 27 has " + sequence27.size() + " elements: " + sequence27);
    
    long MAX = 100000;
    // Simple way
    {
      long highestNumber = 1;
      int highestCount = 1;
      for (long i = 2; i < MAX; i++) {
        int count = getHailstoneSequence(i).size();
        if (count > highestCount) {
          highestCount = count;
          highestNumber = i;
        }
      }
      System.out.println("Method 1, number " + highestNumber + " has the longest sequence, with a length of " + highestCount);
    }
    
    // More memory efficient way
    {
      long highestNumber = 1;
      int highestCount = 1;
      for (long i = 2; i < MAX; i++) {
        int count = 1;
        long n = i;
        while (n != 1) {
          if ((n & 1) == 0)
            n = n / 2;
          else
            n = 3 * n + 1;
          count++;
        }
        if (count > highestCount) {
          highestCount = count;
          highestNumber = i;
        }
      }
      System.out.println("Method 2, number " + highestNumber + " has the longest sequence, with a length of " + highestCount);
    }
    
    // Efficient for analyzing all sequences
    {
      long highestNumber = 1;
      long highestCount = 1;
      Map<Long, Integer> sequenceMap = new HashMap<Long, Integer>();
      sequenceMap.put(Long.valueOf(1), Integer.valueOf(1));
      
      List<Long> currentList = new ArrayList<Long>();
      for (long i = 2; i < MAX; i++) {
        currentList.clear();
        Long n = Long.valueOf(i);
        Integer count = null;
        while ((count = sequenceMap.get(n)) == null) {
          currentList.add(n);
          long nValue = n.longValue();
          if ((nValue & 1) == 0)
            n = Long.valueOf(nValue / 2);
          else
            n = Long.valueOf(3 * nValue + 1);
        }
        int curCount = count.intValue();
        for (int j = currentList.size() - 1; j >= 0; j--)
          sequenceMap.put(currentList.get(j), Integer.valueOf(++curCount));
        if (curCount > highestCount) {
          highestCount = curCount;
          highestNumber = i;
        }
      }
      System.out.println("Method 3, number " + highestNumber + " has the longest sequence, with a length of " + highestCount);
    }
    return;
  }
}
Output:
Sequence for 27 has 112 elements: [27, 82, 41, 124, 62, 31, 94, 47, 142, 71, 214, 107, 322, 161, 484, 242, 121, 364, 182, 91, 274, 137, 412, 206, 103, 310, 155, 466, 233, 700, 350, 175, 526, 263, 790, 395, 1186, 593, 1780, 890, 445, 1336, 668, 334, 167, 502, 251, 754, 377, 1132, 566, 283, 850, 425, 1276, 638, 319, 958, 479, 1438, 719, 2158, 1079, 3238, 1619, 4858, 2429, 7288, 3644, 1822, 911, 2734, 1367, 4102, 2051, 6154, 3077, 9232, 4616, 2308, 1154, 577, 1732, 866, 433, 1300, 650, 325, 976, 488, 244, 122, 61, 184, 92, 46, 23, 70, 35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1]
Method 1, number 77031 has the longest sequence, with a length of 351
Method 2, number 77031 has the longest sequence, with a length of 351
Method 3, number 77031 has the longest sequence, with a length of 351
