package com.clearspring.analytics.stream;

import java.util.List;

public interface ISampleSet {
   long put(Object var1);

   long put(Object var1, int var2);

   Object removeRandom();

   Object peek();

   List peek(int var1);

   int size();

   long count();
}
