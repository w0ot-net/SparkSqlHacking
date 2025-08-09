package com.github.difflib.algorithm;

import java.util.Arrays;
import java.util.List;

public interface DiffAlgorithmI {
   List computeDiff(List var1, List var2, DiffAlgorithmListener var3);

   default List computeDiff(Object[] source, Object[] target, DiffAlgorithmListener progress) {
      return this.computeDiff(Arrays.asList(source), Arrays.asList(target), progress);
   }
}
