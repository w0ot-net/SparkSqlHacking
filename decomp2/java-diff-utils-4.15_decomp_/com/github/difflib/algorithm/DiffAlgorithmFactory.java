package com.github.difflib.algorithm;

import java.util.function.BiPredicate;

public interface DiffAlgorithmFactory {
   DiffAlgorithmI create();

   DiffAlgorithmI create(BiPredicate var1);
}
