package com.github.difflib.algorithm;

public interface DiffAlgorithmListener {
   void diffStart();

   void diffStep(int var1, int var2);

   void diffEnd();
}
