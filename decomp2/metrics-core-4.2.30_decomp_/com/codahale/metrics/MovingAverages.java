package com.codahale.metrics;

public interface MovingAverages {
   void tickIfNecessary();

   void update(long n);

   double getM1Rate();

   double getM5Rate();

   double getM15Rate();
}
