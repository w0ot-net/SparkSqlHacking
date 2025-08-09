package com.codahale.metrics;

public interface Reservoir {
   int size();

   void update(long value);

   Snapshot getSnapshot();
}
