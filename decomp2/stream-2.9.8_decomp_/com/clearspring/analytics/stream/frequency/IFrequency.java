package com.clearspring.analytics.stream.frequency;

public interface IFrequency {
   void add(long var1, long var3);

   void add(String var1, long var2);

   long estimateCount(long var1);

   long estimateCount(String var1);

   long size();
}
