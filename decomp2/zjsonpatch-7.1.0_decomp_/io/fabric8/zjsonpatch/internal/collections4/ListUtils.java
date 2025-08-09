package io.fabric8.zjsonpatch.internal.collections4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ListUtils {
   private ListUtils() {
   }

   public static List longestCommonSubsequence(List list1, List list2) {
      Objects.requireNonNull(list1, "listA");
      Objects.requireNonNull(list2, "listB");
      int[][] dp = new int[list1.size() + 1][list2.size() + 1];

      for(int list1Index = 1; list1Index <= list1.size(); ++list1Index) {
         for(int list2Index = 1; list2Index <= list2.size(); ++list2Index) {
            if (list1.get(list1Index - 1).equals(list2.get(list2Index - 1))) {
               dp[list1Index][list2Index] = dp[list1Index - 1][list2Index - 1] + 1;
            } else {
               dp[list1Index][list2Index] = Math.max(dp[list1Index - 1][list2Index], dp[list1Index][list2Index - 1]);
            }
         }
      }

      List<T> lcs = new ArrayList();
      int list1Index = list1.size();
      int list2Index = list2.size();

      while(list1Index > 0 && list2Index > 0) {
         if (list1.get(list1Index - 1).equals(list2.get(list2Index - 1))) {
            lcs.add(list1.get(list1Index - 1));
            --list1Index;
            --list2Index;
         } else if (dp[list1Index - 1][list2Index] >= dp[list1Index][list2Index - 1]) {
            --list1Index;
         } else {
            --list2Index;
         }
      }

      Collections.reverse(lcs);
      return lcs;
   }
}
